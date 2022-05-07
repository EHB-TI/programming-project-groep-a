package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {

    private Connection connection;
    private static DatabaseSingleton instance = null;

    private DatabaseSingleton()
    {
    }

    public static DatabaseSingleton getInstance()
    {
        if(instance == null) instance = new DatabaseSingleton();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) connection = DriverManager.getConnection("", "", "");
        return connection;
    }
}
