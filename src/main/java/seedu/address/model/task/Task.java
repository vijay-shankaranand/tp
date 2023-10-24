package seedu.address.model.task;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

/**
 * Represents a Task in the Event list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskDescription description;
    private final Date deadline;
    private final Event associatedEvent;

//    /**
//     * To facilitate easy storage of event names in a task
//     * inside storage.
//     */
//    private final EventName associatedEventName;

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
//        this.associatedEventName = associatedEvent.getName();
    }

//    /**
//     * Constructs a {@Task task} with an already existing event.
//     * This constructor aids in storage operations of tasks.
//     * @param description A valid description.
//     * @param deadline A valid deadline.
//     * @param eventName A valid event name.
//     */
//    public Task(TaskDescription description, Date deadline, EventName eventName) {
//        this.description = description;
//        this.deadline = deadline;
//        this.associatedEvent = null;
//        this.associatedEventName = eventName;
//    }

    public TaskDescription getDescription() {
        return description;
    }

    public Date getDate() {
        return deadline;
    }

    public Event getAssociatedEvent() {
        return associatedEvent;
    }

    public EventName getAssociatedEventName() {
        return this.associatedEvent.getName();
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
        System.out.println("desc : " + description.equals(otherEvent.description));
        System.out.println("dead: " + deadline.equals(otherEvent.deadline));
        System.out.println("event: " + associatedEvent.getName().equals(otherEvent.associatedEvent.getName()) + "\n");
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
