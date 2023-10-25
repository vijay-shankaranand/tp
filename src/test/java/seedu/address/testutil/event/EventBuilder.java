package seedu.address.testutil.event;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventAddress;
import seedu.address.model.date.Date;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.person.PersonBuilder;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_EVENT_NAME = "NUS Career Fair 2023";
    public static final String DEFAULT_EVENT_DATE = "2023-12-12";
    public static final String DEFAULT_EVENT_ADDRESS = "311, Clementi Ave 2, #02-25";

    private EventName name;
    private Date date;
    private EventAddress address;
    private Set<Person> contacts;
    private Set<Task> tasks;


    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_EVENT_NAME);
        date = new Date(DEFAULT_EVENT_DATE);
        address = new EventAddress(DEFAULT_EVENT_ADDRESS);
        Set<Person> contacts = new HashSet<>();
        tasks = new HashSet<>();
        contacts.add(new PersonBuilder().build());
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        date = eventToCopy.getDate();
        address = eventToCopy.getAddress();
        contacts = eventToCopy.getContacts();
        tasks = eventToCopy.getTasks();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String eventName) {
        this.name = new EventName(eventName);
        return this;
    }

    /**
     * Sets the {@code EventDate} of the {@code Event} that we are building.
     */
    public EventBuilder withEventDate(String eventDate) {
        this.date = new Date(eventDate);
        return this;
    }

    /**
     * Sets the {@code EventAddress} of the {@code Event} that we are building.
     */
    public EventBuilder withEventAddress(String eventAddress) {
        this.address = new EventAddress(eventAddress);
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Person>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withEventContacts(Person ... contacts) {
        this.contacts = SampleDataUtil.getPersonSet(contacts);
        return this;
    }
    /**
     * Parses the {@code tasks} into a {@code Set<Task>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withEventTasks(Task ... tasks) {
        this.tasks = SampleDataUtil.getTaskSet(tasks);
        return this;
    }

    public Event build() {
        return new Event(name, date, address, contacts, tasks);
    }


}
