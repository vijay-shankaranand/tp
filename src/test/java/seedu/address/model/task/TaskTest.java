package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;
import static seedu.address.testutil.task.TypicalTasks.ORDER_FOOD;

import org.junit.jupiter.api.Test;

import seedu.address.model.name.Name;
import seedu.address.testutil.task.TaskBuilder;


public class TaskTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, null, new Name(null), false));
    }

    @Test
    public void isOverdue() {
        Task notCompletedTask = new TaskBuilder().withDate("2020-01-01").build();
        assertTrue(notCompletedTask.isOverdue());

        Task completedTask = new TaskBuilder().withDate("2020-01-01").withIsCompleted(true).build();
        assertFalse(completedTask.isOverdue());

        Task notCompletedTask2 = new TaskBuilder().withDate("2020-01-01").withIsCompleted(false).build();
        assertTrue(notCompletedTask2.isOverdue());
    }

    @Test
    public void getIsCompletedString() {
        Task notCompletedTask = new TaskBuilder().withDate("2020-01-01").build();
        assertEquals(notCompletedTask.getIsCompletedString(), "hasNotBeenCompleted");

        Task completedTask = new TaskBuilder().withDate("2020-01-01").withIsCompleted(true).build();
        assertEquals(completedTask.getIsCompletedString(), "isCompleted");
    }

    @Test
    public void equals() {
        assertTrue(BOOK_VENUE.equals(BOOK_VENUE));
        assertTrue(BOOK_VENUE.equals(new TaskBuilder(BOOK_VENUE).build()));
        assertFalse(BOOK_VENUE.equals(ORDER_FOOD));
        assertFalse(BOOK_VENUE.equals(null));

        Task editedBookVenue = new TaskBuilder(BOOK_VENUE).withDate("2023-12-11").build();
        assertTrue(BOOK_VENUE.equals(editedBookVenue));

        editedBookVenue = new TaskBuilder(BOOK_VENUE).withDescription("Book venue for event").build();
        assertFalse(BOOK_VENUE.equals(editedBookVenue));

        editedBookVenue = new TaskBuilder(ORDER_FOOD).withEvent(JOBFEST).build();
        assertFalse(BOOK_VENUE.equals(editedBookVenue));
    }

    @Test
    public void toStringMethod() {
        String expected = Task.class.getCanonicalName() + "{description=" + BOOK_VENUE.getDescription() + ", "
                + "deadline=" + BOOK_VENUE.getDate() + ", "
                + "event=" + BOOK_VENUE.getAssociatedEventName() + ", "
                + "isCompleted=" + BOOK_VENUE.isCompleted() + "}";
        assertEquals(expected, BOOK_VENUE.toString());
    }


}
