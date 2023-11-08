package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.task.DeleteTaskCommand.MESSAGE_MISSING_TASK;
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
        Task taskWithSameDescription = new TaskBuilder().withEvent(NTU)
                .withDate("2023-12-10")
                .withDescription("Book venue")
                .build();
        model.addTask(taskWithSameDescription);
        DeleteTaskCommand command = new DeleteTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertThrows(CommandException.class, MESSAGE_MISSING_TASK, () -> command.execute(model));
    }

    @Test
    public void equals() {
        DeleteTaskCommand firstDeleteTaskCommand =
                new DeleteTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        DeleteTaskCommand secondDeleteTaskCommand =
                new DeleteTaskCommand(BOOK_VENUE.getDescription(), NTU.getName());
        DeleteTaskCommand thirdDeleteTaskCommand =
                new DeleteTaskCommand(ORDER_FOOD.getDescription(), JOBFEST.getName());
        DeleteTaskCommand fourthDeleteTaskCommand =
                new DeleteTaskCommand(ORDER_FOOD.getDescription(), NTU.getName());

        // same object -> returns true
        assertTrue(firstDeleteTaskCommand.equals(firstDeleteTaskCommand));

        // same values -> returns true
        DeleteTaskCommand firstDeleteTaskCommandCopy =
                new DeleteTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertTrue(firstDeleteTaskCommand.equals(firstDeleteTaskCommandCopy));

        // different types -> returns false
        assertFalse(firstDeleteTaskCommand.equals(1));

        // null -> returns false
        assertFalse(firstDeleteTaskCommand.equals(null));

        // different task description and different event name -> returns false
        assertFalse(firstDeleteTaskCommand.equals(secondDeleteTaskCommand));

        // same event name but different task description -> returns false
        assertFalse(firstDeleteTaskCommand.equals(thirdDeleteTaskCommand));

        // same task description but different event name -> returns false
        assertFalse(firstDeleteTaskCommand.equals(fourthDeleteTaskCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteTaskCommand command =
                new DeleteTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());

        String expected = DeleteTaskCommand.class.getCanonicalName()
                + "{toDelete=" + BOOK_VENUE.getDescription() + ", "
                + "associatedEvent=" + JOBFEST.getName() + "}";

        assertEquals(expected, command.toString());
    }


}
