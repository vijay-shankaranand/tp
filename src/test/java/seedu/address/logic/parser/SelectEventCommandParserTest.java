package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.SelectEventCommand;

public class SelectEventCommandParserTest {

    private SelectEventCommandParser parser = new SelectEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSelectEventCommand() {
        SelectEventCommand expectedSelectEventCommand = new SelectEventCommand(INDEX_FIRST_EVENT);
        assertParseSuccess(parser, "1", expectedSelectEventCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectEventCommand.MESSAGE_USAGE));
    }
}
