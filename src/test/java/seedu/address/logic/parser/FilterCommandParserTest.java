package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonIsTaggedPredicate;
import seedu.address.model.tag.Tag;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(firstTag);
        tagList.add(secondTag);

        PersonIsTaggedPredicate predicate = new PersonIsTaggedPredicate(tagList);
        FilterCommand expectedFilterCommand = new FilterCommand(tagList, predicate);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "friends owesmoney", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " friends   owesmoney", expectedFilterCommand);

        // include both upper and lower case
        assertParseSuccess(parser, "frieNds owesMoney", expectedFilterCommand);
    }
}
