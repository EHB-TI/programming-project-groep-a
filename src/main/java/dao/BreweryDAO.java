package dao;

import entity.Brewery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BreweryDAO extends BaseDAO {

    //save brewery (only name until database structure is clear)
    public void saveBrewery(Brewery brewery)
    {
        try (Connection connection = getConn()){
          PreparedStatement ps = connection.prepareStatement("INSERT INTO brouwerijen VALUES (NULL, ?, NULL)");
          ps.setString(1, brewery.getNameBrewery());
          ps.executeUpdate();
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //find brewery by name
    public void findBrewery(String name){
        try (Connection connection = getConn()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM brouwerijen WHERE brouwerijnaam = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("brouwerijnaam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}