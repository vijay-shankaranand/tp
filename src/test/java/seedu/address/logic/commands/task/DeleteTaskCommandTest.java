package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.task.AddTaskCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.commands.task.DeleteTaskCommand.MESSAGE_MISSING_TASK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;
import static seedu.address.testutil.event.TypicalEvents.getTypicalJobFestGo;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;
import static seedu.address.testutil.task.TypicalTasks.ORDER_FOOD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tag.DeleteTagCommand;
import seedu.address.logic.commands.tag.DeleteTagCommandTest;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.task.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJobFestGo(), new UserPrefs());

    /**
     * Tests when task to be deleted is valid.
     */
    @Test
    public void execute_validTask_success() {
        model.addTask(BOOK_VENUE);
        expectedModel.addTask(BOOK_VENUE);
        DeleteTaskCommand command = new DeleteTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        expectedModel.deleteTask(BOOK_VENUE.getDescription(), JOBFEST.getName());
        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS,
                BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Tests when task to be deleted does not exist.
     */
    @Test
    public void execute_taskDoesNotExist_throwsException() {
        model.addTask(ORDER_FOOD);
        DeleteTaskCommand command = new DeleteTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertThrows(CommandException.class, MESSAGE_MISSING_TASK, () -> command.execute(model));
    }

    /**
     * Tests when there is a task with same description as that of the one to be deleted
     * while they are associated to different events.
     */
    @Test
    public void execute_taskWithSameDescriptionButDifferentEvent_throwsException() {
        Task TaskWithSameDescription = new TaskBuilder().withEvent(NTU)
                .withDate("2023-12-10")
                .withDescription("Book venue")
                .build();
        model.addTask(TaskWithSameDescription);
        DeleteTaskCommand command = new DeleteTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertThrows(CommandException.class, MESSAGE_MISSING_TASK, () -> command.execute(model));
    }

    /**
     * Tests to see that DeleteTagCommands with the same tag is the same.
     */
    @Test
    public void equals_sameTag() {
        Tag test = new Tag("vendor");
        DeleteTagCommandTest.ModelStubWithTags testModel = new DeleteTagCommandTest.ModelStubWithTags();
        testModel.addTag(test);
        DeleteTagCommand same1 = new DeleteTagCommand(test);
        DeleteTagCommand same2 = new DeleteTagCommand(test);
        DeleteContactCommand diffCommand = new DeleteContactCommand(INDEX_FIRST);
        assertEquals(same1, same2);
        assertEquals(same1, same1);
        assertFalse(same1.equals(diffCommand));
    }

    @Test
    public void toString_testEqual() {
        DeleteTagCommand test = new DeleteTagCommand(new Tag("vendor"));
        assertEquals(test.toString(), test.toString());
    }
}
