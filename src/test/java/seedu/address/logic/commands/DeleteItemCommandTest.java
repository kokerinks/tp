package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.item.Catalogue;
public class DeleteItemCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), new Catalogue());
    }

    @Test
    public void execute_validItemName_deleteSuccessful() throws Exception {
        Item validItem = new Item("Bagel", 5);
        model.addItem(validItem);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new Catalogue());
        expectedModel.removeItem(validItem.getName());

        assertCommandSuccess(new DeleteItemCommand(validItem.getName()), model,
                String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS, validItem), expectedModel);
    }

    @Test
    public void execute_invalidItemName_throwsCommandException() {
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand("NonexistentItem");

        assertCommandFailure(deleteItemCommand, model, DeleteItemCommand.MESSAGE_ITEM_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteItemCommand deleteBagelCommand = new DeleteItemCommand("Bagel");
        DeleteItemCommand deleteCroissantCommand = new DeleteItemCommand("Croissant");

        // same object -> returns true
        assertTrue(deleteBagelCommand.equals(deleteBagelCommand));

        // same values -> returns true
        DeleteItemCommand deleteBagelCommandCopy = new DeleteItemCommand("Bagel");
        assertTrue(deleteBagelCommand.equals(deleteBagelCommandCopy));

        // different types -> returns false
        assertFalse(deleteBagelCommand.equals(1));

        // null -> returns false
        assertFalse(deleteBagelCommand.equals(null));

        // different item -> returns false
        assertFalse(deleteBagelCommand.equals(deleteCroissantCommand));
    }
}
