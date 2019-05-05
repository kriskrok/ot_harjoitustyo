package persistence;

import itemexchange.persistence.ItemDao;
import itemexchange.persistence.Database;
import itemexchange.domain.Item;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemDaoTest {
    Database database;
    ItemDao dao;
    
    @Before
    public void setUp() {
        database = new Database("jdbc:h2:./test");
        database.initializeDatabase();
        database.initData();
        dao = new ItemDao(database);
    }
    
    @Test
    public void savedItemIncreasesItemCount() {
        int itemsPrio = dao.findAll().size();
        
        Item item = new Item();
        item.setName("abc");
        item.setDescription("def");
        item.setOwnerId(1);
        dao.saveOrUpdate(item);
        
        int itemsAfter = dao.findAll().size();
        
        assertEquals(1, itemsAfter - itemsPrio);
    }
    
    @Test
    public void updatingItemUpdatesItempropertyAccordingly() {
        Item item = new Item();
        item.setId(1);
        item.setName("abc");
        item.setDescription("Perhaps a bit better description than what you could've come up with.");
        item.setOwnerId(1);
        dao.saveOrUpdate(item);
        
        Item other = dao.findById(1);
        
        assertEquals(other.getDescription(), "Perhaps a bit better description than what you could've come up with.");
    }
    
    @Test
    public void deletingItemDeletesCorrectItem() {
        Item item = new Item();
        item.setId(1);
        item.setName("abc");
        item.setDescription("Perhaps a bit better description than what you could've come up with.");
        item.setOwnerId(1);
        dao.saveOrUpdate(item);
        
        dao.delete(1);
        
        assertNull(dao.findById(1));
    }
    
    @Test
    public void findByOwnerIdReturnsNonEmptyAccordingly() {
        Item item = new Item();
        item.setId(11);
        item.setName("abc");
        item.setDescription("ghi");
        item.setOwnerId(1);
        dao.saveOrUpdate(item);
        
        int itemsAfter = dao.findByOwnerId(1).size();
        assertTrue(itemsAfter > 0);
    }

}
