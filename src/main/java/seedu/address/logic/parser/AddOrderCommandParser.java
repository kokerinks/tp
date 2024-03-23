package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QTY;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.orders.Order;

/**
 * Parses input arguments and creates a new AddOrderCommand object
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns a AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_ITEM, PREFIX_QTY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ITEM)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        String trimmedName = argMultimap.getValue(PREFIX_NAME).get().trim();
        String[] nameKeywords = trimmedName.split("\\s+");

        // Item name should not be empty
        String trimmedItemName = argMultimap.getValue(PREFIX_ITEM).get().trim();
        checkArgument(trimmedItemName.isBlank(), Order.MESSAGE_CONSTRAINTS);

        // if quantity not specified, default to 1
        int quantity = argMultimap.getValue(PREFIX_QTY).isPresent()
                ? Integer.parseInt(argMultimap.getValue(PREFIX_QTY).get())
                : 1;

        return new AddOrderCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                trimmedItemName, quantity);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
