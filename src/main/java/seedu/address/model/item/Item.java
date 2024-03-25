package seedu.address.model.item;

/**
 * Represents an Item in the catalogue.
 */
public class Item implements Cloneable {

    public static final String ITEM_NAME_VALIDATION_REGEX = "[^\\s].*";

    private String name;
    private int points;

    /**
     * Creates a new item with the given name and points.
     */
    public Item(String newName, int newPoints) {
        name = newName;
        points = newPoints;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public Item clone() {
        return new Item(name, points);
    }

    public boolean isSameItem(Item otherItem) {
        return otherItem != null && otherItem.getName().equals(name);
    }
    @Override
    public String toString() {
        return name + " (" + points + " points)";
    }
}
