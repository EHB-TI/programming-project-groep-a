package dao;

import java.sql.Connection;

// Entiteiten krijgen elk aparte klasse om met de database te werken
// Deze gaan allemaal connectie moeten maken met databank, dus een connection object hebben
// Vandaar deze klasse om te extenden
public abstract class BaseDAO {
    private Connection conn;

    public Connection getConn() throws Exception {
        if(conn == null || conn.isClosed()) conn = DatabaseSingleton.getInstance().getConnection();
        return conn;
    }
}
