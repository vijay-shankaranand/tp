package seedu.address.testutil.task;

import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task BOOK_VENUE = new TaskBuilder().withEvent(JOBFEST)
            .withDate("2023-12-10")
            .withDescription("Book venue")
            .build();

    public static final Task ORDER_FOOD = new TaskBuilder().withEvent(NTU)
            .withDate("2023-12-08")
            .withDescription("Order Food")
            .build();

    private TypicalTasks() {}

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(BOOK_VENUE, ORDER_FOOD));
    }
}
