package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AddTagCommand.MESSAGE_USAGE;
import static seedu.address.model.tag.Tag.MESSAGE_CONSTRAINTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTags.VENUES;
import static seedu.address.testutil.TypicalTags.CLIENTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class AddTagCommandParserTest {

    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String tagVenues = " " + PREFIX_TAG + CLIENTS;
        Tag expectedTag = new TagBuilder().withTag(CLIENTS.getTagName()).build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + tagVenues,  new AddTagCommand(expectedTag));
    }

    @Test
    public void parse_prefixMissing_failure() {
        assertParseFailure(parser, VENUES.getTagName(), String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_tagNameMissing_failure() {
        assertParseFailure(parser, PREFIX_TAG.getPrefix(), String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_tagNameContainsNonAlphanumericChar_failure() {
        String editedVenues;
    }
}
