package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of JobFestGo data in local storage.
 */
public class StorageManager implements Storage {
    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private JobFestGoStorage jobFestGoStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code JobFestGoStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(JobFestGoStorage jobFestGoStorage, UserPrefsStorage userPrefsStorage) {
        this.jobFestGoStorage = jobFestGoStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ JobFestGo methods ==============================

    @Override
    public Path getJobFestGoFilePath() {
        return jobFestGoStorage.getJobFestGoFilePath();
    }

    @Override
    public Optional<ReadOnlyJobFestGo> readJobFestGo() throws DataLoadingException {
        return readJobFestGo(jobFestGoStorage.getJobFestGoFilePath());
    }

    @Override
    public Optional<ReadOnlyJobFestGo> readJobFestGo(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return jobFestGoStorage.readJobFestGo(filePath);
    }

    @Override
    public void saveJobFestGo(ReadOnlyJobFestGo jobFestGo) throws IOException {
        saveJobFestGo(jobFestGo, jobFestGoStorage.getJobFestGoFilePath());
    }

    @Override
    public void saveJobFestGo(ReadOnlyJobFestGo jobFestGo, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        jobFestGoStorage.saveJobFestGo(jobFestGo, filePath);
    }

}
