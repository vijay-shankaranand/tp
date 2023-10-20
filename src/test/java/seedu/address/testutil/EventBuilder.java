package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventAddress;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class EventBuilder {
    public static final String DEFAULT_EVENT_NAME = "NUS Career Fair 2023";
    public static final String DEFAULT_EVENT_DATE = "2023-12-12";
    public static final String DEFAULT_EVENT_ADDRESS = "311, Clementi Ave 2, #02-25";

    private EventName name;
    private EventDate date;
    private EventAddress address;
    private Set<Person> contacts;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_EVENT_NAME);
        date = new EventDate(DEFAULT_EVENT_DATE);
        address = new EventAddress(DEFAULT_EVENT_ADDRESS);
        Set<Person> contacts = new HashSet<>();
        contacts.add(new PersonBuilder().build());
    }

    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        date = eventToCopy.getDate();
        address = eventToCopy.getAddress();
        contacts = eventToCopy.getContacts();
    }

    public EventBuilder withEventName(String eventName) {
        this.name = new EventName(eventName);
        return this;
    }

    public EventBuilder withEventDate(String eventDate) {
        this.date = new EventDate(eventDate);
        return this;
    }

    public EventBuilder withEventAddress(String eventAddress) {
        this.address = new EventAddress(eventAddress);
        return this;
    }

    public EventBuilder withEventContacts(Person ... contacts) {
        this.contacts = SampleDataUtil.getPersonSet(contacts);
        return this;
    }
    public Event build() {
        return new Event(name, date, address, contacts);
    }


}
