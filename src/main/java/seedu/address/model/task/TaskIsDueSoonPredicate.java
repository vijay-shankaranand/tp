package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

public class TaskIsDueSoonPredicate implements Predicate<Task> {

    public TaskIsDueSoonPredicate() {
    }

    @Override
    public boolean test(Task task) {
        return task.isDueSoon();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskIsDueSoonPredicate)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
