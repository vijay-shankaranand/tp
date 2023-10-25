package seedu.address.model.task.exceptions;

/**
 * Represents error when task is already present in the task list
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operatipn would result in duplicate tasks");
    }
}
