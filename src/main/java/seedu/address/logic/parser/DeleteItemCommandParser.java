package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;

import seedu.address.logic.commands.DeleteItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteItemCommand object.
 * Input format: {@code delitem i/ITEM_NAME}, where ITEM_NAME is the name of the item to be deleted.
 */
public class DeleteItemCommandParser implements Parser<DeleteItemCommand> {

    @Override
    public DeleteItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM);

        if (!argMultimap.getValue(PREFIX_ITEM).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE));
        }

        String itemName = argMultimap.getValue(PREFIX_ITEM).get();

        return new DeleteItemCommand(itemName);
    }

}

