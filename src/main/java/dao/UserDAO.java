package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.User;

public class UserDAO extends BaseDAO{

    public void saveUser(User u)
    {
        try(Connection conn = getConn()){
            // De timestamp van de SQL server staat 2 uur vroeger, vandaar wordt een datumvariabele vanuit java aangemaakt.
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `geboortedatum`, `geslacht`, `favobier`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setDate(3, (Date) u.getDOB());
            ps.setString(4, u.getGender().toString());
            ps.setString(5, u.getFavoriteBeer());
            ps.setString(6, u.getProfession());
            ps.setString(7, u.getResidence());
            ps.setString(8, u.getEmail());
            ps.setDate(9, date);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Maybe return resultset and then show them in a JFrame
    // Here a User object is created because this function cannot reach the JTextArea
    // Fix with JTextArea setter
    public User findUser(String s)
    {
        User u = null;
        String name, surname, gender, beer, profession, residence, email;
        Date DOB, joiningDate;

        try(Connection conn = getConn()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `gebruikers` WHERE achternaam = ? OR voornaam = ?");
            // Searching by name is not case-sensitive!
            ps.setString(1, s);
            ps.setString(2, s);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                name = rs.getString("voornaam");
                surname = rs.getString("achternaam");
                DOB = rs.getDate("geboortedatum");
                gender = rs.getString("geslacht");
                beer = rs.getString("favobier");
                profession = rs.getString("beroep");
                residence = rs.getString("woonplaats");
                email = rs.getString("email");
                joiningDate = rs.getDate("toetreding");
                u = new User(name, surname, DOB, gender, beer, profession, residence, email, joiningDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

}
