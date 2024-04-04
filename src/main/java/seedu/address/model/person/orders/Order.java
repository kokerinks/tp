package seedu.address.model.person.orders;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.item.Item.ITEM_NAME_VALIDATION_REGEX;

import java.time.LocalDateTime;

import seedu.address.model.item.Item;

/**
 * Represents an order made by a Person
 */
public class Order implements Cloneable {

    public static final String MESSAGE_CONSTRAINTS = "Item name should not be blank";
    public static final String MESSAGE_INVALID_QUANTITY = "Quantity should be a positive integer";
    public static final String MESSAGE_INVALID_DATETIME = "Order DateTime is invalid";
    public final Item item;
    public final int quantity;
    public final LocalDateTime orderDateTime;

    /**
     * Constructs an {@code Order}.
     *
     * @param item A valid order
     */
    public Order(Item item, int quantity) {
        requireNonNull(item);
        this.item = item;
        this.quantity = quantity;
        this.orderDateTime = LocalDateTime.now().withNano(0);
    }

    /**
     * Constructs an {@code Order} with a specific {@code LocalDateTime} ordered. Time is rounded down to the second
     *
     * @param item A valid order
     * @param quantity The quantity of this order
     * @param orderDateTime The time of this order
     */
    public Order(Item item, int quantity, LocalDateTime orderDateTime) {
        requireNonNull(item);
        requireNonNull(orderDateTime);
        this.item = item;
        this.quantity = quantity;
        this.orderDateTime = orderDateTime.withNano(0);
        assert(quantity > 0);
    }

    /**
     * Returns true if a given string is a valid item.
     */
    public static boolean isValidItems(String test) {

        return test.matches(ITEM_NAME_VALIDATION_REGEX);
    }

    public String getDateTime() {
        return orderDateTime.toString().replace("T", "\n");
    }

    public String getItemName() {
        return item.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getItemPoints() {
        return item.getPoints();
    }

    @Override
    public String toString() {
        return orderDateTime.toString() + " " + item + " x" + quantity;
    }

    @Override
    public Order clone() {
        return new Order(item, quantity, orderDateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return item.equals(otherOrder.item) && (quantity == otherOrder.quantity)
                && orderDateTime.equals(otherOrder.orderDateTime);
    }
}

