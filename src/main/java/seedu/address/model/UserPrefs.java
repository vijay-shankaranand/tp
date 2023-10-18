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
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path tagListFilePath = Paths.get("data", "taglist.json");
    private Path eventListFilePath = Paths.get("data", "eventlist.json");

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
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setTagListFilePath(newUserPrefs.getTagListFilePath());
        setEventListFilePath(newUserPrefs.getEventListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
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
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath)
                && tagListFilePath.equals(otherUserPrefs.tagListFilePath)
                && eventListFilePath.equals(otherUserPrefs.eventListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, tagListFilePath, eventListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal address book data file location : " + addressBookFilePath);
        sb.append("\nLocal tag list file location : " + tagListFilePath);
        sb.append("\nLocal event list file location : " + eventListFilePath);
        return sb.toString();
    }

}
