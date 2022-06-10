package dao;

import entity.Beer;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class UserDAO extends BaseDAO{

    // Pass jframe to display jdialogs
    // Otherwise split in checkDuplicateUser and saveUser to call upon from FindUserScreen and show jdialog from there
    // But then connection has to be opened twice
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
                ps.setDate(3, java.sql.Date.valueOf(u.getDOB()));
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
        int userID;
        String DOB, joiningDate;
        try (Connection conn = getConn()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM gebruikers WHERE voornaam = ? OR achternaam = ? OR email = ?");
            // Searching is not case-sensitive!
            ps.setString(1, n);
            ps.setString(2, sn);
            ps.setString(3, em);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userID = rs.getInt("gebruikersid");
                name = rs.getString("voornaam");
                surname = rs.getString("achternaam");
                DOB = rs.getString("geboortedatum");
                gender = rs.getString("geslacht");
                beer = rs.getString("favobier");
                profession = rs.getString("beroep");
                residence = rs.getString("woonplaats");
                email = rs.getString("email");
                joiningDate = rs.getString("toetreding");
                User u = new User(userID, name, surname, LocalDate.parse(DOB), gender, beer, profession, residence, email, LocalDate.parse(joiningDate));
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
    public int[] deleteUser(int userID)
    {
        int[] checkIfDeleted = new int[2];
        try (Connection conn = getConn()) {
                // Delete user's beers
                PreparedStatement psLink = conn.prepareStatement("DELETE FROM gedronkenbieren WHERE gebruikersid = ?");
                psLink.setInt(1, userID);
                checkIfDeleted[0] = psLink.executeUpdate();
                // Delete user
                PreparedStatement psDelete = conn.prepareStatement("DELETE FROM gebruikers WHERE gebruikersid = ?");
                psDelete.setInt(1, userID);
                // Returns int amount of rows deleted
                checkIfDeleted[1] = psDelete.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return checkIfDeleted;

    }

    public ArrayList<Beer> selectUser(int userID)
    {
        ArrayList<Beer> beersFound = new ArrayList<>();
        String beerName, variant, color, brewery;
        double percentage;
        int beerID;
            try (Connection conn = getConn()) {
                PreparedStatement ps = conn.prepareStatement("SELECT b.* FROM gebruikers g JOIN gedronkenbieren gb ON g.gebruikersid = gb.gebruikersid JOIN bieren b ON b.bierid = gb.bierid WHERE g.gebruikersid = ? ORDER BY biernaam, variant");
                ps.setString(1, Integer.toString(userID));
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    // Beers already ordered by name and then variant in SQL query
                    beerName = rs.getString("biernaam");
                    variant = rs.getString("variant");
                    percentage = rs.getDouble("percentage");
                    color = rs.getString("kleur");
                    brewery = rs.getString("brouwerij");
                    beerID = rs.getInt("bierid");

                    Beer b = new Beer(beerID, beerName, variant, percentage, color, brewery);
                    beersFound.add(b);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        return beersFound;
    }

    // Returns same b object but with beerID added
    public Beer addUserBeer(Beer b, int userID)
    {
            try (Connection conn = getConn()) {
                // Check if beer already in database
                PreparedStatement psCheck = conn.prepareStatement("SELECT bierid FROM bieren WHERE biernaam = ? AND variant = ?");
                psCheck.setString(1, b.getName());
                psCheck.setString(2, b.getVariant());
                ResultSet rs = psCheck.executeQuery();
                // Beer already exists --> add to gedronkenbieren table
                if (rs.next()) {
                    // Add to table gedronkenbieren
                    // Get user id and beer id

                    int beerID = rs.getInt("bierid");
                    // Add to beer object and hidden id column in jtable (for remove)
                    b.setBeerID(beerID);
                    PreparedStatement psLinkTable = conn.prepareStatement("INSERT INTO `gedronkenbieren`(`gebruikersid`, `bierid`) VALUES (?,?)");
                    psLinkTable.setInt(1, userID);
                    psLinkTable.setInt(2, beerID);
                    psLinkTable.executeUpdate();
                }
                // Beer doesn't exist yet --> add to bieren and gedronkenbieren
                else {
                    // Add to database
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO `bieren`(`bierid`, `biernaam`, `variant`, `percentage`, `kleur`, `brouwerij`) VALUES (NULL,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, b.getName());
                    ps.setString(2, b.getVariant());
                    ps.setDouble(3, b.getAlcoholPercentage());
                    ps.setString(4, b.getColor());
                    ps.setString(5, b.getBrewery());
                    ps.executeUpdate();
                    // Add to table user-beers
                    ResultSet rsID = ps.getGeneratedKeys();
                    int beerID = -1;
                    if (rsID.next()) {
                        beerID = rsID.getInt(1);
                        // Add to beer object and hidden id column in jtable (for remove)
                        b.setBeerID(beerID);
                    }
                    PreparedStatement psLinkTable = conn.prepareStatement("INSERT INTO `gedronkenbieren`(`gebruikersid`, `bierid`) VALUES (?,?)");
                    psLinkTable.setInt(1, userID);
                    psLinkTable.setInt(2, beerID);
                    psLinkTable.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return b;
    } // End of addUserBeer



    public int removeBeer(int beerID, int userID)
    {
        int checkIfDeleted = 0;
        try (Connection conn = getConn()) {
            // Delete user's beer in question
            PreparedStatement psLink = conn.prepareStatement("DELETE FROM gedronkenbieren WHERE bierid = ? AND gebruikersid = ?");
            psLink.setInt(1, beerID);
            psLink.setInt(2, userID);
            checkIfDeleted = psLink.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkIfDeleted;
    }


} // End of class
