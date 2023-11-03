package seedu.address.model.event;

import java.util.Comparator;

import seedu.address.model.date.Date;

/**
 * Compares two events by date.
 */
public class EventSortByDateComparator implements Comparator<Event> {

    /**
     * Compares {@code eventA} and {@code eventB} by date.
     */
    public int compare(Event eventA, Event eventB) {
        Date firstDate = eventA.getDate();
        Date secondDate = eventB.getDate();

        if (firstDate.isOverdue() && !secondDate.isOverdue()) {
            return 1;
        }

        if (!firstDate.isOverdue() && secondDate.isOverdue()) {
            return -1;
        }

        return firstDate.compareTo(secondDate);
    }
}
