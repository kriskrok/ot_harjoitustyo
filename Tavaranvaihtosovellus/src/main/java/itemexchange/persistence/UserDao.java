package itemexchange.persistence;

import java.util.*;
import java.sql.*;
import itemexchange.domain.User;

public class UserDao {
    
    private static final String DELETE = "DELETE FROM User WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM User WHERE id = ?";
    private static final String FIND_BY_CREDENTIALS = "SELECT * FROM USER WHERE username = ? AND password = ?";
    private static final String FIND_ALL = "SELECT * FROM User ORDER BY id";
    private static final String INSERT = "INSERT INTO User (name, username, password) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE User SET name = ?, username = ?, password = ? WHERE id = ?";

    private Database database;
    private ItemDao itemDao;

    public UserDao(Database database) {
        this.database = database;
    }

    public User findById(Integer key) {
        User user = null;
        
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, key);

            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            
            user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setItems(itemDao.findByOwnerId(rs.getInt("id")));

            stmt.close();
            rs.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return user;
    }
    
    public User findByCredentials(String username, String password) {
        User user = null;
        
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_BY_CREDENTIALS);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            
            user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setItems(itemDao.findByOwnerId(rs.getInt("id")));

            stmt.close();
            rs.close();

            connection.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return user;
    }

    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_ALL);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setItems(itemDao.findByOwnerId(rs.getInt("id")));

                users.add(user);
            }

            stmt.close();
            rs.close();

            connection.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    
    public int saveOrUpdate(User object) {
        int rowsAffected = 0;
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = null;

            if (object.getId() != null) {
                stmt = connection.prepareStatement(UPDATE);
                stmt.setInt(4, object.getId());
            } else {
                stmt = connection.prepareStatement(INSERT);
            }

            stmt.setString(1, object.getName());
            stmt.setString(2, object.getUsername());
            stmt.setString(3, object.getPassword());

            rowsAffected = stmt.executeUpdate();
            stmt.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowsAffected;
    }
    
    public void delete(Integer key) {        
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE);

            stmt.setInt(1, key);
            stmt.executeUpdate();

            stmt.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

}
