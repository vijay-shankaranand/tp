package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.SelectEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectEventCommand object.
 */
public class SelectEventCommandParser implements Parser<SelectEventCommand> {
    /**
     * Parses the given {@code args} of arguments in the context of the SelectEventCommand
     * and returns a SelectEventCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SelectEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
