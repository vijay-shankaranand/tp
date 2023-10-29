package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;

/**
 * Tests that a {@code Task} is linked to the given event.
 */
public class TaskIsInEventPredicate implements Predicate<Task> {
    // Event to check if Person is involved.
    private final Event event;

    public TaskIsInEventPredicate(Event event) {
        this.event = event;
    }

    @Override
    public boolean test(Task task) {
        return event.isSameEvent(task.getAssociatedEvent());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskIsInEventPredicate)) {
            return false;
        }

        TaskIsInEventPredicate otherTaskIsInEventPredicate = (TaskIsInEventPredicate) other;
        return event.equals(otherTaskIsInEventPredicate.event);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("event", event).toString();
    }
}
