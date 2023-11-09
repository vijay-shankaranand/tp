package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.HOON;
import static seedu.address.testutil.contact.TypicalContacts.IDA;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.JobFestGo;
import seedu.address.model.ReadOnlyJobFestGo;

public class JsonJobFestGoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonJobFestGoStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readJobFestGo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readJobFestGo(null));
    }

    private java.util.Optional<ReadOnlyJobFestGo> readJobFestGo(String filePath) throws Exception {
        return new JsonJobFestGoStorage(Paths.get(filePath)).readJobFestGo(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readJobFestGo("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readJobFestGo("notJsonFormatJobFestGo.json"));
    }

    @Test
    public void readJobFestGo_invalidContactJobFestGo_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readJobFestGo("invalidContactJobFestGo.json"));
    }

    @Test
    public void readJobFestGo_invalidAndValidContactJobFestGo_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readJobFestGo("invalidAndValidContactJobFestGo.json"));
    }

    @Test
    public void readAndSaveJobFestGo_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempJobFestGo.json");
        JobFestGo original = getTypicalJobFestGo();
        JsonJobFestGoStorage jsonJobFestGoStorage = new JsonJobFestGoStorage(filePath);

        // Save in new file and read back
        jsonJobFestGoStorage.saveJobFestGo(original, filePath);
        ReadOnlyJobFestGo readBack = jsonJobFestGoStorage.readJobFestGo(filePath).get();
        assertEquals(original, new JobFestGo(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(HOON);
        original.removeContact(ALICE);
        jsonJobFestGoStorage.saveJobFestGo(original, filePath);
        readBack = jsonJobFestGoStorage.readJobFestGo(filePath).get();
        assertEquals(original, new JobFestGo(readBack));

        // Save and read without specifying file path
        original.addContact(IDA);
        jsonJobFestGoStorage.saveJobFestGo(original); // file path not specified
        readBack = jsonJobFestGoStorage.readJobFestGo().get(); // file path not specified
        assertEquals(original, new JobFestGo(readBack));

    }

    @Test
    public void saveJobFestGo_nullJobFestGo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJobFestGo(null, "SomeFile.json"));
    }

    /**
     * Saves {@code JobFestGo} at the specified {@code filePath}.
     */
    private void saveJobFestGo(ReadOnlyJobFestGo jobFestGo, String filePath) {
        try {
            new JsonJobFestGoStorage(Paths.get(filePath))
                    .saveJobFestGo(jobFestGo, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveJobFestGo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJobFestGo(new JobFestGo(), null));
    }
}
