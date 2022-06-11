package dao;

import entity.Beer;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class UserDAO extends BaseDAO{
    /* FUNCTIONS in order:
    (descriptions not 100 % detailed)

    - saveUser: Takes User object from fields of AddUserScreen and saves them to database
    - findUser: Takes name and/or surname and/or e-mail and returns ArrayList to be displayed in JTable on FindUserScreen
    - deleteUser: Takes ID from user selected in JTable and removes user from database (not from table, could be easily implemented)
    - selectUser: Takes ID from user selected in JTable and returns ArrayList of user's beer to be displayed in CheckUserScreen's JTable
    - addUserBeer: Takes Beer object (without ID) from fields of CheckUserScreen and saves them to database and returns Beer object (with ID) to be added to JTable
    - removeBeer: Takes ID from beer selected in JTable and removes it from the user-beer linking table (not entire database)
     */


    // Pass jframe to display jdialogs
    // Otherwise split in checkDuplicateUser and saveUser to call upon from FindUserScreen and show jdialog from there
    // But then connection has to be opened twice
    public void saveUser(User u, JFrame jframe)
    {
        try(Connection conn = getConn()){
            // Timestamp from SQL server is 2 hours early, so a date variable from Java is created.
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            // Check for duplicates based on name, surname and email, these make a user unique
            // Could also change database to set those columns to unique, but Java has been chosen for practice
            // Also possible with COUNT(*)
            // SQL injection --> PreparedStatement
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
                // Because of date PreparedStatement instead of Statement
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
            // SQL injection --> PreparedStatement
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
            // No SQL injection --> Statement
            String sqlLinkTable = "DELETE FROM gedronkenbieren WHERE gebruikersid = " + userID;
            Statement psLink = conn.createStatement();
            // Returns int amount of beers user had
            checkIfDeleted[0] = psLink.executeUpdate(sqlLinkTable);
            // Delete user
            // No SQL injection --> Statement
            String sqlDelete = "DELETE FROM gebruikers WHERE gebruikersid = " + userID;
            Statement psDelete = conn.createStatement();
            // Returns int amount of rows deleted (should be 1)
            checkIfDeleted[1] = psDelete.executeUpdate(sqlDelete);
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
                // No SQL injection --> Statement
                String sql = "SELECT b.* FROM gebruikers g JOIN gedronkenbieren gb ON g.gebruikersid = gb.gebruikersid JOIN bieren b ON b.bierid = gb.bierid WHERE g.gebruikersid = " + userID + " ORDER BY biernaam, variant";
                Statement ps = conn.createStatement();
                ResultSet rs = ps.executeQuery(sql);

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
        int beerID = -1;
            try (Connection conn = getConn()) {
                // Check if beer already in database
                // SQL injection --> Prepared Statement
                PreparedStatement psCheck = conn.prepareStatement("SELECT bierid FROM bieren WHERE biernaam = ? AND variant = ?");
                psCheck.setString(1, b.getName());
                psCheck.setString(2, b.getVariant());
                ResultSet rs = psCheck.executeQuery();

                // Beer already exists --> get ID from database
                if (rs.next()) {
                    // Add to table gedronkenbieren
                    // Get user id and beer id
                    beerID = rs.getInt("bierid");
                    // Add ID to beer object and thus to hidden id column in jtable (for remove)
                    b.setBeerID(beerID);
                }
                // Beer doesn't exist yet --> add to database and retrieve generated ID
                else {
                    // Add to database
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO `bieren`(`bierid`, `biernaam`, `variant`, `percentage`, `kleur`, `brouwerij`) VALUES (NULL,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, b.getName());
                    ps.setString(2, b.getVariant());
                    ps.setDouble(3, b.getAlcoholPercentage());
                    ps.setString(4, b.getColor());
                    ps.setString(5, b.getBrewery());
                    ps.executeUpdate();
                    // Retrieve ID
                    ResultSet rsID = ps.getGeneratedKeys();
                    if (rsID.next()) {
                        beerID = rsID.getInt(1);
                        // Add ID to beer object and thus to hidden id column in jtable (for remove)
                        b.setBeerID(beerID);
                    }
                }
                // Add to user-beer linking table (gedronkenbieren)
                String sql = "INSERT INTO `gedronkenbieren`(`gebruikersid`, `bierid`) VALUES (" + userID + ", " + beerID + ")";
                Statement psLinkTable = conn.createStatement();
                psLinkTable.executeUpdate(sql);
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
            // No SQL injection --> Statement
            String sql = "DELETE FROM gedronkenbieren WHERE bierid = " + beerID + " AND gebruikersid = " + userID;
            Statement psLink = conn.createStatement();
            checkIfDeleted = psLink.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkIfDeleted;
    }


} // End of class
