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
            PreparedStatement s = conn.prepareStatement("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `geboortedatum`, `geslacht`, `favobier`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,?,?,?,?,?,?,?,?,?)");
            s.setString(1, u.getName());
            s.setString(2, u.getSurname());
            s.setDate(3, (Date) u.getDOB());
            s.setString(4, u.getGender().toString());
            s.setString(5, u.getFavoriteBeer());
            s.setString(6, u.getProfession());
            s.setString(7, u.getResidence());
            s.setString(8, u.getEmail());
            s.setDate(9, date);
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
