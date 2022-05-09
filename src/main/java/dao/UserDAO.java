package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.User;

import javax.management.StandardEmitterMBean;

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
            // Dit moet nog aangepast worden om ingelezen waardes van klasse 'User' te gebruiken in plaats van vooringevulde zaken
            // De tijdzone van de database server staat 2 uur vroeger, wordt nu aangepast via telkens een SQL statement
            // Dit moet ook telkens opnieuw per sessie
            // Misschien vragen of dit voor de server permanent kan gebeuren?
            /* Dit werkt niet...
            Statement timeUpdate = conn.createStatement();
            timeUpdate.executeUpdate("SET time_zone = '+02:00';");*/
            Statement s = conn.createStatement();
            s.executeUpdate("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `leeftijd`, `geslacht`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,'Benjamin','Dewilde',65,'M','Historicus','Merchtem','bdw@gmail.com',NULL)");
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
        UserDAO udao = new UserDAO();
        udao.saveUser();
    }

}
