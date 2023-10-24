package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.date.Date;

/**
 * Represents a Task in the Event list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskDescription description;
    private final Date date;

    /**
     * Constructs a {@code Task}.
     *
     * @param description A valid description.
     * @param date A valid date.
     */
    public Task(TaskDescription description, Date date) {
        this.description = description;
        this.date = date;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
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
        return description.equals(otherEvent.description)
                && date.equals(otherEvent.date);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .add("date", date)
                .toString();
    }

}
