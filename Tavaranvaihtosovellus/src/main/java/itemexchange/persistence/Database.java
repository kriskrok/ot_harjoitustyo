package itemexchange.persistence;

import java.sql.*;

public class Database {
    private String databaseAddress;
    
    //jdbc:h2:tcp://localhost/hello-1 -user sa
    
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress, "root", "password");
    }
    
}
