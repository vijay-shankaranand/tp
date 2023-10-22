package seedu.address.model.person.exceptions;

import seedu.address.model.person.Name;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PersonNotFoundException extends RuntimeException {
    private final Name name;

    public PersonNotFoundException() {
        super("Index out of bound.");
        this.name = null;
    }

    public PersonNotFoundException(Name name) {
        super("Person not found: " + name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
