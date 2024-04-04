package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.model.item.exceptions.DuplicateItemException;

public class CatalogueTest {
    @Test
    public void addItem_validItem_true() {
        assertTrue(new Catalogue().addItem(new Item("valid", 20)));
    }

    @Test
    public void addItem_sameItem_false() {
        Catalogue test = new Catalogue();
        test.addItem(new Item("valid", 20));
        assertFalse(test.addItem(new Item("valid", 20)));

        Catalogue expected = new Catalogue();
        expected.addItem(new Item("valid", 20));
        assertEquals(test, expected);
    }

    @Test
    public void delItem() {
        Catalogue actual = new Catalogue();
        actual.addItem(new Item("test", 20));

        assertTrue(actual.removeItem("Not In Cat") == null);

        Catalogue expected = new Catalogue();
        expected.addItem(new Item("test", 20));
        assertEquals(actual, expected);

        assertTrue(actual.removeItem("test") != null);
        Catalogue empty = new Catalogue();
        assertEquals(actual, empty);


    }

    @Test
    public void setItems_catalogueEmptyToFilled_success() {
        Catalogue actual = new Catalogue();
        Catalogue expected = new Catalogue();
        expected.addItem(new Item("Hi", 200));
        actual.setItems(expected);

        assertEquals(actual, expected);
    }

    @Test
    public void setItems_catalogueFilledToEmpty_success() {
        Catalogue actual = new Catalogue();
        Catalogue expected = new Catalogue();
        actual.addItem(new Item("Hi", 200));
        actual.setItems(expected);

        assertEquals(actual, expected);
    }

    @Test
    public void setItems_itemListFilledToEmpty_failure() {
        Catalogue test = new Catalogue();
        List<Item> itemList = FXCollections.observableArrayList();

        //Add two items with same name
        itemList.add(new Item("Hello", 2));
        itemList.add(new Item("Hello", 3));

        assertThrows(DuplicateItemException.class, () -> test.setItems(itemList));
    }


}
