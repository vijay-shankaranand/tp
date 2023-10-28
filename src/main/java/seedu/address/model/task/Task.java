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
    public static final String TASK_IS_COMPLETED = "isCompleted";
    public static final String TASK_HAS_NOT_BEEN_COMPLETED = "hasNotBeenCompleted";

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
    public Task(TaskDescription description, Date deadline, Event associatedEvent, boolean isCompleted) {
        this.description = description;
        this.deadline = deadline;
        this.associatedEvent = associatedEvent;
        this.associatedEventName = associatedEvent.getName();
        this.isCompleted = isCompleted;
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

    /**
     * Returns true if the task is due within 3 days.
     */
    public boolean isDueWithinThreeDays() {
        if (isCompleted) {
            return false;
        }
        return this.deadline.isDueWithinThreeDays();
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
        return this.isCompleted ? TASK_IS_COMPLETED : TASK_HAS_NOT_BEEN_COMPLETED;
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
                && description.equals(otherEvent.description);
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
