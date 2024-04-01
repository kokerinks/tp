package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ORDER_ITEM_COOKIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_COOKIES;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.orders.Order;

public class AddOrderCommandParserTest {
    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        NameContainsKeywordsPredicate expectedNamePred =
                new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Choo"));

        try {
            AddOrderCommand actualCommand = parser.parse(NAME_DESC_BOB + ORDER_ITEM_COOKIES);
            AddOrderCommand expectedCommand = new AddOrderCommand(expectedNamePred, VALID_ORDER_COOKIES, 1);
            assertEquals(actualCommand, expectedCommand);
        } catch (ParseException pe) {
            fail("Invalid userInput");
        }

    }

    @Test
    public void parse_prefixMissing_fails() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE),
                () -> parser.parse(NAME_DESC_BOB));
    }

    @Test
    public void parse_invalidItemName_fails() {
        assertThrows(ParseException.class, Order.MESSAGE_CONSTRAINTS, () -> parser.parse(NAME_DESC_BOB + " i/"));
    }

    @Test
    public void parse_invalidQuantity_fails() {
        assertThrows(ParseException.class, Order.MESSAGE_INVALID_QUANTITY,
                () -> parser.parse(NAME_DESC_BOB + ORDER_ITEM_COOKIES + " q/-1"));
    }
}
