package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.User;

public class UserDAO extends BaseDAO{
    public void saveUser(User u)
    {
        try(Connection conn = getConn()){
            Statement s = conn.createStatement();
            s.executeUpdate("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `leeftijd`, `geslacht`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,'Joris','Imkers',30,'M','Boekhouder','Doornik','borisimkers@gmail.com',NULL)");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUser()
    {
        try(Connection conn = getConn()){
            Statement s = conn.createStatement();
            // Dit moet nog aangepast worden om ingelezen waardes van klasse 'User' te gebruiken in plaats van vooringevulde zaken
            s.executeUpdate("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `leeftijd`, `geslacht`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,'Timo','Koninckx',45,'M','Bankier','Antwerpen','timotimo@gmail.com',NULL)");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void saveUsers(ArrayList<User> u)
    {
        try {
            Connection conn = getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // NIET UITVOEREN TENZIJ JE DUMMY WAARDES HIERBOVEN AANPAST
        // Anders zit er een dubbele gebruiker in databank, op zich niet heel erg ma bon
        /*UserDAO udao = new UserDAO();
        udao.saveUser();*/
    }

}
