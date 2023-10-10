package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public interface ModelTagList {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
    /**
     * Returns the user prefs' Tag List file path.
     */
    Path getTagListFilePath();

    /**
     * Sets the user prefs' Tag List file path.
     */
    void setTagListFilePath(Path tagListFilePath);

    /**
     * Replaces tag list data with the data in {@code TagList}.
     */
    void setTagList(ReadOnlyTagList tagList);

    /** Returns the TagList */
    ReadOnlyTagList getTagList();

    /**
     * Returns true if an existing tag with the same identity as {@code tag} exists in the tag list.
     */
    boolean hasTag(Tag tag);

    /**
     * Deletes the given tag.
     * The tag must exist in the tag list.
     */
    void deleteTag(Tag tag);

    /**
     * Adds the given tag.
     * {@code tag} must not already exist in the tag list.
     */
    void addTag(Tag tag);

    /**
     * Replaces the given tag {@code target} with {@code editedTag}.
     * {@code target} must exist in the tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the tag list.
     */
    void setTag(Tag target, Tag editedTag);

    /** Returns an unmodifiable view of the filtered tag list */
    ObservableList<Tag> getFilteredTagList();

    /**
     * Updates the filter of the filtered tag list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTagList(Predicate<Tag> predicate);
}
