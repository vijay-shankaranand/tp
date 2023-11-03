package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.event.EventBuilder;

public class EventSortByDateComparatorTest {
    @Test
    public void firstEventDueButSecondEventNotDue_returnsTrue() {
        Event eventA = new EventBuilder().withDate("2020-01-01").build();
        Event eventB = new EventBuilder().withDate("2100-01-01").build();
        EventSortByDateComparator comparator = new EventSortByDateComparator();
        assertTrue(comparator.compare(eventA, eventB) > 0);
    }

    @Test
    public void secondEventDueButFirstEventNotDue_returnsTrue() {
        Event eventA = new EventBuilder().withDate("2100-01-01").build();
        Event eventB = new EventBuilder().withDate("2022-01-01").build();
        EventSortByDateComparator comparator = new EventSortByDateComparator();
        assertTrue(comparator.compare(eventA, eventB) < 0);
    }

    @Test
    public void bothOverdue_returnsTrue() {
        Event eventA = new EventBuilder().withDate("2020-01-02").build();
        Event eventB = new EventBuilder().withDate("2020-01-01").build();
        EventSortByDateComparator comparator = new EventSortByDateComparator();
        assertTrue(comparator.compare(eventA, eventB) > 0);
    }

    @Test
    public void bothNotOverdue_returnsTrue() {
        Event eventA = new EventBuilder().withDate("2100-01-02").build();
        Event eventB = new EventBuilder().withDate("2100-01-01").build();
        EventSortByDateComparator comparator = new EventSortByDateComparator();
        assertTrue(comparator.compare(eventA, eventB) > 0);
    }
}
