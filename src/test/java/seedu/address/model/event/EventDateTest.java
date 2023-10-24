package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class EventDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EventDate(invalidDate));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventDate.isValidDate(null));

        // invalid name
        assertFalse(EventDate.isValidDate("")); // empty string
        assertFalse(EventDate.isValidDate(" ")); // spaces only
        assertFalse(EventDate.isValidDate("2023-02-11")); // before curr date
        assertFalse(EventDate.isValidDate("2023-23-22")); // invalid month
        assertFalse(EventDate.isValidDate("2023-12-42")); // invalid day

        // valid name
        assertTrue(EventDate.isValidDate("2023-12-22")); // alphabets only

    }

    @Test
    public void equals() {
        EventDate date = new EventDate("2023-12-30");

        // same values -> returns true
        assertTrue(date.equals(new EventDate("2023-12-30")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new EventDate("2023-12-29")));
    }
}
