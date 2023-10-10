package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of a tags collection
 */
public interface ReadOnlyTagList {

    /**
     * Returns an unmodifiable view of tags collection.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagCollection();
}
