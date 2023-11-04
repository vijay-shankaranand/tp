package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the JobFestGo.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class TaskDescription {

    public static final String MESSAGE_CONSTRAINTS = "Task description should only contain "
            + "alphanumeric characters, spaces and any of the following characters: -,.()"
            + "\nIt should not be blank.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}-,.() ]*";


    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param description A valid description.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDescription(String testDescription) {
        return testDescription.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDescription)) {
            return false;
        }

        TaskDescription otherDescription = (TaskDescription) other;
        return value.toLowerCase().equals(otherDescription.value.toLowerCase());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
