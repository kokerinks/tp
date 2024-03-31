package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ItemTest {
    @Test
    public void isSameItem_and_Equals() {
        Item base = new Item("item1", 10);
        Item sameNameSamePoints = new Item("item1", 10);
        Item diffNameSamePoints = new Item("item2", 10);
        Item sameNameDiffPoints = new Item("item1", 20);
        Item diffNameDiffPoints = new Item("item2", 20);

        //Tests isSameItem
        assertTrue(base.isSameItem(base));
        assertTrue(base.isSameItem(sameNameSamePoints));
        assertFalse(base.isSameItem(diffNameSamePoints));
        assertTrue(base.isSameItem(sameNameDiffPoints));
        assertFalse(base.isSameItem(diffNameDiffPoints));
        assertFalse(base.isSameItem(null));

        //Tests equals
        assertEquals(base, base);
        assertEquals(base, sameNameSamePoints);
        assertNotEquals(base, diffNameSamePoints);
        assertNotEquals(base, sameNameDiffPoints);
        assertNotEquals(base, (diffNameDiffPoints));
        assertNotEquals(base, null);
    }
}
