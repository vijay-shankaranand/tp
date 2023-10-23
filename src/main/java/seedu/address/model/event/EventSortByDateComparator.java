package seedu.address.model.event;

import java.util.Comparator;

/**
 * Compares two events by date.
 */
public class EventSortByDateComparator implements Comparator<Event> {
    
    /**
     * Compares {@code eventA} and {@code eventB} by date.
     */
    public int compare(Event eventA, Event eventB) {
        
        EventDate firstDate = eventA.getDate();
        EventDate secondDate = eventB.getDate();

        return firstDate.compareTo(secondDate);
    }
}
