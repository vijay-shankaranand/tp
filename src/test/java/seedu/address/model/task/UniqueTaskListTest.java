package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;
import static seedu.address.testutil.task.TypicalTasks.ORDER_FOOD;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.task.TaskBuilder;

public class UniqueTaskListTest {
    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_nullTaskDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null, null));
    }

    @Test
    public void contains_nullTaskDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null, null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(BOOK_VENUE));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(BOOK_VENUE);
        assertTrue(uniqueTaskList.contains(BOOK_VENUE));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(BOOK_VENUE);
        Task editedBookVenue = new TaskBuilder(BOOK_VENUE).withDate("2024-09-10").build();
        assertTrue(uniqueTaskList.contains(editedBookVenue));
    }

    @Test
    public void contains_taskWithDifferentDateInList_returnsTrue() {
        uniqueTaskList.add(BOOK_VENUE);
        Task editedBookVenue = new TaskBuilder(BOOK_VENUE).withDate("2024-12-11").build();
        assertTrue(uniqueTaskList.contains(editedBookVenue));
    }

    @Test
    public void contains_taskWithDifferentDescriptionInList_returnsFalse() {
        uniqueTaskList.add(BOOK_VENUE);
        Task editedBookVenue = new TaskBuilder(BOOK_VENUE).withDescription("Book venue for event").build();
        assertFalse(uniqueTaskList.contains(editedBookVenue));
    }

    @Test
    public void contains_taskWithDifferentEventInList_returnsFalse() {
        uniqueTaskList.add(BOOK_VENUE);
        Task editedBookVenue = new TaskBuilder(BOOK_VENUE).withEvent(ORDER_FOOD.getAssociatedEvent()).build();
        assertFalse(uniqueTaskList.contains(editedBookVenue));
    }

    @Test
    public void getByValues_taskInList_returnsTrue() {
        uniqueTaskList.add(BOOK_VENUE);
        assertEquals(uniqueTaskList.getByValues(BOOK_VENUE.getDescription(),
                BOOK_VENUE.getDate(), BOOK_VENUE.getAssociatedEvent()), BOOK_VENUE);
    }

    @Test
    public void getByValues_taskNotInList_throwsTaskNotFound() {
        uniqueTaskList.add(ORDER_FOOD);
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.getByValues(BOOK_VENUE.getDescription(),
                        BOOK_VENUE.getDate(), BOOK_VENUE.getAssociatedEvent()));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(BOOK_VENUE);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(BOOK_VENUE));
    }

    @Test
    public void deleteTasksFromEvent_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.deleteTasksFromEvent(null));
    }

    @Test
    public void deleteTasksFromEvent_validSet_success() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.add(ORDER_FOOD);
        uniqueTaskList.deleteTasksFromEvent(Set.of(BOOK_VENUE));
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(ORDER_FOOD);
        assertTrue(uniqueTaskList.equals(expectedUniqueTaskList));
    }

    @Test
    public void deleteTasksFromEvent_invalidSet_throwsTaskNotFoundException() {
        uniqueTaskList.add(ORDER_FOOD);
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.deleteTasksFromEvent(Set.of(BOOK_VENUE,
                ORDER_FOOD)));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, BOOK_VENUE));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(BOOK_VENUE, BOOK_VENUE));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.setTask(BOOK_VENUE, BOOK_VENUE);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(BOOK_VENUE);
        assertTrue(uniqueTaskList.equals(expectedUniqueTaskList));
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(BOOK_VENUE);
        Task editedBookVenue = new TaskBuilder(BOOK_VENUE).build();
        uniqueTaskList.setTask(BOOK_VENUE, editedBookVenue);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedBookVenue);
        assertTrue(uniqueTaskList.equals(expectedUniqueTaskList));
    }
    @Test
    public void setEvent_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.setTask(BOOK_VENUE, ORDER_FOOD);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(ORDER_FOOD);
        assertTrue(uniqueTaskList.equals(expectedUniqueTaskList));
    }

    @Test
    public void setEvent_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.add(ORDER_FOOD);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(BOOK_VENUE, ORDER_FOOD));
    }

    @Test
    public void setTask_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTask_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(BOOK_VENUE);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(ORDER_FOOD);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertTrue(uniqueTaskList.equals(expectedUniqueTaskList));
    }

    @Test
    public void setTask_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_hasDuplicate_throwsDuplicateTaskException() {
        List<Task> listWithDuplicate = List.of(BOOK_VENUE, BOOK_VENUE);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicate));
    }

    @Test
    public void iterator_iterateThroughList_success() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.add(ORDER_FOOD);
        int count = 0;
        for (Task task : uniqueTaskList) {
            count++;
        }
        assertTrue(count == 2);
    }

    @Test
    public void equals_sameList_returnsTrue() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.add(ORDER_FOOD);
        assertTrue(uniqueTaskList.equals(uniqueTaskList));
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        uniqueTaskList.add(BOOK_VENUE);
        assertFalse(BOOK_VENUE.equals(null));
    }

    @Test
    public void hashcode_sameList_returnsTrue() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.add(ORDER_FOOD);
        assertTrue(uniqueTaskList.hashCode() == uniqueTaskList.hashCode());
    }

    @Test
    public void hashcode_differentList_returnsFalse() {
        uniqueTaskList.add(BOOK_VENUE);
        uniqueTaskList.add(ORDER_FOOD);
        UniqueTaskList uniqueTaskList2 = new UniqueTaskList();
        uniqueTaskList2.add(BOOK_VENUE);
        assertFalse(uniqueTaskList.hashCode() == uniqueTaskList2.hashCode());
    }

    @Test
    public void areTasksUnique_duplicateTasks_returnsFalse() {
        List<Task> listWithDuplicate = List.of(BOOK_VENUE, BOOK_VENUE);
        assertFalse(uniqueTaskList.areTasksUnique(listWithDuplicate));
    }

}
