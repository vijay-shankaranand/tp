package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventAddress;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;


public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = "NUS@%";
    private static final String INVALID_DATE = "2023-13-13";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = NTU.getName().toString();
    private static final String VALID_DATE = NTU.getDate().toString();
    private static final String VALID_ADDRESS = NTU.getAddress().toString();
    private static final List<JsonAdaptedPerson> VALID_CONTACTS = NTU.getContacts().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(NTU);
        assertEquals(NTU, event.toModelType());
    }

    @Test
    public void toModelType_invalidEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_DATE, VALID_ADDRESS, VALID_CONTACTS);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_DATE, VALID_ADDRESS, VALID_CONTACTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_DATE, VALID_ADDRESS, VALID_CONTACTS);
        String expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventDate_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, null, VALID_ADDRESS, VALID_CONTACTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventAddress_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_DATE, INVALID_ADDRESS, VALID_CONTACTS);
        String expectedMessage = EventAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventAddress_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_DATE, null, VALID_CONTACTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidContacts_throwsIllegalValueException() {
        List<JsonAdaptedPerson> invalidContacts = new ArrayList<>(VALID_CONTACTS);
        invalidContacts.add(new JsonAdaptedPerson(INVALID_NAME, INVALID_PHONE, INVALID_EMAIL, INVALID_ADDRESS,
                null));
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_DATE, VALID_ADDRESS, invalidContacts);
        assertThrows(IllegalValueException.class, event::toModelType);
    }
}
