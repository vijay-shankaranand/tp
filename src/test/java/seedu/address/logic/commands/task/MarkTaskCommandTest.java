package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.task.MarkTaskCommand.MESSAGE_MISSING_TASK;
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
 * Contains integration tests (interaction with the Model) for {@code MarkTaskCommand}.
 */
public class MarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJobFestGo(), new UserPrefs());

    /**
     * Tests when task to be deleted is valid.
     */
    @Test
    public void execute_validTask_success() {
        model.addTask(BOOK_VENUE);
        expectedModel.addTask(BOOK_VENUE);
        MarkTaskCommand command = new MarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        expectedModel.deleteTask(BOOK_VENUE.getDescription(), JOBFEST.getName());
        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_SUCCESS,
                BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Tests when task to be deleted does not exist.
     */
    @Test
    public void execute_taskDoesNotExist_throwsException() {
        model.addTask(ORDER_FOOD);
        MarkTaskCommand command = new MarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
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
        MarkTaskCommand command = new MarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertThrows(CommandException.class, MESSAGE_MISSING_TASK, () -> command.execute(model));
    }

    @Test
    public void equals() {
        MarkTaskCommand firstMarkTaskCommand =
                new MarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        MarkTaskCommand secondMarkTaskCommand =
                new MarkTaskCommand(BOOK_VENUE.getDescription(), NTU.getName());
        MarkTaskCommand thirdMarkTaskCommand =
                new MarkTaskCommand(ORDER_FOOD.getDescription(), JOBFEST.getName());
        MarkTaskCommand fourthMarkTaskCommand =
                new MarkTaskCommand(ORDER_FOOD.getDescription(), NTU.getName());

        // same object -> returns true
        assertTrue(firstMarkTaskCommand.equals(firstMarkTaskCommand));

        // same values -> returns true
        MarkTaskCommand firstMarkTaskCommandCopy =
                new MarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());
        assertTrue(firstMarkTaskCommand.equals(firstMarkTaskCommandCopy));

        // different types -> returns false
        assertFalse(firstMarkTaskCommand.equals(1));

        // null -> returns false
        assertFalse(firstMarkTaskCommand.equals(null));

        // different task description and different event name -> returns false
        assertFalse(firstMarkTaskCommand.equals(secondMarkTaskCommand));

        // same event name but different task description -> returns false
        assertFalse(firstMarkTaskCommand.equals(thirdMarkTaskCommand));

        // same task description but different event name -> returns false
        assertFalse(firstMarkTaskCommand.equals(fourthMarkTaskCommand));
    }

    @Test
    public void toStringMethod() {
        MarkTaskCommand command =
                new MarkTaskCommand(BOOK_VENUE.getDescription(), JOBFEST.getName());

        String expected = MarkTaskCommand.class.getCanonicalName()
                + "{toDelete=" + BOOK_VENUE.getDescription() + ", "
                + "associatedEvent=" + JOBFEST.getName() + "}";

        assertEquals(expected, command.toString());
    }
}
