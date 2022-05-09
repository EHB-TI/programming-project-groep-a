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
            s.executeUpdate("INSERT INTO `gebruikers`(`gebruikersid`, `voornaam`, `achternaam`, `leeftijd`, `geslacht`, `beroep`, `woonplaats`, `email`, `toetreding`) VALUES (NULL,'Joris','Imkers',30,'M','Boekhouder','Doornik','borisimkers@gmail.com',NULL)");
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
        UserDAO udao = new UserDAO();
        udao.saveUser();
    }

}
