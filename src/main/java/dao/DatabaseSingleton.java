package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseSingleton {

    private Connection connection;
    private static DatabaseSingleton instance = null;

    // Constructor
    private DatabaseSingleton()
    {
    }

    // Instance of singleton
    public static DatabaseSingleton getInstance()
    {
        if(instance == null) instance = new DatabaseSingleton();
        return instance;
    }

    // Work with property file for security
    public static Properties loadPropertiesFile() throws Exception {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/JDBCSettings.properties");
        props.load(fis);
        fis.close();
        return props;
    }

    public Connection getConnection() throws Exception {
        if(connection == null || connection.isClosed())
        {
            Properties prop = loadPropertiesFile();
            String url = prop.getProperty("URL");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
        }
            return connection;
    }
}
