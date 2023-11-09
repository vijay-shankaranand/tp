package seedu.address.model.tag.exceptions;

/**
 * Represents error when tag is already present in the tag list
 */
public class DuplicateTagException extends RuntimeException {
    public DuplicateTagException() {
        super("Operation would result in duplicate tags");
    }
}
