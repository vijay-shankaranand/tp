package seedu.address.model.task.exceptions;

public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operatipn would result in duplicate tasks");
    }
}
