package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path jobFestGoFilePath = Paths.get("data" , "jobfestgo.json");
    private Path tagListFilePath = Paths.get("data", "taglist.json");
    private Path eventListFilePath = Paths.get("data", "eventlist.json");
    private Path taskListFilePath = Paths.get("data", "taskList.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setJobFestGoFilePath(newUserPrefs.getJobFestGoFilePath());
        setTagListFilePath(newUserPrefs.getTagListFilePath());
        setEventListFilePath(newUserPrefs.getEventListFilePath());
        setTaskListFilePath(newUserPrefs.getTaskListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getJobFestGoFilePath() {
        return jobFestGoFilePath;
    }

    public void setJobFestGoFilePath(Path jobFestGoFilePath) {
        requireNonNull(jobFestGoFilePath);
        this.jobFestGoFilePath = jobFestGoFilePath;
    }

    public Path getTagListFilePath() {
        return tagListFilePath;
    }

    public void setTagListFilePath(Path tagListFilePath) {
        requireNonNull(tagListFilePath);
        this.tagListFilePath = tagListFilePath;
    }

    public Path getEventListFilePath() {
        return eventListFilePath;
    }

    public void setEventListFilePath(Path eventListFilePath) {
        requireNonNull(eventListFilePath);
        this.eventListFilePath = eventListFilePath;
    }
    public Path getTaskListFilePath() {
        return taskListFilePath;
    }
    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        this.taskListFilePath = taskListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && jobFestGoFilePath.equals(otherUserPrefs.jobFestGoFilePath)
                && tagListFilePath.equals(otherUserPrefs.tagListFilePath)
                && eventListFilePath.equals(otherUserPrefs.eventListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, jobFestGoFilePath, tagListFilePath, eventListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal jobfestgo data file location : " + jobFestGoFilePath);
        sb.append("\nLocal tag list file location : " + tagListFilePath);
        sb.append("\nLocal event list file location : " + eventListFilePath);
        return sb.toString();
    }

}
