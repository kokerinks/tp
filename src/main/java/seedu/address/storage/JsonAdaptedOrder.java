package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;
import seedu.address.model.person.orders.Order;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    private final String itemName;
    private final String itemPoints;
    private final String quantity;
    private final String orderDateTime;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code items} and {@code orderDateTime}.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("itemName") String itemName,
                            @JsonProperty("itemPoints") String itemPoints,
                            @JsonProperty("quantity") String quantity,
                            @JsonProperty("orderDateTime") String orderDateTime) {
        requireNonNull(itemName);
        requireNonNull(itemPoints);
        requireNonNull(quantity);
        requireNonNull(orderDateTime);
        this.itemName = itemName;
        this.itemPoints = itemPoints;
        this.quantity = quantity;
        this.orderDateTime = orderDateTime;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        requireNonNull(source);
        this.itemName = source.item.getName();
        this.itemPoints = String.valueOf(source.item.getPoints());
        this.quantity = String.valueOf(source.quantity);
        this.orderDateTime = source.orderDateTime.toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (!Order.isValidItems(itemName)) {
            throw new IllegalValueException(Order.MESSAGE_CONSTRAINTS);
        }

        try {
            LocalDateTime modelOrderDateTime = LocalDateTime.parse(orderDateTime);
            int modelQuantity = Integer.parseInt(quantity);
            Item modelItem = new Item(itemName, Integer.parseInt(itemPoints));
            return new Order(modelItem, modelQuantity, modelOrderDateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Order.MESSAGE_INVALID_DATETIME);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Order.MESSAGE_CONSTRAINTS);
        }
    }
}
