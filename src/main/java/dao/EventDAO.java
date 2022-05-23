package dao;

import entity.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EventDAO extends BaseDAO {

    @Override //Establish a connection
    public Connection getConn() throws Exception {
        return super.getConn();
    }

    //save event
    //HOW TO SAVE LocalDate, Brewery, Beer, Region?
    public void saveEvent(Event event)
    {
        try(Connection conn = getConn()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO evenementen (titel) VALUES (?)");
            stmt.setString(1, event.getTitle());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //findEvent on title
    public void findEventByTitle(String searchTerm)
    {
        //wildcard % doesn't work?
        try(Connection conn = getConn()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM evenementen WHERE titel LIKE ?");
            stmt.setString(1, searchTerm);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("titel"));
            }
            if (!rs.next()){
                System.out.println("Done.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
