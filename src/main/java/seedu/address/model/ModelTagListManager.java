package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class ModelTagListManager implements ModelTagList {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final TagList tagList;
    private final UserPrefs userPrefs;
    private final FilteredList<Tag> filteredTags;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelTagListManager(ReadOnlyTagList tagList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tagList, userPrefs);

        logger.fine("Initializing with address book: " + tagList + " and user prefs " + userPrefs);

        this.tagList = new TagList(tagList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTags = new FilteredList<>(this.tagList.getTagList());
    }

    public ModelTagListManager() {
        this(new TagList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTagListFilePath() {
        return userPrefs.getTagListFilePath();
    }

    @Override
    public void setTagListFilePath(Path tagListFilePath) {
        requireNonNull(tagListFilePath);
        userPrefs.setTagListFilePath(tagListFilePath);
    }

    //=========== TagList ================================================================================


    @Override
    public void setTagList(ReadOnlyTagList tagList) {
        this.tagList.resetData(tagList);
    }

    @Override
    public ReadOnlyTagList getTagList() {
        return tagList;
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tagList.hasTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
    }

    @Override
    public void addTag(Tag tag) {
        tagList.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);
        tagList.setTag(target, editedTag);
    }

    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelTagListManager)) {
            return false;
        }

        ModelTagListManager otherModelManager = (ModelTagListManager) other;
        return tagList.equals(otherModelManager.tagList)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredTags.equals(otherModelManager.filteredTags);
    }
}
