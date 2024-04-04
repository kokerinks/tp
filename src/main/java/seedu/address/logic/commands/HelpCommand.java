package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String COMMAND_LIST = "Command List " + "\n"
            + AddCommand.COMMAND_WORD + " : Add a member" + "\n"
            + AddMemPointsCommand.COMMAND_WORD + " : Add membership points" + "\n"
            + AddPointsCommand.COMMAND_WORD + " : Add redemption points" + "\n"
            + AddOrderCommand.COMMAND_WORD + " : Add order" + "\n"
            + AddItemCommand.COMMAND_WORD + " : Add item" + "\n"
            + DeleteItemCommand.COMMAND_WORD + " : Delete item" + "\n"
            + RedeemPointsCommand.COMMAND_WORD + " : Redeem points" + "\n"
            + ClearCommand.COMMAND_WORD + " : Clear all members" + "\n"
            + DeleteCommand.COMMAND_WORD + " : Delete a member" + "\n"
            + EditCommand.COMMAND_WORD + " : Edit member details" + "\n"
            + ExitCommand.COMMAND_WORD + " : Exit the program" + "\n"
            + FindCommand.COMMAND_WORD + " : Find members" + "\n"
            + HelpCommand.COMMAND_WORD + " : Access user guide" + "\n"
            + ListCommand.COMMAND_WORD + " : List all members" + "\n"
            + SeedDataCommand.COMMAND_WORD + " : Seed data";

    private String helpType;

    public HelpCommand() {
        this.helpType = "";
    }

    public HelpCommand(String helpType) {
        this.helpType = helpType;
    }

    @Override
    public CommandResult execute(Model model) {
        switch (helpType) {
        case "commands":
            return new CommandResult(COMMAND_LIST);
        default:
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && helpType.equals(((HelpCommand) other).helpType)); // state check
    }
}
