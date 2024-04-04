package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand.
     * @param args the user input string
     * @return the parsed HelpCommand object
     */
    public HelpCommand parse(String args) throws ParseException {
        args = args.trim();
        if (args.isEmpty()) {
            return new HelpCommand();
        }
        String[] argsList = args.split(" ");
        String commandType = null;
        for (String arg : argsList) {
            switch (arg) {

            case "--commands":
            case "-c":
                commandType = "commands";
                break;
            default:
                throw new ParseException(String.format(HelpCommand.INVALID_FLAG_MESSAGE, arg) + "\n"
                        + HelpCommand.MESSAGE_USAGE);
            }
        }
        return new HelpCommand(commandType);
    }
}
