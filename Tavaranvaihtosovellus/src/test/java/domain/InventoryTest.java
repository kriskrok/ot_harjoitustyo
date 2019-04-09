package domain;

import itemexchange.domain.*;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InventoryTest {

    static Inventory inventory;
    static User matti;
    static Item item1;
    static Item item2;
    static Item item3;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        inventory = new Inventory();
        matti = new User("Matti", "Masa");
        item1 = new Item("Mug", matti);
        item2 = new Item("Holy Grail", matti);
        item3 = new Item("Shiny Bauble", matti);
        

//        inventory.addItem(item1, matti.getName());
//        inventory.addItem(item2, matti.getName());
//        inventory.addItem(item3, matti.getName());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addItemDoesntAddANullItem() {
        inventory.addItem(null, "Matti");
        assertEquals(0, inventory.getUserItems("Matti").size());
    }
    
    @Test
    public void addItemDoesntAddItemwithEmptyOwner() {
        inventory.addItem(item1, "");
        assertEquals(0, inventory.getUserItems("Matti").size());
    }
    
    @Test
    public void addItemAddsCorrectlyWhenOwnerHasNoPreviousItems() {
        inventory.addItem(item1, "Matti");
        assertEquals(1, inventory.getUserItems("Matti").size());
    }
    
    @Test
    public void addItemAddstoCorrectOwner() {
        inventory.addItem(item1, "Matti");
        inventory.addItem(item2, "Maija");
        assertEquals(1, inventory.getUserItems("Matti").size());
        assertEquals(1, inventory.getUserItems("Maija").size());
    }
    
    @Test
    public void addItemAddsCorrectlyWhenOwnerHasPreviousItems() {
        inventory.addItem(item1, "Matti");
        inventory.addItem(item2, "Matti");
        inventory.addItem(item3, "Matti");
        assertEquals(3, inventory.getUserItems("Matti").size());
    }
    
    @Test
    public void getUsersReturnsCorrectUsers() {
        inventory.addItem(item1, "Matti");
        inventory.addItem(item2, "Maija");
        inventory.addItem(item3, "Viljaana");
        assertEquals(3, inventory.getUsers().size());
    }
    
    @Test
    public void getInventoryReturnsAValidMap() {
        inventory.addItem(item1, "Matti");
        inventory.addItem(item2, "Maija");
        inventory.addItem(item3, "Viljaana");
        
        Map<String, ArrayList<Item>> expected = new HashMap<>();
        expected.put("Matti", new ArrayList<Item>());
        expected.get("Matti").add(item1);
        expected.put("Maija", new ArrayList<Item>());
        expected.get("Maija").add(item2);
        expected.put("Viljaana", new ArrayList<Item>());
        expected.get("Viljaana").add(item3);
        
        assertThat(inventory.getInventory(), is(expected));
        
    }
}
