package seedu.address.model.contact;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;

/**
 * Tests that a {@code Person} is linked to the given event.
 */
public class PersonIsInEventPredicate implements Predicate<Person> {
    //Event to check if Person is involved.
    private final Event event;

    public PersonIsInEventPredicate(Event event) {
        this.event = event;
    }

    @Override
    public boolean test(Person person) {
        return event.isLinkedToContact(person);
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
