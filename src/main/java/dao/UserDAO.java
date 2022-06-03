package dao;

import entity.User;
import gui.CheckUserScreen;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class UserDAO extends BaseDAO{

    // Pass jframe to display jdialogs
    public void saveUser(User u, JFrame jframe)
    {
        try(Connection conn = getConn()){
            // De timestamp van de SQL server staat 2 uur vroeger, vandaar wordt een datumvariabele vanuit java aangemaakt.
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            // Check for duplicates based on name, surname and email, these make a user unique
            // Could also change database to set those columns to unique, but Java has been chosen for practice
            // Also possible with COUNT(*)
            PreparedStatement psCheck = conn.prepareStatement("SELECT * FROM `gebruikers` WHERE voornaam = ? AND achternaam = ? AND email = ?");
            psCheck.setString(1, u.getName());
            psCheck.setString(2, u.getSurname());
            psCheck.setString(3, u.getEmail());
            ResultSet rs = psCheck.executeQuery();
            // User already exists
            if (rs.next()) {
                JOptionPane.showMessageDialog(jframe, "A user with this name, surname and e-mail already exists!", "Duplicate user", JOptionPane.WARNING_MESSAGE);
            }
            // User doesn't exist yet
            else {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `geboortedatum`, `geslacht`, `favobier`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, u.getName());
                ps.setString(2, u.getSurname());
                ps.setDate(3, (Date) u.getDOB());
                ps.setString(4, u.getGender());
                ps.setString(5, u.getFavoriteBeer());
                ps.setString(6, u.getProfession());
                ps.setString(7, u.getResidence());
                ps.setString(8, u.getEmail());
                ps.setDate(9, date);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(jframe, "User added!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> findUser(String n, String sn, String em) {
        ArrayList<User> usersFound = new ArrayList<>();
        String name, surname, gender, beer, profession, residence, email;
        Date DOB, joiningDate;
        try (Connection conn = getConn()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM gebruikers WHERE voornaam = ? OR achternaam = ? OR email = ?");
            // Searching is not case-sensitive!
            ps.setString(1, n);
            ps.setString(2, sn);
            ps.setString(3, em);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("voornaam");
                surname = rs.getString("achternaam");
                DOB = rs.getDate("geboortedatum");
                gender = rs.getString("geslacht");
                beer = rs.getString("favobier");
                profession = rs.getString("beroep");
                residence = rs.getString("woonplaats");
                email = rs.getString("email");
                joiningDate = rs.getDate("toetreding");
                User u = new User(name, surname, DOB, gender, beer, profession, residence, email, joiningDate);
                usersFound.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersFound; // null if user not found
    }

    // Deletes the user in the last row of the table shown in the FindUserScreen GUI
    // That is where the last user searched appears
    public void deleteUser(JTable jtable, JFrame jframe)
    {
        int lastRow = jtable.getModel().getRowCount()-1;
        if (lastRow < 0) {
            JOptionPane.showMessageDialog(jframe, "You must first search for a user before deleting.", "Delete error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            // Get name, surname and email of user to delete from jtable
            // We decided that these are what makes a user unique (see saveUser function above)
            String name = (String)jtable.getModel().getValueAt(lastRow, 0); // Returns object, cast to String
            String surname = (String)jtable.getModel().getValueAt(lastRow, 1);
            String email = (String)jtable.getModel().getValueAt(lastRow, 7);

            try (Connection conn = getConn()) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM gebruikers WHERE voornaam = ? AND achternaam = ? AND email = ?");
                ps.setString(1, name);
                ps.setString(2, surname);
                ps.setString(3, email);
                // Returns int amount of rows deleted
                int checkIfDeleted = ps.executeUpdate();
                if (checkIfDeleted != 0) {
                    JOptionPane.showMessageDialog(jframe, "The user has been deleted!.", "User deleted", JOptionPane.WARNING_MESSAGE);
                }
                // Delete error
                else {
                    JOptionPane.showMessageDialog(jframe, "The user has already been deleted or doesn't exist. Try searching for another user to delete first.", "User delete failed", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void selectUser(JTable jtable, JFrame jframe)
    {
        String beerName, variant, percentage, color;
        int lastRow = jtable.getModel().getRowCount()-1;
        if (lastRow < 0) {
            JOptionPane.showMessageDialog(jframe, "You must first search for a user before deleting.", "Delete error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            String name = (String)jtable.getModel().getValueAt(lastRow, 0); // Returns object, cast to String
            String surname = (String)jtable.getModel().getValueAt(lastRow, 1);
            String email = (String)jtable.getModel().getValueAt(lastRow, 7);
            try (Connection conn = getConn()) {
                PreparedStatement ps = conn.prepareStatement("SELECT b.* FROM gebruikers g JOIN gedronkenbieren gb ON g.gebruikersid = gb.gebruikersid JOIN bieren b ON b.bierid = gb.bierid WHERE voornaam = ? AND achternaam = ? AND email = ? ORDER BY biernaam, variant");
                ps.setString(1, name);
                ps.setString(2, surname);
                ps.setString(3, email);

                ResultSet rs = ps.executeQuery();
                CheckUserScreen cus = new CheckUserScreen(name);
                // Beers already ordered by name and then variant in SQL query
                while (rs.next()) {
                    beerName = rs.getString("biernaam");
                    variant = rs.getString("variant");
                    percentage = rs.getString("percentage");
                    color = rs.getString("kleur");
                    String[] tableData = {beerName, variant, percentage, color};
                    cus.addToTable(tableData);
                    }

            }catch (Exception e) {
                e.printStackTrace();
            }




        }
    }


}
