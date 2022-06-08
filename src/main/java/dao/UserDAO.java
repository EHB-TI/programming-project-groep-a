package dao;

import entity.User;
import gui.CheckUserScreen;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    // ALSO DELETE LINKS IN TABLE
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
                // Find user ID
                PreparedStatement psID = conn.prepareStatement("SELECT gebruikersid FROM gebruikers WHERE voornaam = ? AND achternaam = ? AND email = ?");
                ResultSet rs = psID.executeQuery();
                int ID = rs.getInt("gebruikersid");
                // Delete user's beers
                PreparedStatement psLink = conn.prepareStatement("DELETE FROM gedronkenbieren WHERE gebruikersid = ?");
                psLink.setInt(1, ID);
                int beersDeleted = psLink.executeUpdate();
                // Delete user
                PreparedStatement psDelete = conn.prepareStatement("DELETE FROM gebruikers WHERE gebruikersid = ?");
                psDelete.setInt(1, ID);
                // Returns int amount of rows deleted
                int checkIfDeleted = psDelete.executeUpdate();
                if (checkIfDeleted != 0) {
                    JOptionPane.showMessageDialog(jframe, "The user and the user's " + beersDeleted + " beers have been deleted!", "User deleted", JOptionPane.WARNING_MESSAGE);
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
        String beerName, variant, percentage, color, brewery;
        int userID = -1;

        int lastRow = jtable.getModel().getRowCount()-1;
        if (lastRow < 0) {
            JOptionPane.showMessageDialog(jframe, "You must first search for a user.", "No user selected", JOptionPane.WARNING_MESSAGE);
        }
        else {
            String name = (String)jtable.getModel().getValueAt(lastRow, 0); // Returns object, cast to String
            String surname = (String)jtable.getModel().getValueAt(lastRow, 1);
            String email = (String)jtable.getModel().getValueAt(lastRow, 7);
            try (Connection conn = getConn()) {
                PreparedStatement ps = conn.prepareStatement("SELECT b.*, g.gebruikersid FROM gebruikers g JOIN gedronkenbieren gb ON g.gebruikersid = gb.gebruikersid JOIN bieren b ON b.bierid = gb.bierid WHERE voornaam = ? AND achternaam = ? AND email = ? ORDER BY biernaam, variant");
                ps.setString(1, name);
                ps.setString(2, surname);
                ps.setString(3, email);
                ResultSet rs = ps.executeQuery();


                if (rs.next()) {
                    // Get ID out of result set
                    userID = Integer.parseInt(rs.getString("gebruikersid"));
                    // New screen to display user's beers
                    CheckUserScreen cus = new CheckUserScreen(name, surname, email, userID);
                    // Beers already ordered by name and then variant in SQL query
                    // Use do-while instead of while because if-statement already moved the cursor in resultset
                    do {
                        beerName = rs.getString("biernaam");
                        variant = rs.getString("variant");
                        percentage = rs.getString("percentage");
                        color = rs.getString("kleur");
                        brewery = rs.getString("brouwerij");

                        String[] tableData = {beerName, variant, percentage, color, brewery};
                        cus.addToTable(tableData, name);
                    } while (rs.next());
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addUserBeer(String beerName, String variant, double percentage, String color, String brewery, JFrame jframe, JTable table, DefaultTableModel model, int userID)
    {
        boolean duplicate = false;
        // Check for duplicates based on name and variant
        // Check if beer already in user table
        for(int i = 0; i < table.getRowCount(); i++){ // For each row, check first two columns
            if(table.getModel().getValueAt(i, 0).toString().equalsIgnoreCase(beerName) && table.getModel().getValueAt(i, 1).toString().equalsIgnoreCase(variant)) {
                    JOptionPane.showMessageDialog(jframe, "The user already drank this beer!", "Duplicate beer", JOptionPane.WARNING_MESSAGE);
                    duplicate = true;
            }
        }
        if (!duplicate) {
            try (Connection conn = getConn()) {
                // Check if beer already in database
                PreparedStatement psCheck = conn.prepareStatement("SELECT bierid FROM bieren WHERE biernaam = ? AND variant = ?");
                psCheck.setString(1, beerName);
                psCheck.setString(2, variant);
                ResultSet rs = psCheck.executeQuery();
                // Beer already exists --> add to jtable and user-beer table
                if (rs.next()) {
                    // Add to jtable
                    String[] tableData = {beerName, variant, String.valueOf(percentage), color, brewery};
                    model.addRow(tableData);
                    // Add to table user-beers
                    // Get user id and beer id
                    int beerID = rs.getInt("bierid");
                    PreparedStatement psLinkTable = conn.prepareStatement("INSERT INTO `gedronkenbieren`(`gebruikersid`, `bierid`) VALUES (?,?)");
                    psLinkTable.setInt(1, userID);
                    psLinkTable.setInt(2, beerID);
                    psLinkTable.executeUpdate();
                    JOptionPane.showMessageDialog(jframe, "Beer added!");
                }
                // Beer doesn't exist yet --> add to jtable AND database
                else {
                    // Add to jtable
                    String[] tableData = {beerName, variant, String.valueOf(percentage), color, brewery};
                    model.addRow(tableData);
                    // Add to database
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO `bieren`(`bierid`, `biernaam`, `variant`, `percentage`, `kleur`, `brouwerij`) VALUES (NULL,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, beerName);
                    ps.setString(2, variant);
                    ps.setDouble(3, percentage);
                    ps.setString(4, color);
                    ps.setString(5, brewery);
                    ps.executeUpdate();
                    // Add to table user-beers
                    ResultSet rsID = ps.getGeneratedKeys();
                    int beerID = -1;
                    if (rsID.next()) {beerID = rsID.getInt(1);}
                    System.out.println(beerID);
                    PreparedStatement psLinkTable = conn.prepareStatement("INSERT INTO `gedronkenbieren`(`gebruikersid`, `bierid`) VALUES (?,?)");
                    psLinkTable.setInt(1, userID);
                    psLinkTable.setInt(2, beerID);
                    psLinkTable.executeUpdate();

                    JOptionPane.showMessageDialog(jframe, "Beer added!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    } // End of addUserBeer






} // End of class
