package seedu.address.model.person.orders;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

/**
 * Represents an order made by a Person
 */
public class Order implements Cloneable {

    public static final String MESSAGE_CONSTRAINTS = "Item name should not be blank";
    public static final String MESSAGE_INVALID_DATETIME = "Order DateTime is invalid";

    public final Item item;
    public final int quantity;
    public final LocalDateTime orderDateTime;

    /**
     * Constructs an {@code Order}.
     *
     * @param items A valid order
     */
    public Order(Item item, int quantity) {
        this.item = requireNonNull(item);
        this.quantity = quantity;
        assert(quantity != 0);
        this.orderDateTime = LocalDateTime.now();
    }

    /**
     * Constructs an {@code Order} with a specific {@code LocalDateTime} ordered.
     *
     * @param items A valid order
     * @param orderDateTime The time of this order
     */
    public Order(Item item, int quantity, LocalDateTime orderDateTime) {
        this.item = requireNonNull(item);
        this.quantity = quantity;
        this.orderDateTime = requireNonNull(orderDateTime);
        assert(quantity != 0);
    }

    /**
     * Returns true if a given string is a valid item.
     */
    public static boolean isValidItems(String test) {
        return test.matches(VALIDATION_REGEX);
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

