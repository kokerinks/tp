package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    @Test
    public void parse_validArgs_returnsHelpCommand() {
        HelpCommandParser parser = new HelpCommandParser();
        assertEquals(new HelpCommand(), parser.parse(""));
        assertEquals(new HelpCommand(), parser.parse(" "));
        assertEquals(new HelpCommand("commands"), parser.parse(" --commands"));
        assertEquals(new HelpCommand("commands"), parser.parse(" -c"));
    }

}
