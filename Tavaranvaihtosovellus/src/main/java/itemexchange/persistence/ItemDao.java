package itemexchange.persistence;

import itemexchange.domain.Item;
import itemexchange.domain.User;
import java.util.*;
import java.sql.*;

public class ItemDao {
    
    private Database database;
    private UserDao userDao;
    
    public ItemDao(Database database, UserDao userDao) {
        this.database = database;
        this.userDao = userDao;
    }
    
    public Item findOne(Integer key) throws SQLException {
        /*Item(
            id SERIAL NOT NULL AUTO_INCREMENT,
            name VARCHAR(255), owner_id INTEGER,
            PRIMARY KEY(id),
            FOREIGN KEY(owner_id) REFERENCES User(id)) */

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Item WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Item item = new Item();
        
        item.setId(rs.getInt("id"));
        item.setName("name");
        item.setOwner(userDao.findOne(rs.getInt("id")));

        stmt.close();
        rs.close();

        connection.close();
        
        return item;
    }
    
    public List<Item> findAll() throws SQLException {
        /*User(
            id SERIAL NOT NULL AUTO_INCREMENT,
            name VARCHAR(255),
            username VARCHAR(255),
            password VARCHAR(255),
            PRIMARY KEY(id))*/

        List<Item> items = new ArrayList<>();

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM Item");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Item item = new Item();
        
            item.setId(rs.getInt("id"));
            item.setName("name");
            item.setOwner(userDao.findOne(rs.getInt("id")));

            items.add(item);
        }

        stmt.close();
        rs.close();

        connection.close();

        return items;
    }
    
    public User saveOrUpdate(User object) throws SQLException {
        return null;
    }
    
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Item WHERE id = ?");
        
        stmt.setInt(1, key);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
    }
    
    
    
}
