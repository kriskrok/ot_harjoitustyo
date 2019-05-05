package persistence;

import itemexchange.persistence.Database;
import java.sql.*;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


public class DatabaseTest {
    
    Database database;
    
    @Test
    public void DBConnectionIsNotNull() {
        database = new Database("jdbc:h2:./test");

        assertNotNull(database.getConnection());
    }


    
    /*

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static Connection getDBConnection() {
            Connection dbConnection = null;
            try {
                Class.forName(DB_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
                return dbConnection;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return dbConnection;
    }
    */
}
