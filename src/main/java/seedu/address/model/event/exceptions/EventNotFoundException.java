package seedu.address.model.event.exceptions;

import seedu.address.model.event.EventName;

/**
 * Represents an error during when event is not found in file.
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Index out of bound");
    }
    public EventNotFoundException(EventName event) {
        super("Event not found: " + event);
    }
}
