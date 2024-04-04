package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParserTest {

    @Test
    public void parse_validArgs_returnsHelpCommand() throws ParseException {
        HelpCommandParser parser = new HelpCommandParser();
        assertEquals(parser.parse("").getClass(), HelpCommand.class);
        assertEquals(parser.parse(" --commands").getClass(), HelpCommand.class);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        HelpCommandParser parser = new HelpCommandParser();
        assertThrows(ParseException.class, () -> parser.parse("-invalid"));
    }

}
