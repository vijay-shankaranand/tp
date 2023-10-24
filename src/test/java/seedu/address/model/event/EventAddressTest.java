package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class EventAddressTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new EventAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> EventAddress.isValidAddress(null));

        // invalid addresses
        assertFalse(EventAddress.isValidAddress("")); // empty string
        assertFalse(EventAddress.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(EventAddress.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(EventAddress.isValidAddress("-")); // one character
        assertTrue(EventAddress.isValidAddress("Leng Inc; 1234 Market St; San Fransico")); // long address
    }

    @Test
    public void equals() {
        EventAddress address = new EventAddress("Valid Address");

        // same values -> returns true
        assertTrue(address.equals(new EventAddress("Valid Address")));

        // same object -> returns true
        assertTrue(address.equals(address));

        // null -> returns false
        assertFalse(address.equals(null));

        // different types -> returns false
        assertFalse(address.equals(5.0f));

        // different values -> returns false
        assertFalse(address.equals(new EventAddress("Other Valid Address")));
    }
}
