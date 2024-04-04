package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.item.Item;

/**
 * JSON-friendly version of {@link Item}.
 */
public class JsonAdaptedItem {

    private final String name;
    private final String points;

    /**
     * Constructs a {@code JsonAdaptedItem} with the given {@code name} and {@code points}.
     */
    public JsonAdaptedItem(@JsonProperty("name") String name, @JsonProperty("points") String points) {
        requireNonNull(name);
        requireNonNull(points);
        this.name = name;
        this.points = points;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        requireNonNull(source);
        name = source.getName();
        points = String.valueOf(source.getPoints());
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     */
    public Item toModelType() {
        return new Item(name, Integer.parseInt(points));
    }
}
