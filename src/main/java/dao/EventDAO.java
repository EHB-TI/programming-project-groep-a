package dao;

import entity.Event;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class EventDAO extends BaseDAO {

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

    //save event met meer velden
    public void saveEventMoreFields(Event event){
        Connection conn = null;
        try {
            conn = getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO evenementen (evenementid, titel, datum, organisator, bier) VALUES (NULL, ?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(2, event.getTitle());
            ps.setDate(3, Date.valueOf(event.getEventDate()));
            ps.setString(4, String.valueOf(event.getOrganisingBrewery()));
            ps.setString(5, String.valueOf(event.getFeaturedBeer()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //?
        try (PreparedStatement ps2 = conn.prepareStatement("INSERT INTO aanwezigen (evenementid, gebruikersid) VALUES (NULL, ?)", Statement.RETURN_GENERATED_KEYS)) {
            for (User v : event.getVisitors()) {
                ps2.setInt(2, v.getUserID());
                ps2.executeUpdate();
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //findEvent by title
    public ArrayList<String> findEventByTitle(String searchTerm) {
        ArrayList<String> eventsFound = new ArrayList<>();
        //wildcard % doesn't work?
        try (Connection conn = getConn()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM evenementen WHERE titel LIKE ?");
            stmt.setString(1, searchTerm);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("titel"));
                eventsFound.add(rs.getString("titel"));
            }
            if (!rs.next()) {
                System.out.println("Done.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventsFound;
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

    //deleteEvent by name
    public void deleteEvent(String title){
        try(Connection conn = getConn()){
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM evenementen WHERE titel = ?");
            stmt.setString(1, title);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
