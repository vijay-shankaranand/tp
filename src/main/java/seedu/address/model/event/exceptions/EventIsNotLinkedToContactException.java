package seedu.address.model.event.exceptions;

import seedu.address.model.contact.Contact;

/**
 * Signals that the association between contact and event does not exist.
 */
public class EventIsNotLinkedToContactException extends RuntimeException {
    private final Contact unlinkedContact;

    /**
     * Constructs a new EventIsNotLinkedToContactException for a currently unlinked contact
     * of an event.
     */
    public EventIsNotLinkedToContactException(Contact contact) {
        super("The contact is not linked to the event currently");
        this.unlinkedContact = contact;
    }

    public Contact getContact() {
        return this.unlinkedContact;
    }
}