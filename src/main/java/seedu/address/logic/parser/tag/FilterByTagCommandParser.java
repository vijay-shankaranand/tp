package seedu.address.logic.parser.tag;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.tag.FilterByTagCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.PersonIsTaggedPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterByTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterByTagCommand
     * and returns a FilterByTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterByTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterByTagCommand.MESSAGE_USAGE));
        }

        String[] tagNames = trimmedArgs.split("\\s+");
        Tag[] tags = new Tag[tagNames.length];

        for (int i = 0; i < tagNames.length; i++) {
            tags[i] = ParserUtil.parseTag(tagNames[i]);
        };

        return new FilterByTagCommand(Arrays.asList(tags),
                new PersonIsTaggedPredicate(Arrays.asList(tags)));
    }
}
