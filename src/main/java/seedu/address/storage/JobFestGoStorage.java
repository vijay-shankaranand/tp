package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyJobFestGo;

/**
 * Represents a storage for {@link seedu.address.model.JobFestGo}.
 */
public interface JobFestGoStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getJobFestGoFilePath();

    /**
     * Returns JobFestGo data as a {@link ReadOnlyJobFestGo}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyJobFestGo> readJobFestGo() throws DataLoadingException;

    /**
     * @see #getJobFestGoFilePath()
     */
    Optional<ReadOnlyJobFestGo> readJobFestGo(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyJobFestGo} to the storage.
     * @param JobFestGo cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveJobFestGo(ReadOnlyJobFestGo JobFestGo) throws IOException;

    /**
     * @see #saveJobFestGo(ReadOnlyJobFestGo)
     */
    void saveJobFestGo(ReadOnlyJobFestGo JobFestGo, Path filePath) throws IOException;

}
