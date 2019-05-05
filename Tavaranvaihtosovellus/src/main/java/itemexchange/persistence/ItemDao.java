package itemexchange.persistence;

import itemexchange.domain.Item;
import java.util.*;
import java.sql.*;

public class ItemDao {    
    private static final String DELETE = "DELETE FROM Item WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM Item WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM Item ORDER BY id";
    private static final String INSERT = "INSERT INTO Item (name, description, owner_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Item SET name = ?, description = ?, owner_id = ? WHERE id = ?";
    
    private Database database;
    private UserDao userDao;
    
    public ItemDao(Database database) {
        this.database = database;
    }
    
    public Item findById(Integer key) {
        Item item = null;
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, key);
            
            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setOwnerId(rs.getInt("owner_id"));
            stmt.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }
    
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setOwnerId(rs.getInt("owner_id"));
                items.add(item);
            }
            stmt.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }
    
    public Set<Item> findByOwnerId(Integer key) {
        Set<Item> items = new HashSet<>();
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Item WHERE owner_id = ?");
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setOwnerId(rs.getInt("owner_id"));
                items.add(item);
            }
            stmt.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }
    
    public int saveOrUpdate(Item object) {
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
            stmt.setString(2, object.getDescription());
            stmt.setInt(3, object.getOwnerId());

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
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
}
