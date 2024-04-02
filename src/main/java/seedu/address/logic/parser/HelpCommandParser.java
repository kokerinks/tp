package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand.
     * @param args the user input string
     * @return the parsed HelpCommand object
     */
    public HelpCommand parse(String args) {
        String[] argsList = args.split(" ");
        String commandType = null;
        for (String arg : argsList) {
            switch (arg) {

            case "--commands":
            case "-c":
                commandType = "commands";
                break;
            default:
                break;
            }
        }
        if (commandType == null) {
            return new HelpCommand();
        } else {
            return new HelpCommand(commandType);
        }
    }
}
