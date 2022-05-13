package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

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

    public void findUser(String s)
    {
        try(Connection conn = getConn()){
            PreparedStatement ps = conn.prepareStatement("SELECT `voornaam`, `achternaam`, `favobier`, `beroep`, `email`, `toetreding` WHERE achternaam = ? OR voornaam = ?");
            ps.setString(1, s);
            ps.setString(2, s);
            s.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // NIET UITVOEREN TENZIJ JE DUMMY WAARDES HIERONDER AANPAST
        // Anders zit er een dubbele gebruiker in databank, op zich niet heel erg ma bon
        /*UserDAO udao = new UserDAO();
        User u = new User("Max", "Maes", "1991-05-20", "M", "Consultant", "Rochefort", "Brussel", "mmaes@hotmail.be");
        System.out.println(u);
        udao.saveUser(u);*/
    }

}
