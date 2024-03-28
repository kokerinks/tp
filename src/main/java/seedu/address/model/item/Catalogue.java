package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.exceptions.DuplicateItemException;

/**
 * A list of items that enforces uniqueness between its elements and does not allow nulls.
 * A item is considered unique by comparing using {@code Item#isSameItem(Item)}. As such, adding and updating of
 * items uses Item#isSameItem(Item) for equality to ensure that the item being added or updated is
 * unique in terms of identity in the Catalogue.
 *
 * Supports a minimal set of list operations.
 *
 * @see Item#isSameItem(Item)
 */
public class Catalogue implements Iterable<Item> {

    private final ObservableList<Item> internalList = FXCollections.observableArrayList();
    private final ObservableList<Item> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public Catalogue() {}

    /**
     * Returns true if the list contains an item with the same name.
     */
    public boolean addItem(Item newItem) {
        for (Item item: internalList) {
            if (item.isSameItem(newItem)) {
                return false;
            }
        }
        internalList.add(newItem);
        return true;
    }

    /**
     * Returns true if the list contains an item with the same name.
     */
    public Item findItem(String name) {
        for (Item item: internalList) {
            if (item.getName().equals(name)) {
                return item.clone();
            }
        }
        return null;
    }

    /**
     * Returns true if the list contains an item with the same name.
     */
    public boolean removeItem(String name) {
        for (Item item: internalList) {
            if (item.getName().equals(name)) {
                return internalList.remove(item);
            }
        }
        return false;
    }

    public void setItems(List<Item> items) {
        if (!itemsAreUnique(items)) {
            throw new DuplicateItemException();
        }
        internalList.setAll(items);
    }

    public void setItems(Catalogue replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    @Override
    public Iterator<Item> iterator() {
        return internalList.iterator();
    }

    public ObservableList<Item> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns true if the list contains only unique items.
     */
    public boolean itemsAreUnique(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).getName().equals(items.get(j).getName())) {
                    return false;
                }
            }
        }
        return true;
    }
}
