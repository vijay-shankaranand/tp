package seedu.address.model.event.exceptions;

import seedu.address.model.name.Name;

/**
 * Represents an error during when event is not found in file.
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Index out of bound");
    }
    public EventNotFoundException(Name event) {
        super("Event not found: " + event);
    }
}
