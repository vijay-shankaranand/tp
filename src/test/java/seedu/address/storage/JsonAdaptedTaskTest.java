package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.date.Date;
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskDescription;

public class JsonAdaptedTaskTest {
    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(BOOK_VENUE);
        assertEquals(BOOK_VENUE, task.toModelType());
    }

    @Test
    public void toModelType_nullTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, "2024-10-10", "", "false");
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT,
                TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("", "2024-10-10", "", "false");
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("Book venue", null, "", "false");
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT,
                seedu.address.model.date.Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("Book venue", "2022-13-10", "test", "false");
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullEvent_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("Book venue", "2024-10-10", "", "false");
        assertThrows(IllegalValueException.class, Name.MESSAGE_CONSTRAINTS, task::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("Book venue", "2024-10-10", "NUS Career Fair 2023", null);

        assertThrows(IllegalValueException.class, String.format(MISSING_FIELD_MESSAGE_FORMAT,
                "status"), task::toModelType);
    }

    @Test
    public void toModelTypeForEvent_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(BOOK_VENUE);
        assertEquals(BOOK_VENUE, task.toModelTypeForEvent(JOBFEST));
    }

    @Test
    public void toModelTypeForEvent_nullTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, "2024-10-10", "", "false");
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT,
                TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> task.toModelTypeForEvent(JOBFEST));
    }

    @Test
    public void toModelTypeForEvent_invalidTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("", "2024-10-10", "", "false");
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> task.toModelTypeForEvent(JOBFEST));
    }

    @Test
    public void toModelTypeForEvent_nullDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("Book venue", null, "", "false");
        String expectedMessage = String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT,
                seedu.address.model.date.Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> task.toModelTypeForEvent(JOBFEST));
    }

    @Test
    public void toModelTypeForEvent_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("Book venue", "2022-21-10", "", "false");
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> task.toModelTypeForEvent(JOBFEST));
    }

    @Test
    public void toModelTypeForEvent_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("Book venue", "2024-10-10", "NUS Career Fair 2023", null);

        assertThrows(IllegalValueException.class, String.format(MISSING_FIELD_MESSAGE_FORMAT,
                "status"), () -> task.toModelTypeForEvent(JOBFEST));
    }


}
