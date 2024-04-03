package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Catalogue;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Points;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RedeemPointsCommand.
 */
public class RedeemPointsCommandTest {

    private static final String POINTS_TO_REDEEM = "10";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Catalogue());

    @Test
    public void execute_redeemPointsUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Integer newPoints = firstPerson.getPoints().getValue() - Integer.parseInt(POINTS_TO_REDEEM);
        Person editedPerson = new PersonBuilder(firstPerson).withPoints(Integer.toString(newPoints)).build();

        RedeemPointsCommand redeemPointsCommand =
                new RedeemPointsCommand(firstPerson.getName(), new Points(POINTS_TO_REDEEM));

        String expectedMessage = String.format(RedeemPointsCommand.MESSAGE_REDEEMPOINTS_SUCCESS, POINTS_TO_REDEEM,
                firstPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new Catalogue());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(redeemPointsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() {
        Name outOfBoundName = new Name("Nonexistent Name");
        RedeemPointsCommand redeemPointsCommand = new RedeemPointsCommand(outOfBoundName, new Points(POINTS_TO_REDEEM));

        assertCommandFailure(redeemPointsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final Name firstPersonName = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getName();
        final RedeemPointsCommand standardCommand =
                new RedeemPointsCommand(firstPersonName, new Points(POINTS_TO_REDEEM));

        // same values -> returns true
        RedeemPointsCommand commandWithSameValues =
                new RedeemPointsCommand(firstPersonName, new Points(POINTS_TO_REDEEM));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different name -> returns false
        Name secondPersonName = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()).getName();
        assertFalse(standardCommand.equals(new RedeemPointsCommand(secondPersonName, new Points(POINTS_TO_REDEEM))));

        // different points -> returns false
        assertFalse(standardCommand.equals(new RedeemPointsCommand(firstPersonName, new Points("20"))));
    }
}
