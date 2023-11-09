package seedu.address.logic.parser.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.task.DeleteTaskCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.event.TypicalEvents.NTU;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.model.name.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.task.TaskBuilder;

public class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task correctTask = new TaskBuilder(BOOK_VENUE).withEvent(NTU).build();
        DeleteTaskCommand correctCommand = new DeleteTaskCommand(correctTask.getDescription(),
                correctTask.getAssociatedEventName());
        System.out.println(correctCommand);
        assertParseSuccess(parser,
                " " + PREFIX_TASK_DESCRIPTION + BOOK_VENUE.getDescription()
                        + " " + PREFIX_EVENT + NTU.getName(), correctCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing name prefix
        assertParseFailure(parser, " " + BOOK_VENUE.getDescription() + " "
                        + PREFIX_EVENT + NTU.getName(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // missing event prefix
        assertParseFailure(parser, " " + PREFIX_TASK_DESCRIPTION + BOOK_VENUE.getDescription() + " "
                        + NTU.getName(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedValue_failure() {
        // repeated name prefix
        assertParseFailure(parser, " " + PREFIX_TASK_DESCRIPTION + BOOK_VENUE.getDescription() + " "
                        + PREFIX_TASK_DESCRIPTION + BOOK_VENUE.getDescription() + " "
                        + PREFIX_EVENT + NTU.getName(),
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_DESCRIPTION));

        // repeated event prefix
        assertParseFailure(parser, " " + PREFIX_TASK_DESCRIPTION + BOOK_VENUE.getDescription() + " "
                        + PREFIX_EVENT + NTU.getName() + " " + PREFIX_EVENT + NTU.getName(),
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, " " + PREFIX_TASK_DESCRIPTION + " "
                        + PREFIX_EVENT + NTU.getName(),
                TaskDescription.MESSAGE_CONSTRAINTS);

        // invalid event
        assertParseFailure(parser, " " + PREFIX_TASK_DESCRIPTION + BOOK_VENUE.getDescription() + " "
                        + PREFIX_EVENT,
                Name.MESSAGE_CONSTRAINTS);
    }
}
