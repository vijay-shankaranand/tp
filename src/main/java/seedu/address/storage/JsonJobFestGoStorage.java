package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyJobFestGo;

/**
 * A class to access JobFestGo data stored as a json file on the hard disk.
 */
public class JsonJobFestGoStorage implements JobFestGoStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonJobFestGoStorage.class);

    private Path filePath;

    public JsonJobFestGoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getJobFestGoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyJobFestGo> readJobFestGo() throws DataLoadingException {
        return readJobFestGo(filePath);
    }

    /**
     * Similar to {@link #readJobFestGo()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyJobFestGo> readJobFestGo(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableJobFestGo> jsonJobFestGo = JsonUtil.readJsonFile(
                filePath, JsonSerializableJobFestGo.class);
        if (!jsonJobFestGo.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonJobFestGo.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveJobFestGo(ReadOnlyJobFestGo JobFestGo) throws IOException {
        saveJobFestGo(JobFestGo, filePath);
    }

    /**
     * Similar to {@link #saveJobFestGo(ReadOnlyJobFestGo)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveJobFestGo(ReadOnlyJobFestGo JobFestGo, Path filePath) throws IOException {
        requireNonNull(JobFestGo);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableJobFestGo(JobFestGo), filePath);
    }

}
