package seedu.address.model.event.exceptions;

import seedu.address.model.contact.Contact;

/**
 * Signals that the required association between contact and event already exists.
 */
public class EventIsAlreadyLinkedToContactException extends RuntimeException {
    private final Contact linkedContact;

    /**
     * Constructs a new EventIsAlreadyLinkedToContactException for an existing linked contact
     * of an event.
     */
    public EventIsAlreadyLinkedToContactException(Contact contact) {
        super("The contact is already linked to the event");
        this.linkedContact = contact;
    }

    public Contact getContact() {
        return this.linkedContact;
    }
}
