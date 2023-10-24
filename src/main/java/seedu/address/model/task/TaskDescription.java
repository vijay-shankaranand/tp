package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TaskDescription {

    public static final String MESSAGE_CONSTRAINTS = "Task descriptions can take any values, "
            + "and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

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
        return value.equals(otherDescription.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
