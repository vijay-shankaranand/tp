package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.task.UnmarkTaskCommand.MESSAGE_INCOMPLETED_TASK;
import static seedu.address.logic.commands.task.UnmarkTaskCommand.MESSAGE_MISSING_TASK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;
import static seedu.address.testutil.event.TypicalEvents.getTypicalJobFestGo;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;
import static seedu.address.testutil.task.TypicalTasks.ORDER_FOOD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.task.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code UnmarkTaskCommand}.
 */
public class UnmarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJobFestGo(), new UserPrefs());

    /**
     * Tests when task to be deleted is valid.
     */
    @Test
    public void execute_validTask_success() {
        Task markedTask = new TaskBuilder(BOOK_VENUE).withIsCompleted(true).build();
        model.addTask(markedTask);
        expectedModel.addTask(BOOK_VENUE);
        UnmarkTaskCommand command = new UnmarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        String expectedMessage = String.format(UnmarkTaskCommand.MESSAGE_SUCCESS,
                BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Tests when task to be deleted does not exist.
     */
    @Test
    public void execute_taskDoesNotExist_throwsException() {
        Task markedTask = new TaskBuilder(ORDER_FOOD).withIsCompleted(true).build();
        model.addTask(markedTask);
        UnmarkTaskCommand command = new UnmarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
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
                .withIsCompleted(true)
                .build();
        model.addTask(TaskWithSameDescription);
        UnmarkTaskCommand command = new UnmarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertThrows(CommandException.class, MESSAGE_MISSING_TASK, () -> command.execute(model));
    }

    /**
     * Tests when the task has not been marked as completed.
     */
    @Test
    public void execute_taskIsNotCompleted_throwsException() {
        model.addTask(BOOK_VENUE);
        UnmarkTaskCommand command = new UnmarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertThrows(CommandException.class, MESSAGE_INCOMPLETED_TASK, () -> command.execute(model));
    }

    @Test
    public void equals() {
        UnmarkTaskCommand firstUnmarkTaskCommand =
                new UnmarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        UnmarkTaskCommand secondUnmarkTaskCommand =
                new UnmarkTaskCommand(BOOK_VENUE.getDescription(), NTU.getName());
        UnmarkTaskCommand thirdUnmarkTaskCommand =
                new UnmarkTaskCommand(ORDER_FOOD.getDescription(), JOBFEST.getName());
        UnmarkTaskCommand fourthUnmarkTaskCommand =
                new UnmarkTaskCommand(ORDER_FOOD.getDescription(), NTU.getName());

        // same object -> returns true
        assertTrue(firstUnmarkTaskCommand.equals(firstUnmarkTaskCommand));

        // same values -> returns true
        UnmarkTaskCommand firstUnmarkTaskCommandCopy =
                new UnmarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertTrue(firstUnmarkTaskCommand.equals(firstUnmarkTaskCommandCopy));

        // different types -> returns false
        assertFalse(firstUnmarkTaskCommand.equals(1));

        // null -> returns false
        assertFalse(firstUnmarkTaskCommand.equals(null));

        // different task description and different event name -> returns false
        assertFalse(firstUnmarkTaskCommand.equals(secondUnmarkTaskCommand));

        // same event name but different task description -> returns false
        assertFalse(firstUnmarkTaskCommand.equals(thirdUnmarkTaskCommand));

        // same task description but different event name -> returns false
        assertFalse(firstUnmarkTaskCommand.equals(fourthUnmarkTaskCommand));
    }

    @Test
    public void toStringMethod() {
        UnmarkTaskCommand command =
                new UnmarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());

        String expected = UnmarkTaskCommand.class.getCanonicalName()
                + "{toUnmark=" + BOOK_VENUE.getDescription() + ", "
                + "associatedEvent=" + JOBFEST.getName() + "}";

        assertEquals(expected, command.toString());
    }
}
