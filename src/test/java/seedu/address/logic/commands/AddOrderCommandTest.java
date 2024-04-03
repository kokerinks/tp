package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddOrderCommand.MESSAGE_ITEM_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.CatalogueTest;
import seedu.address.model.item.Item;
import seedu.address.model.item.Catalogue;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddOrderCommandTest {

    @Test
    public void constructor_nullNameContainsKeywordsPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddOrderCommand(null, "itemName", 1));
    }

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddOrderCommand(new NameContainsKeywordsPredicate(Collections.singletonList("test")), null, 0));
    }

    @Test
    public void execute_personFoundInFilteredPersonList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Catalogue());

        String itemName = "Kaya Toast";
        int points = 200;

        model.addItem(new Item(itemName, points));

        List<String> predicate = Collections.singletonList("benson");

        NameContainsKeywordsPredicate bensonPredicate = new NameContainsKeywordsPredicate(predicate);
        int quantity = 3;
        LocalDateTime orderDateTime = LocalDateTime.parse("2024-01-01T07:00:00");

        AddOrderCommand addOrderToSecondTypicalPerson =
                new AddOrderCommand(bensonPredicate, itemName, quantity, orderDateTime);

        Person bensonWithAddedOrder = new PersonBuilder(BENSON).withOrders(
                // existing orders
                "Cookies|100|5|2012-03-21T07:45:01",
                "Cupcake|150|2|2014-12-11T13:01:02",
                // new order
                String.format("%s|%d|%d|%s", itemName, points, quantity, orderDateTime))
                // existing 10 points + 200 * 3 quantity
                .withMembershipPoints("610")
                .withPoints("670").build();

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Catalogue());
        expectedModel.addItem(new Item(itemName, points));
        expectedModel.setPerson(BENSON, bensonWithAddedOrder);

        assertCommandSuccess(addOrderToSecondTypicalPerson, model,
                "Added order to Person: Benson Meier", expectedModel);
    }

    @Test
    public void execute_personNotFoundInFilteredPersonList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Catalogue());

        List<String> predicate = Collections.singletonList("nobody");

        NameContainsKeywordsPredicate nobodyPredicate = new NameContainsKeywordsPredicate(predicate);
        String itemName = "Kaya Toast";
        int quantity = 1;
        LocalDateTime orderDateTime = LocalDateTime.parse("2024-01-01T07:00:00");

        AddOrderCommand addOrderToSecondTypicalPerson =
                new AddOrderCommand(nobodyPredicate, itemName, quantity, orderDateTime);

        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_NOT_FOUND, () ->
                addOrderToSecondTypicalPerson.execute(model));
    }

    @Test
    public void execute_itemNotInAddressBook_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Catalogue());

        List<String> predicate = Collections.singletonList("alice");

        NameContainsKeywordsPredicate alicePredicate = new NameContainsKeywordsPredicate(predicate);
        String itemName = "Non-existent Item";
        int quantity = 1;
        LocalDateTime orderDateTime = LocalDateTime.parse("2024-01-01T07:00:00");

        AddOrderCommand addOrderToSecondTypicalPerson =
                new AddOrderCommand(alicePredicate, itemName, quantity, orderDateTime);

        assertThrows(CommandException.class, MESSAGE_ITEM_NOT_FOUND, () ->
                addOrderToSecondTypicalPerson.execute(model));
    }

    @Test
    public void generateSuccessMessage_properPersonPassedIn_success() {
        Person personPassedIn = ALICE;
        String expectedMessage = "Added order to Person: Alice Pauline";
        assertEquals(AddOrderCommand.generateSuccessMessage(personPassedIn), expectedMessage);
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate johnny1 = new NameContainsKeywordsPredicate(Collections.singletonList("Johnny"));
        NameContainsKeywordsPredicate johnny2 = new NameContainsKeywordsPredicate(Collections.singletonList("Johnny"));
        NameContainsKeywordsPredicate walker = new NameContainsKeywordsPredicate(Collections.singletonList("Walker"));

        String itemName1 = "Kaya Toast";
        int quantity1 = 1;
        LocalDateTime orderDateTime1 = LocalDateTime.parse("2024-01-01T07:00:00");

        String itemName2 = "Kaya Toast";
        int quantity2 = 1;
        LocalDateTime orderDateTime2 = LocalDateTime.parse("2024-01-01T07:00:00");

        String itemName3 = "Cookies";
        int quantity3 = 5;
        LocalDateTime orderDateTime3 = LocalDateTime.parse("2012-03-21T07:45:01");
        AddOrderCommand addJohnnyKayatoastCommand1 = new AddOrderCommand(johnny1, itemName1, quantity1, orderDateTime1);
        AddOrderCommand addJohnnyKayatoastCommand2 = new AddOrderCommand(johnny2, itemName2, quantity2, orderDateTime2);
        AddOrderCommand addJohnnyCookiesCommand = new AddOrderCommand(johnny1, itemName3, quantity3, orderDateTime3);
        AddOrderCommand addWalkerCookiesCommand = new AddOrderCommand(walker, itemName3, quantity3, orderDateTime3);

        // same object -> returns true
        assertTrue(addJohnnyKayatoastCommand1.equals(addJohnnyKayatoastCommand1));

        // same items and orderDateTime -> returns true
        assertTrue(addJohnnyKayatoastCommand1.equals(addJohnnyKayatoastCommand2));

        // different types -> returns false
        assertFalse(addJohnnyKayatoastCommand1.equals(1));

        // null -> returns false
        assertFalse(addJohnnyKayatoastCommand1.equals(null));

        // different personNamePredicate -> returns false
        assertFalse(addJohnnyCookiesCommand.equals(addWalkerCookiesCommand));

        // different items -> returns false
        assertFalse(addJohnnyKayatoastCommand1.equals(addJohnnyCookiesCommand));
    }

}
