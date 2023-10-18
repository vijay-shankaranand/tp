package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventAddress;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String date;
    private final String address;
    private final List<JsonAdaptedPerson> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("date") String date,
                            @JsonProperty("address") String address,
                             @JsonProperty("contacts") List<JsonAdaptedPerson> contacts) {
        this.name = name;
        this.date = date;
        this.address = address;
        if (contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().eventName;
        date = source.getDate().eventDate;
        address = source.getAddress().value;
        contacts.addAll(source.getContacts().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        final List<Person> eventContacts = new ArrayList<>();
        for (JsonAdaptedPerson person : contacts) {
            eventContacts.add(person.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidName(name)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelName = new EventName(name);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidDate(date)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelDate = new EventDate(date);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventAddress.class.getSimpleName()));
        }
        if (!EventAddress.isValidAddress(address)) {
            throw new IllegalValueException(EventAddress.MESSAGE_CONSTRAINTS);
        }
        final EventAddress modelAddress = new EventAddress(address);

        final Set<Person> modelPersons = new HashSet<>(eventContacts);
        return new Event(modelName, modelDate, modelAddress, modelPersons);
    }
}
