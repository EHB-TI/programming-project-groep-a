package dao;

import entity.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EventDAO extends BaseDAO {

    @Override //Establish a connection
    public Connection getConn() throws Exception {
        return super.getConn();
    }

    //TO DO saveEvent
    public void saveEvent()
    {
        try(Connection conn = getConn()){
            //TO DO
            Statement stmt = null;
            ResultSet rs = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TO DO findEvent
    public void findEvent(String s) //s can be the title
    {
        try(Connection conn = getConn()){
            //TO DO
            Statement stmt = null;
            ResultSet rs = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
