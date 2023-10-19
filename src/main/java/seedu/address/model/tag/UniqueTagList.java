package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#isSameTag(Tag)}. As such, adding and updating of
 * tags uses Tag#isSameTag(Tag) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniqueTagList. However, the removal of a tag uses Tag#equals(Object) so
 * as to ensure that the tag with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tag#isSameTag(Tag)
 */
public class UniqueTagList implements Iterable<Tag> {
    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag tagToCheck) {
        requireNonNull(tagToCheck);
        return internalList.stream().anyMatch(tagToCheck::isSameTag);
    }

    /**
     * Adds a tag  to the list.
     * The tag must not already exist in the list.
     */
    public void add(Tag tagToBeAdded) {
        requireNonNull(tagToBeAdded);
        if (contains(tagToBeAdded)) {
            throw new DuplicateTagException();
        }
        internalList.add(tagToBeAdded);
    }

    /**
     * Deletes a tag {@code tagToBeDeleted} from the list.
     * {@code tagToBeDeleted} must already exist in the list.
     */
    public void delete(Tag tagToBeDeleted) {
        requireNonNull(tagToBeDeleted);
        if (!contains(tagToBeDeleted)) {
            throw new TagNotFoundException();
        }
        internalList.remove(tagToBeDeleted);
    }

    /**
     * Replaces the tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the list.
     * The tag of {@code editedTag} must not be the same as another existing tag in the list.
     */
    public void setTag(Tag targetTag, Tag editedTag) {
        requireAllNonNull(targetTag, editedTag);

        int index = internalList.indexOf(targetTag);
        if (index == -1) {
            throw new TagNotFoundException();
        }

        if (!targetTag.isSameTag(editedTag) && contains(editedTag)) {
            throw new DuplicateTagException();
        }

        internalList.set(index, editedTag);
    }

    public void setTags(UniqueTagList replacementTag) {
        requireNonNull(replacementTag);
        internalList.setAll(replacementTag.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!areTagsUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags);
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean areTagsUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).isSameTag(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTagList)) {
            return false;
        }

        UniqueTagList otherUniqueTagList = (UniqueTagList) other;
        return internalList.equals(otherUniqueTagList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
