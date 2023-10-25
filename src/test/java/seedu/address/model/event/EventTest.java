package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CONTACT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.event.EventBuilder;


public class EventTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null, null, null, null));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getContacts().remove(0));
    }

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(NTU.isSameEvent(NTU));

        // null -> returns false
        assertFalse(NTU.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedEvent = new EventBuilder(NTU).withEventDate(VALID_EVENT_DATE).withEventAddress(VALID_EVENT_ADDRESS)
                .withEventContacts(VALID_EVENT_CONTACT).build();
        assertTrue(NTU.isSameEvent(editedEvent));

        // different name, all other attributes same -> returns false
        editedEvent = new EventBuilder(NTU).withName(VALID_EVENT_NAME).build();
        assertFalse(NTU.isSameEvent(editedEvent));

    }


    @Test
    public void equals() {
        // same values -> returns true
        Event ntuCopy = new EventBuilder(NTU).build();
        assertTrue(NTU.equals(ntuCopy));

        // same object -> returns true
        assertTrue(NTU.equals(NTU));

        // null -> returns false
        assertFalse(NTU.equals(null));

        // different type -> returns false
        assertFalse(NTU.equals(5));

        // different person -> returns false
        assertFalse(NTU.equals(JOBFEST));

        // different name -> returns false
        Event editedEvent = new EventBuilder(NTU).withName(VALID_EVENT_NAME).build();
        assertFalse(NTU.equals(editedEvent));

        // different date -> returns false
        editedEvent = new EventBuilder(NTU).withEventDate(VALID_EVENT_DATE).build();
        assertFalse(NTU.equals(editedEvent));

        // different address -> returns false
        editedEvent = new EventBuilder(NTU).withEventAddress(VALID_EVENT_ADDRESS).build();
        assertFalse(NTU.equals(editedEvent));

        // different contacts -> returns false
        editedEvent = new EventBuilder(NTU).withEventContacts(VALID_EVENT_CONTACT).build();;
        assertFalse(NTU.equals(editedEvent));
    }

    @Test
    public void toStringMethod() {
        String expected = Event.class.getCanonicalName() + "{name=" + NTU.getName() + ", date=" + NTU.getDate()
                + ", address=" + NTU.getAddress() + ", contacts=" + NTU.getContacts() + "}";
        assertEquals(expected, NTU.toString());
    }
}
