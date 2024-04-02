package seedu.address.model.person.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Item;


public class OrderTest {
    @Test
    public void equals() {
        Item item = new Item("test", 20);
        Order order = new Order(item, 10);
        Order orderCopy = new Order(item, 10);
        assertEquals(order, orderCopy);
    }
}
