package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class TaskDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidTaskDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidTaskDescription));
    }

    @Test
    public void isValidTaskDescription() {
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidDescription(null));

        assertFalse(TaskDescription.isValidDescription("")); // empty string
        assertFalse(TaskDescription.isValidDescription(" ")); // spaces only

        assertTrue(TaskDescription.isValidDescription("Do CS2103T tutorial"));
        assertTrue(TaskDescription.isValidDescription("Do CS2103T tutorial 2"));
        assertThrows(ParseException.class, String.format(TaskDescription.MESSAGE_CONSTRAINTS), () ->
                ParserUtil.parseTaskDescription("Do CS2103T tutorial 2!"));
    }

    @Test
    public void equals() {
        TaskDescription taskDescription = new TaskDescription("Do CS2103T tutorial");
        assertTrue(taskDescription.equals(taskDescription));
        assertTrue(taskDescription.equals(new TaskDescription("Do CS2103T tutorial")));
        assertFalse(taskDescription.equals(null));
        assertFalse(taskDescription.equals(5.0f));
        assertFalse(taskDescription.equals(new TaskDescription("Do CS2103T tutorial 2")));
    }
}
