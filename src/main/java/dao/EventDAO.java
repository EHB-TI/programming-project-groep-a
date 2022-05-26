package dao;

import entity.Event;

import java.sql.*;

public class EventDAO extends BaseDAO {

    @Override //Establish a connection
    public Connection getConn() throws Exception {
        return super.getConn();
    }

    //save event enkel met titel
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

    //save event met meer velden, om te oefenen
    public void saveEventMoreFields(Event event){
        Connection conn = null;
        try {
            conn = getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO evenementen (titel, datum, organisator, bier) VALUES (?,?,?,?)")) {
            ps.setString(1, event.getTitle());
            ps.setDate(2, Date.valueOf(event.getEventDate()));
            ps.setString(3, String.valueOf(event.getOrganisingBrewery()));
            ps.setString(4, String.valueOf(event.getFeaturedBeer()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //findEvent by title
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

    //findEvent by date, om te oefenen
    public void findEventByDate(Date date){
        Connection conn = null;
        try {
            conn = getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM evenementen WHERE datum = ?")) {
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("titel"));
                System.out.println(rs.getDate("datum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
