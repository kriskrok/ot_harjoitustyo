package persistence;

import itemexchange.persistence.UserDao;
import itemexchange.persistence.ItemDao;
import itemexchange.persistence.Database;
import itemexchange.domain.User;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {
    Database database;
    UserDao userDao;
    
    @Before
    public void setUp() {
        database = new Database("jdbc:h2:./test");
        database.initializeDatabase();
        database.initData();
        userDao = new UserDao(database);
        userDao.setItemDao(new ItemDao(database));
    }
    

    @Test
    public void savedUserIncreasesUserCount() {
        int usersPrio = userDao.findAll().size();
        
        User user = new User();
        user.setName("a");
        user.setUsername("b");
        user.setPassword("c");
        userDao.saveOrUpdate(user);
        
        int usersAfter = userDao.findAll().size();
        
        assertEquals(1, usersAfter - usersPrio);
    }
    
    @Test
    public void updatingUserUpdatesUserpropertyAccordingly() {
        User user = new User();
        user.setId(1);
        user.setName("a");
        user.setUsername("Perhaps a bit better description than what you could've come up with.");
        user.setPassword("c");
        int rowsAffected = userDao.saveOrUpdate(user);
        
        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void findByCredentialsReturnsCorrectUser() {
        User user = new User();
        user.setName("a");
        user.setUsername("Mighty Beaver");
        user.setPassword("password123");
        
        userDao.saveOrUpdate(user);
        
        User other = userDao.findByCredentials("Mighty Beaver", "password123");
        
        assertEquals("a", other.getName());
    }
    
    @Test
    public void findAllReturnsNonEmptyAccordingly() {
        User user = new User();
        user.setId(1);
        user.setName("abc");
        user.setUsername("def");
        user.setPassword("c");
        userDao.saveOrUpdate(user);
        
        int users = userDao.findAll().size();
        assertTrue(users > 0);
    }
}
