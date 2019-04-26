package itemexchange.persistence;

import java.util.*;
import java.sql.*;
import itemexchange.domain.User;

public class UserDao {

    private Database database;
    private ItemDao itemDao;

    public UserDao(Database database, ItemDao itemDao) {
        this.database = database;
        this.itemDao = itemDao;
    }

    public User findOne(Integer key) throws SQLException {
        /*Item(
            id SERIAL NOT NULL AUTO_INCREMENT,
            name VARCHAR(255), owner_id INTEGER,
            PRIMARY KEY(id),
            FOREIGN KEY(owner_id) REFERENCES User(id)) */

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        User user = new User();

        user.setId(rs.getInt("id"));
        user.setName("name");
        user.setUsername("username");
        user.setPassword("password");
        //puuttuu Set<Item> items käsittely itemDao.findById(rs.getInt)

        stmt.close();
        rs.close();

        connection.close();
        
        return user;
    }

    public List<User> findAll() throws SQLException {
        /*User(
            id SERIAL NOT NULL AUTO_INCREMENT,
            name VARCHAR(255),
            username VARCHAR(255),
            password VARCHAR(255),
            PRIMARY KEY(id))*/

        List<User> users = new ArrayList<>();

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name, username, password FROM User");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName("name");
            user.setUsername("username");
            user.setPassword("password");
            //puuttuu Set<Item> items käsittely

            users.add(user);
        }

        stmt.close();
        rs.close();

        connection.close();

        return users;
    }
    
    public User saveOrUpdate(User object) throws SQLException {
        return null;
    }
    
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE id = ?");
        
        stmt.setInt(1, key);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
    }
    

}
