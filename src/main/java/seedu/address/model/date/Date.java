package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents an Event's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be valid and in format YYYY-MM-DD not before today's date.";

    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final String date;

    /**
     * Constructs a {@code EventDate}.
     *
     * @param date A valid EventDate.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String testDate) {
        LocalDate date;
        if (testDate.matches(VALIDATION_REGEX)) {
            try {
                date = LocalDate.parse(testDate);
            } catch (DateTimeException e) {
                return false;
            }
        } else {
            return false;
        }
        return isDateTodayOrAfter(date);
    }

    /**
     * Returns true if a given date is today or after today.
     */
    public static boolean isDateTodayOrAfter(LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date.isBefore(today)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherName = (Date) other;
        return date.equals(otherName.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }


}
