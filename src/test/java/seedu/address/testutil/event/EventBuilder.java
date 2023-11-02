package seedu.address.testutil.event;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.address.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.contact.ContactBuilder;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_EVENT_NAME = "NUS Career Fair 2023";
    public static final String DEFAULT_EVENT_DATE = "2023-12-12";
    public static final String DEFAULT_EVENT_ADDRESS = "311, Clementi Ave 2, #02-25";


    private Name name;
    private Address address;
    private Date date;

    private Set<Contact> contacts;
    private Set<Task> tasks;


    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {

        name = new Name(DEFAULT_EVENT_NAME);
        date = new Date(DEFAULT_EVENT_DATE);
        address = new Address(DEFAULT_EVENT_ADDRESS);
        contacts = new HashSet<>();
        tasks = new HashSet<>();
        contacts.add(new ContactBuilder().build());
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
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code EventDate} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String eventDate) {
        this.date = new Date(eventDate);
        return this;
    }

    /**
     * Sets the {@code EventAddress} of the {@code Event} that we are building.
     */
    public EventBuilder withEventAddress(String eventAddress) {
        this.address = new Address(eventAddress);
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Contact>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withEventContacts(Contact... contacts) {
        this.contacts = SampleDataUtil.getContactSet(contacts);
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
