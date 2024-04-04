package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;

public class AddItemCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        Item validItem = new Item("Bagel", 5);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addItem(validItem);

        assertCommandSuccess(new AddItemCommand(validItem), model,
                String.format(AddItemCommand.MESSAGE_SUCCESS, validItem), expectedModel);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Item duplicateItem = new Item("Croissant", 10);
        model.addItem(duplicateItem);
        AddItemCommand addItemCommand = new AddItemCommand(duplicateItem);

        assertCommandFailure(addItemCommand, model, AddItemCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void equals() {
        Item apple = new Item("Apple", 5);
        Item banana = new Item("Banana", 3);
        AddItemCommand addAppleCommand = new AddItemCommand(apple);
        AddItemCommand addBananaCommand = new AddItemCommand(banana);

        // same object -> returns true
        assertTrue(addAppleCommand.equals(addAppleCommand));

        // same Item -> returns true
        AddItemCommand addAppleCommandCopy = new AddItemCommand(apple);
        assertTrue(addAppleCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addAppleCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleCommand.equals(null));

        // different item -> returns false
        assertFalse(addAppleCommand.equals(addBananaCommand));
    }

}
