package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Task} has deadline within 3 days.
 */
public class TaskInReminderPredicate implements Predicate<Task> {

    public TaskInReminderPredicate() {
    }
    @Override
    public boolean test(Task task) {
        return task.isDueWithinThreeDays() || task.isOverdue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskInReminderPredicate)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
