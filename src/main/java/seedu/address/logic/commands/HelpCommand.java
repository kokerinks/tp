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
            + "Add a member: " + AddCommand.COMMAND_WORD + "\n"
            + "Add membership points: " + AddMemPointsCommand.COMMAND_WORD + "\n"
            + "Add redemption points:  " + AddPointsCommand.COMMAND_WORD + "\n"
            + "Add order: " + AddOrderCommand.COMMAND_WORD + "\n"
            + "Clear all members: " + ClearCommand.COMMAND_WORD + "\n"
            + "Delete a member: " + DeleteCommand.COMMAND_WORD + "\n"
            + "Edit member details: " + EditCommand.COMMAND_WORD + "\n"
            + "Exit the program: " + ExitCommand.COMMAND_WORD + "\n"
            + "Find members: " + FindCommand.COMMAND_WORD + "\n"
            + "Access user guide: " + HelpCommand.COMMAND_WORD + "\n"
            + "List all members: " + ListCommand.COMMAND_WORD + "\n"
            + "Seed data: " + SeedDataCommand.COMMAND_WORD;

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
