package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes an item by name from the item catalogue.
 * If the item doesn't exist, an error is displayed.
 * Usage: {@code delitem i/ITEM_NAME}.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "delitem";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the name in the displayed item list.\n"
            + "Parameters: i/ITEM_NAME\n"
            + "Example: " + COMMAND_WORD + " i/donut";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s";
    public static final String MESSAGE_ITEM_NOT_FOUND = "Item not found in the catalogue.";

    private final String itemName;

    public DeleteItemCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasItem(itemName)) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }

        model.removeItem(itemName);
        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, itemName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItemCommand // instanceof handles nulls
                && itemName.equals(((DeleteItemCommand) other).itemName)); // state check
    }
}

