package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.task.TypicalTasks.getTypicalJobFestGo;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.JobFestGo;
import seedu.address.testutil.contact.TypicalContacts;
import seedu.address.testutil.tag.TypicalTags;

public class JsonSerializableJobFestGoTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableJobFestGoTest");
    private static final Path TYPICAL_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("typicalContactsJobFestGo.json");
    private static final Path INVALID_CONTACT_FILE = TEST_DATA_FOLDER.resolve("invalidContactJobFestGo.json");
    private static final Path DUPLICATE_CONTACT_FILE = TEST_DATA_FOLDER.resolve("duplicateContactJobFestGo.json");
    private static final Path TYPICAL_TAGS_FILE = TEST_DATA_FOLDER.resolve("typicalTagsJobFestGo.json");
    private static final Path INVALID_TAG_FILE = TEST_DATA_FOLDER.resolve("invalidTagJobFestGo.json");
    private static final Path DUPLICATE_TAG_FILE = TEST_DATA_FOLDER.resolve("duplicateTagJobFestGo.json");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksJobFestGo.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskJobFestGo.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskJobFestGo.json");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsJobFestGo.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventJobFestGo.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventJobFestGo.json");

    @Test
    public void toModelType_typicalContactsFile_success() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONTACTS_FILE,
                JsonSerializableJobFestGo.class).get();
        JobFestGo jobFestGoFromFile = dataFromFile.toModelType();
        JobFestGo typicalContactsJobFestGo = TypicalContacts.getTypicalJobFestGo();
        assertEquals(jobFestGoFromFile, typicalContactsJobFestGo);
    }

    @Test
    public void toModelType_invalidContactFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACT_FILE,
                JsonSerializableJobFestGo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACT_FILE,
                JsonSerializableJobFestGo.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableJobFestGo.MESSAGE_DUPLICATE_CONTACT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalTagsFile_success() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(TYPICAL_TAGS_FILE,
                JsonSerializableJobFestGo.class).get();
        JobFestGo jobFestGoFromFile = dataFromFile.toModelType();
        JobFestGo typicalTagsJobFestGo = TypicalTags.getTypicalJobFestGo();
        assertEquals(jobFestGoFromFile, typicalTagsJobFestGo);
    }

    @Test
    public void toModelType_invalidTagFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(INVALID_TAG_FILE,
                JsonSerializableJobFestGo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTag_throwsIllegalValueException() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TAG_FILE,
                JsonSerializableJobFestGo.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableJobFestGo.MESSAGE_DUPLICATE_TAG,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableJobFestGo.class).get();
        JobFestGo jobFestGoFromFile = dataFromFile.toModelType();
        JobFestGo typicalEventsJobFestGo = getTypicalJobFestGo();
        assertEquals(jobFestGoFromFile, typicalEventsJobFestGo);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableJobFestGo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableJobFestGo.class).get();
        JobFestGo jobFestGoFromFile = dataFromFile.toModelType();
        JobFestGo typicalTasksJobFestGo = getTypicalJobFestGo();
        assertEquals(jobFestGoFromFile, typicalTasksJobFestGo);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableJobFestGo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTask_throwsIllegalValueException() throws Exception {
        JsonSerializableJobFestGo dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableJobFestGo.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableJobFestGo.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }




}
