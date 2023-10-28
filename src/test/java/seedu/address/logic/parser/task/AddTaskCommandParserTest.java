package seedu.address.logic.parser.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.task.AddTaskCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.event.TypicalEvents.NTU;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.model.date.Date;
import seedu.address.model.name.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.task.TaskBuilder;

public class AddTaskCommandParserTest {

    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task correctTask = new TaskBuilder(BOOK_VENUE).withEvent(NTU).build();
        AddTaskCommand correctCommand = new AddTaskCommand(correctTask.getDescription(), correctTask.getDate(),
                correctTask.getAssociatedEventName());
        System.out.println(correctCommand);
        assertParseSuccess(parser, " " + PREFIX_NAME
                + BOOK_VENUE.getDescription()
                + " " + PREFIX_DATE + BOOK_VENUE.getDate() + " " + PREFIX_EVENT + NTU.getName(), correctCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing name prefix
        assertParseFailure(parser, " " + BOOK_VENUE.getDescription() + " "
                        + PREFIX_DATE + BOOK_VENUE.getDate() + " " + PREFIX_EVENT + NTU.getName(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // missing date prefix
        assertParseFailure(parser, " " + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + BOOK_VENUE.getDate() + " " + PREFIX_EVENT + NTU.getName(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // missing event prefix
        assertParseFailure(parser, " " + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + PREFIX_DATE + BOOK_VENUE.getDate() + " " + NTU.getName(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedValue_failure() {
        // repeated name prefix
        assertParseFailure(parser, " " + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + PREFIX_DATE + BOOK_VENUE.getDate() + " " + PREFIX_EVENT + NTU.getName(),
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // repeated date prefix
        assertParseFailure(parser, " " + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + PREFIX_DATE + BOOK_VENUE.getDate() + " "
                        + PREFIX_DATE + BOOK_VENUE.getDate() + " " + PREFIX_EVENT + NTU.getName(),
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));
//
        // repeated event prefix
        assertParseFailure(parser, " " + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + PREFIX_DATE + BOOK_VENUE.getDate() + " "
                        + PREFIX_EVENT + NTU.getName() + " " + PREFIX_EVENT + NTU.getName(),
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, " " + PREFIX_NAME + " " + PREFIX_DATE + BOOK_VENUE.getDate() + " "
                        + PREFIX_EVENT + NTU.getName(),
                TaskDescription.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, " " + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + PREFIX_DATE + " " + PREFIX_EVENT + NTU.getName(),
                Date.MESSAGE_CONSTRAINTS);

        // invalid event
        assertParseFailure(parser, " " + PREFIX_NAME + BOOK_VENUE.getDescription() + " "
                        + PREFIX_DATE + BOOK_VENUE.getDate() + " " + PREFIX_EVENT,
                Name.MESSAGE_CONSTRAINTS);
    }
}
