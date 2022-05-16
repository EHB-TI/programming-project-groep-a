package dao;

import entity.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EventDAO extends BaseDAO {

    @Override //Establish a connection
    public Connection getConn() throws Exception {
        return super.getConn();
    }

    //TO DO saveEvent
    public void saveEvent()
    {
        try(Connection conn = getConn()){
            //TO DO: prepared statement
            //PreparedStatement ps = conn.prepareStatement("INSERT INTO events ()");
            //Statement stmt here?
            //ResultSet here?
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TO DO findEvent
    public void findEvent(String s) //s can be the title (or an int eventID cast to String?)
    {
        try(Connection conn = getConn()){
            //prepared statement
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM events WHERE title = ?");
            //Statement stmt here?
            //ResultSet here?
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
