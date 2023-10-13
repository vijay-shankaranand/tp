package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.model.tag.Tag;

/**
 * Contains testing of the {@code DeleteTagCommandParser}.
 */
public class DeleteTagCommandParserTest {

    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTagCommand() {
        Tag correctTag = new Tag("vendor");
        DeleteTagCommand correctCommand = new DeleteTagCommand(correctTag);
        assertParseSuccess(parser, "delete_tag t/vendor", correctCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }
}
