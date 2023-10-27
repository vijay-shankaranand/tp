package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;

/**
 * Represents a Task in the Event list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskDescription description;
    private final Date deadline;
    private final Event associatedEvent;

    /**
     * To facilitate easy storage of event names in a task
     * inside storage.
     */
    private final Name associatedEventName;
    private final boolean isCompleted;

    /**
     * Constructs a {@code Task}
     *
     * @param description A valid description.
     * @param deadline A valid deadline.
     */
    public Task(TaskDescription description, Date deadline, Event associatedEvent) {
        this.description = description;
        this.deadline = deadline;
        this.associatedEvent = associatedEvent;
        this.associatedEventName = associatedEvent.getName();
        this.isCompleted = false;
    }

    /**
     * Constructs a {@Task task} with an already existing event.
     * This constructor aids in storage operations of tasks.
     * @param description A valid description.
     * @param deadline A valid deadline.
     * @param eventName A valid event name.
     */
    public Task(TaskDescription description, Date deadline, Name eventName, boolean isCompleted) {
        this.description = description;
        this.deadline = deadline;
        this.associatedEvent = null;
        this.associatedEventName = eventName;
        this.isCompleted = isCompleted;
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

    public Name getAssociatedEventName() {
        return this.associatedEventName;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getIsCompletedString() {
        return this.isCompleted ? "isCompleted" : "hasNotBeenCompleted";
    }

    /**
     * Marks the task as completed.
     */
    public void setAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void setAsNotCompleted() {
        this.isCompleted = false;
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

        return associatedEvent.isSameEvent(otherEvent.associatedEvent)
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
                .add("event", associatedEvent.getName())
                .toString();
    }
}
