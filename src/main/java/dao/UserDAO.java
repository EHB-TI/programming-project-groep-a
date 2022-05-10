package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import entity.User;

import javax.management.StandardEmitterMBean;

public class UserDAO extends BaseDAO{

    public void saveUser(User u)
    {
        try(Connection conn = getConn()){
            // De timestamp is 2 uur te vroeg!
            String insert = "INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `leeftijd`, `geslacht`, `favobier`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,?,?,?,?,?,?,?,?,now())";
            PreparedStatement s = conn.prepareStatement("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `leeftijd`, `geslacht`, `favobier`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,?,?,?,?,?,?,?,?,now())");
            s.setString(1, u.getName());
            s.setString(2, u.getSurname());
            s.setInt(3, u.getAge());
            s.setString(4, u.getGender().toString());
            s.setString(5, u.getFavoriteBeer());
            s.setString(6, u.getProfession());
            s.setString(7, u.getResidence());
            s.setString(8, u.getEmail());
            s.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // NIET UITVOEREN TENZIJ JE DUMMY WAARDES HIERONDER AANPAST
        // Anders zit er een dubbele gebruiker in databank, op zich niet heel erg ma bon
        UserDAO udao = new UserDAO();
        User u = new User("Alexia", "Lheureux", 16, "V", "Vertegenwoordiger", "Gueuze", "Zutendaal", "alexia@hotmail.be");
        System.out.println(u);
        udao.saveUser(u);
    }

}
