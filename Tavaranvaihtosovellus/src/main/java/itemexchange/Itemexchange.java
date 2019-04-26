package itemexchange;

import itemexchange.persistence.Database;
import itemexchange.ui.ItemexchangeUi;
import itemexchange.ui.TextInterface;
import java.util.*;
import java.sql.*;
import javafx.application.Application;


public class Itemexchange {

    public static void main(String[] args) throws Exception {
        
        //jdbc:h2:tcp://localhost/hello-1 -user sa
        
        Database database = new Database("jdbc:h2:./database");
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT 1");

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.println("Hei tietokantamaailma!");
        } else {
            System.out.println("Yhteyden muodostaminen epäonnistui.");
        }
        
        connection.close();
        
//        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
//        System.exit(SpringApplication.exit(context, () -> 0));
    }

    private static void initializeDatabase() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:./database", "root", "password");
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE User(id SERIAL NOT NULL AUTO_INCREMENT, name VARCHAR(255), username VARCHAR(255), password VARCHAR(255), PRIMARY KEY(id));").executeUpdate();
            conn.prepareStatement("DROP TABLE Item IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Item(id SERIAL NOT NULL AUTO_INCREMENT, name VARCHAR(255), owner_id INTEGER, PRIMARY KEY(id),  FOREIGN KEY(owner_id) REFERENCES User(id));").executeUpdate();
            
            conn.prepareStatement("INSERT INTO User (name, username, password) VALUES ('Matti', 'Masa', 'matti'), ('Maija', 'Maija', 'maija'), ('Viljonkka', 'Viljonkka', 'viljonkka');").executeLargeUpdate();
            conn.prepareStatement("INSERT INTO ITEM (name, owner_id) VALUES ('Mug', 1), ('Holy Grail', 1), ('Shiny Bauble', 2), ('Immovable object', 2), ('Paperclip', 3);").executeLargeUpdate();
                    
        conn.close();
    }


}