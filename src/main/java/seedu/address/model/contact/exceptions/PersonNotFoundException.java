package seedu.address.model.contact.exceptions;

import seedu.address.model.name.Name;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PersonNotFoundException extends RuntimeException {
    private final Name name;

    /**
     * Constructs a new PersonNotFoundException for a non-existent person referred by index.
     */
    public PersonNotFoundException() {
        super("Index out of bound.");
        this.name = null;
    }

    /**
     * Constructs a new PersonNotFoundException for a non-existent person referred by name.
     */
    public PersonNotFoundException(Name name) {
        super("Person not found: " + name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
