package domain;

import itemexchange.domain.Item;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {
    Item item;

    @Before
    public void setUp() {
        item = new Item();
        item.setId(1);
        item.setName("Immovable object");
        item.setDescription("Player: \"What happens if the Unstoppable Force hits the Immovable Object?\" GM: \"Tuesday Mainenterance.\"");
        item.setOwnerId(2);
    }

    @Test
    public void idAttributeHoldsCorrectValue() {
        assertEquals(new Integer(1), item.getId());
    }

    @Test
    public void nameAttributeHoldsCorrectValue() {
        assertEquals("Immovable object", item.getName());
    }

    @Test
    public void descriptionHoldsCorrectValue() {
        assertEquals("Player: \"What happens if the Unstoppable Force hits the Immovable Object?\" GM: \"Tuesday Mainenterance.\"", item.getDescription());
    }

    @Test
    public void owner_idAttributeHoldsCorrectValue() {
        assertEquals(new Integer(2), item.getOwnerId());
    }
}
