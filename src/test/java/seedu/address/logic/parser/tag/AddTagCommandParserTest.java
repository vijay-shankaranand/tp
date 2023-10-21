package seedu.address.logic.parser.tag;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.tag.AddTagCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.tag.TypicalTags.CLIENTS;
import static seedu.address.testutil.tag.TypicalTags.VENUES;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.tag.AddTagCommand;
import seedu.address.model.tag.Tag;

public class AddTagCommandParserTest {

    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tag correctTag = new Tag(CLIENTS.getTagName());
        AddTagCommand correctCommand = new AddTagCommand(correctTag);
        assertParseSuccess(parser, "add_tag " + PREFIX_TAG + CLIENTS.getTagName(), correctCommand);
    }

    @Test
    public void parse_prefixMissing_failure() {
        assertParseFailure(parser, VENUES.getTagName(), String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_tagNameMissing_failure() {
        assertParseFailure(parser, PREFIX_TAG.getPrefix(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedTagValue_failure() {
        assertParseFailure(parser, "add_tag " + PREFIX_TAG + CLIENTS + " " + PREFIX_TAG + VENUES,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));
    }
}
