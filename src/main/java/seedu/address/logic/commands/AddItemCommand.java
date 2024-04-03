package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POINTS;
import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
public class AddItemCommand extends Command {

    public static final String COMMAND_WORD = "additem";
    public static final String INVALID_COMMAND_FORMAT = "Invalid command format! ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the catalogue. "
            + "Parameters: "
            + PREFIX_ITEM + "ITEM_NAME "
            + PREFIX_POINTS + "POINTS_PER_UNIT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "Chocolate Cake "
            + PREFIX_POINTS + "50";

    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the catalogue";

    private final Item toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Item}
     */
    public AddItemCommand(Item item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(toAdd.getName())) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && toAdd.equals(((AddItemCommand) other).toAdd));
    }

}
