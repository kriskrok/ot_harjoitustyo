package itemexchange.persistence;

import java.sql.*;

public class Database {
    private String databaseAddress;
    private String dbUser = "root";
    private String dbPassword = "password";
    
    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
        
        initialize();
    }
    
    public Connection getConnection() {        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(databaseAddress, dbUser, dbPassword);
            
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return connection;
    }
    
    private void initialize() {
        String checkConnFor = this.databaseAddress + ";IFEXISTS=TRUE";
        
        try {
            Connection conn = DriverManager.getConnection(checkConnFor, dbUser, dbPassword);
            
            conn.close();
        } catch (SQLException e) {
            initializeDatabase();
            initData();
        }
    }
    
    public void initializeDatabase() {
        try {
            Connection conn = getConnection();
            
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE User(id SERIAL NOT NULL AUTO_INCREMENT, name VARCHAR(255), username VARCHAR(255), password VARCHAR(255), PRIMARY KEY(id));").executeUpdate();
            conn.prepareStatement("DROP TABLE Item IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Item(id SERIAL NOT NULL AUTO_INCREMENT, name VARCHAR(255), description VARCHAR, owner_id INTEGER, PRIMARY KEY(id),  FOREIGN KEY(owner_id) REFERENCES User(id));").executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                    
    }
    
    public void initData() {
        try {
            Connection conn = getConnection();
            
            conn.prepareStatement("INSERT INTO User (name, username, password) VALUES ('Matti', 'Masa', 'matti'), ('Maija', 'Maija', 'maija'), ('Viljonkka', 'Viljonkka', 'viljonkka');").executeLargeUpdate();
            conn.prepareStatement("INSERT INTO ITEM (name, description, owner_id) VALUES ('Mug', 'The sleek look of teakwood will fit in quite nicely in the office. Pick from a 12-, 16- or 20-ounce bottle that will keep a latte steaming for up to twelve hours thanks to stainless steel and triple-walled insulation.', 1),"
                    + "('Holy Grail', 'The Holy Grail is a treasure that serves as an important motif in Arthurian literature. Different traditions describe it as a cup, dish or stone with miraculous powers that provide happiness, eternal youth or sustenance in infinite abundance, often in the custody of the Fisher King. The term \"holy grail\" is often used to denote an elusive object or goal that is sought after for its great significance.', 1),"
                    + " ('Shiny Bauble', 'Shiny Bauble is a level 10 temporary item enhancement. It is a quest reward and sold by NPCs.', 2),"
                    + "('Immovable object', 'Player: \"What happens if the Unstoppable Force hits the Immovable Object?\" GM: \"Tuesday Mainenterance.\"', 2),"
                    + "('Paperclip', 'A paper clip (or sometimes paperclip) is a device used to hold sheets of paper together, usually made of steel wire bent to a looped shape. Most paper clips are variations of the Gem type introduced in the 1890s or earlier, characterized by the almost two full loops made by the wire. Common to paper clips proper is their utilization of torsion and elasticity in the wire, and friction between wire and paper. When a moderate number of sheets are inserted between the two \"tongues\" of the clip, the tongues will be forced apart and cause torsion in the bend of the wire to grip the sheets together.', 3);").executeLargeUpdate();
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
