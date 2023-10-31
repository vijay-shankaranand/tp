package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.address.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.name.Name;
import seedu.address.model.task.Task;

/**
 * Represents an Event in the JobFestGo.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final Name eventName;

    // Data fields
    private final Address address;
    private final Date eventDate;

    private final Set<Contact> contacts = new HashSet<>();
    private final Set<Task> tasks = new HashSet<>();

    /**
     * Constructs a {@code Event}.
     *
     * @param eventName A valid name.
     * @param eventDate A valid date.
     * @param address A valid address.
     * @param contacts A valid set of contacts.
     */
    public Event(Name eventName, Date eventDate, Address address, Set<Contact> contacts, Set<Task> tasks) {
        requireAllNonNull(eventName, eventDate, address, contacts, tasks);

        this.eventName = eventName;
        this.eventDate = eventDate;
        this.address = address;
        this.contacts.addAll(contacts);
        this.tasks.addAll(tasks);
    }

    public Address getAddress() {
        return address;
    }

    public Name getName() {
        return eventName;
    }

    public Date getDate() {
        return eventDate;
    }


    /**
     * Returns an immutable contacts set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Contact> getContacts() {
        return Collections.unmodifiableSet(contacts);
    }
    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    /**
     * Checks whether this {@code Event} is linked to the given {@code Contact}.
     * @param contact The {@code Contact} to be checked with.
     * @return {@code true} if this {@code Event} is linked to the given {@code Contact}
     *     and {@code false} otherwise.
     */
    public boolean isLinkedToContact(Contact contact) {
        return contacts.stream().anyMatch(contact::isSameContact);
    }

    /**
     * Links the given {@code Contact} to this {@code Event}.
     * @param toLink The {@code Contact} to be linked to this {@code Event}.
     */
    public void linkContact(Contact toLink) {
        contacts.add(toLink);
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && (otherEvent.getName().equals(getName()));
    }

    /**
     * Checks if the event has concluded.
     * @return true if the event date is past, false otherwise.
     */
    public boolean isCompleted() {
        return this.eventDate.isOverdue();
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return eventName.equals(otherEvent.eventName)
                && eventDate.equals(otherEvent.eventDate)
                && address.equals(otherEvent.address)
                && contacts.equals(otherEvent.contacts)
                && tasks.equals(otherEvent.tasks);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventDate, address, contacts, tasks);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", eventName)
                .add("date", eventDate)
                .add("address", address)
                .add("contacts", contacts)
                .add("tasks", tasks)
                .toString();
    }

}
