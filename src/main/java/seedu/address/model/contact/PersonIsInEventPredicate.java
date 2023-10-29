package seedu.address.model.contact;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;

/**
 * Tests that a {@code Contact} is linked to the given event.
 */
public class PersonIsInEventPredicate implements Predicate<Contact> {
    //Event to check if Contact is involved.
    private final Event event;

    public PersonIsInEventPredicate(Event event) {
        this.event = event;
    }

    @Override
    public boolean test(Contact contact) {
        return event.isLinkedToContact(contact);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonIsInEventPredicate)) {
            return false;
        }

        PersonIsInEventPredicate otherPersonIsInEventPredicate = (PersonIsInEventPredicate) other;
        return event.equals(otherPersonIsInEventPredicate.event);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("event", event).toString();
    }
}
