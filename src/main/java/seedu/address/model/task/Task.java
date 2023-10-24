package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;

/**
 * Represents a Task in the Event list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskDescription description;
    private final Date deadline;
    private final Event associatedEvent;

    /**
     * Constructs a {@code Task}.
     *
     * @param description A valid description.
     * @param deadline A valid deadline.
     */
    public Task(TaskDescription description, Date deadline, Event associatedEvent) {
        this.description = description;
        this.deadline = deadline;
        this.associatedEvent = associatedEvent;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public Date getDate() {
        return deadline;
    }

    public Event getAssociatedEvent() {
        return associatedEvent;
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Task)) {
            return false;
        }

        Task otherEvent = (Task) other;
        return associatedEvent.equals(otherEvent.associatedEvent)
                && description.equals(otherEvent.description)
                && deadline.equals(otherEvent.deadline);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, deadline, associatedEvent);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .add("deadline", deadline)
                .add("for event", associatedEvent)
                .toString();
    }

}
