package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.orders.Order;

/**
 * Changes the order of an existing person in the address book.
 */
public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an order to the order list of the person identified by the given Name.\n"
            + "Parameters: n/MEMBER_NAME i/ITEM_NAME [q/QUANTITY]\n"
            + "Example: " + COMMAND_WORD + " n/Alex Yeoh i/Chocolate Chip Cookies 100g q/3";

    public static final String MESSAGE_ADD_ORDER_SUCCESS = "Added order to Person: %1$s";

    public static final String MESSAGE_ITEM_NOT_FOUND = "Item not found in the inventory";

    public final NameContainsKeywordsPredicate personNamePredicate;
    public final String itemName;
    public final int quantity;

    /**
     * @param personNamePredicate of the person to add the order to
     * @param itemName name of item ordered
     * @param quantity of specified item ordered
     */
    public AddOrderCommand(NameContainsKeywordsPredicate personNamePredicate, String itemName, int quantity) {
        requireAllNonNull(personNamePredicate, itemName, quantity);

        this.personNamePredicate = personNamePredicate;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Find first person in filtered list matching the personNamePredicate
        Person personToUpdate = null;
        for (Person person : lastShownList) {
            if (!personNamePredicate.test(person)) {
                continue;
            } else {
                personToUpdate = person;
                break;
            }
        }
        if (isNull(personToUpdate)) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        //Find matching item in catalogue
        AddressBook addressBook = (AddressBook) model.getAddressBook();
        Item item = addressBook.findItem(itemName);
        if (isNull(item)) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }

        personToUpdate.addOrders(new Order(item, quantity));
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(personToUpdate));
    }

    /**
     * Generates a command execution success message based on whether
     * the order is added to or removed from {@code personToUpdate}.
     */
    static String generateSuccessMessage(Person personToUpdate) {
        return String.format(MESSAGE_ADD_ORDER_SUCCESS, personToUpdate.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOrderCommand)) {
            return false;
        }

        AddOrderCommand e = (AddOrderCommand) other;
        return personNamePredicate.equals(e.personNamePredicate)
                && itemName.equals(e.itemName) && (quantity == e.quantity);
    }
}
