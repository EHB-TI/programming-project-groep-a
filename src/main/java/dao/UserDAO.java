package dao;

import entity.User;

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

    public ArrayList<User> findUser(String s) {
        ArrayList<User> usersFound = new ArrayList<>();
        String name, surname, gender, beer, profession, residence, email;
        Date DOB, joiningDate;

        try (Connection conn = getConn()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `gebruikers` WHERE achternaam = ? OR voornaam = ?");
            // Searching by name is not case-sensitive!
            ps.setString(1, s);
            ps.setString(2, s);
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
        try(Connection conn = getConn()){
            int lastRow = jtable.getModel().getRowCount()-1;
            if (lastRow < 0) {
                JOptionPane.showMessageDialog(jframe, "You must first search for a user before deleting.", "Delete error", JOptionPane.WARNING_MESSAGE);
            }
            // Get name, surname and email of user to delete from jtable
            // We decided that these are what makes a user unique (see saveUser function above)
            // Returns object
            Object name = jtable.getModel().getValueAt(lastRow, 0);
            Object surname = jtable.getModel().getValueAt(lastRow, 1);
            Object email = jtable.getModel().getValueAt(lastRow, 7);

            PreparedStatement ps = conn.prepareStatement("DELETE FROM `gebruikers` WHERE voornaam = ? AND achternaam = ? AND email = ?");
            ps.setString(1, (String)name); // Cast to string
            ps.setString(2, (String)surname);
            ps.setString(3, (String)email);
            // Returns int (amount of rows deleted)
            int checkIfDeleted = ps.executeUpdate();
            System.out.println(checkIfDeleted);
            if(checkIfDeleted != 0) {
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
