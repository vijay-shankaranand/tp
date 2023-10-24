package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidName(null));

        // invalid name
        assertFalse(EventName.isValidName("")); // empty string
        assertFalse(EventName.isValidName(" ")); // spaces only
        assertFalse(EventName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(EventName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(EventName.isValidName("peter jack")); // alphabets only
        assertTrue(EventName.isValidName("12345")); // numbers only
        assertTrue(EventName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(EventName.isValidName("Capital Tan")); // with capital letters
        assertTrue(EventName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        EventName name = new EventName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new EventName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new EventName("Other Valid Name")));
    }
}
