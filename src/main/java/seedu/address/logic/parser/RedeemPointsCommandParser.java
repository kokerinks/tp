package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POINTS;

import java.util.Optional;

import seedu.address.logic.commands.RedeemPointsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Points;

/**
 * Parses input arguments and creates a new RedeemPointsCommand object.
 */
public class RedeemPointsCommandParser implements Parser<RedeemPointsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RedeemPointsCommand
     * and returns a RedeemPointsCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RedeemPointsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POINTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_POINTS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedeemPointsCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Points points = ParserUtil.parsePoints(argMultimap.getValue(PREFIX_POINTS).get());

        if (points.value <= 0) {
            throw new ParseException(RedeemPointsCommand.MESSAGE_CONSTRAINTS);
        }

        return new RedeemPointsCommand(name, points);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            Optional<String> value = argumentMultimap.getValue(prefix);
            if (!value.isPresent() || value.get().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
