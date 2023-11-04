package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents an Event's date in the JobFestGo.
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
        if (testDate.matches(VALIDATION_REGEX)) {
            return isParsable(testDate);
        } else {
            return false;
        }
    }

    /**
     * Returns true if a given string is parsable.
     */
    public static boolean isParsable(String testDate) {
        try {
            LocalDate.parse(testDate);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given date is today or after today.
     */
    public static boolean isDateTodayOrAfter(Date date) {
        LocalDate today = LocalDate.now();
        LocalDate taskDue = LocalDate.parse(date.date);
        if (taskDue.isBefore(today)) {
            return false;
        }
        return true;
    }

    /**
     * Compares two EventDates by date.
     * @param secondEventDate second EventDate to compare
     * @return -1 if first EventDate is before second EventDate, 0 if equal, 1 if first EventDate is after
     */
    public int compareTo(Date secondEventDate) {
        LocalDate firstDate;
        LocalDate secondDate;

        firstDate = LocalDate.parse(this.date);
        secondDate = LocalDate.parse(secondEventDate.date);

        return firstDate.compareTo(secondDate);
    }

    /**
     * Returns true if the date is within 3 days from today.
     * @return true if date is within 3 days from today, false, otherwise.
     */
    public boolean isWithinThreeDays() {
        LocalDate taskDue = LocalDate.parse(this.date);

        if (taskDue.isBefore(fourDaysFromNow()) && !this.isOverdue()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the date four days after today's date.
     * @return date four days after today's date.
     */
    public LocalDate fourDaysFromNow() {
        LocalDate today = LocalDate.now();
        return today.plusDays(4);
    }

    /**
     * Returns true if the date is before today's date.
     * @return true if date is before today's date, false otherwise.
     */
    public boolean isOverdue() {
        LocalDate today = LocalDate.now();
        LocalDate taskDue = LocalDate.parse(this.date);

        return taskDue.isBefore(today);
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
