package seedu.address.model.contact.exceptions;

import seedu.address.model.name.Name;

/**
 * Signals that the operation is unable to find the specified contact.
 */
public class ContactNotFoundException extends RuntimeException {
    private final Name name;

    /**
     * Constructs a new PersonNotFoundException for a non-existent contact referred by index.
     */
    public ContactNotFoundException() {
        super("Index out of bound.");
        this.name = null;
    }

    /**
     * Constructs a new PersonNotFoundException for a non-existent contact referred by name.
     */
    public ContactNotFoundException(Name name) {
        super("Contact not found: " + name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
