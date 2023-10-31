package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A contact is considered unique by comparing using {@code Contact#isSameContact(Contact)}. As such, adding and updating
 * of contacts uses Contact#isSameContact(Contact) for equality to ensure that the contact being added or updated is
 * unique in terms of identity in the UniqueContactList. However, the removal of a contact uses Contact#equals(Object)
 * to ensure that the contact with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Contact#isSameContact(Contact)
 */
public class UniqueContactList implements Iterable<Contact> {

    private final ObservableList<Contact> internalList = FXCollections.observableArrayList();
    private final ObservableList<Contact> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent contact as the given argument.
     */
    public boolean contains(Contact toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameContact);
    }

    /**
     * Adds a contact to the list.
     * The contact must not already exist in the list.
     */
    public void add(Contact toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateContactException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns the {@code Contact} with given name from the list.
     * @param name The name of the desired {@code Contact}.
     */
    public Contact getByName(Name name) throws ContactNotFoundException {
        Contact toGet = null;
        for (int i = 0; i < internalList.size(); i++) {
            Contact thisContact = internalList.get(i);
            if (thisContact.getName().equals(name)) {
                toGet = thisContact;
            }
        }
        if (toGet == null) {
            throw new ContactNotFoundException(name);
        } else {
            return toGet;
        }
    }

    /**
     * Replaces the contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the list.
     * The contact identity of {@code editedContact} must not be the same as another existing contact in the list.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ContactNotFoundException();
        }

        if (!target.isSameContact(editedContact) && contains(editedContact)) {
            throw new DuplicateContactException();
        }

        internalList.set(index, editedContact);
    }

    /**
     * Removes the equivalent contact from the list.
     * The contact must exist in the list.
     */
    public void remove(Contact toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ContactNotFoundException();
        }
    }

    public void setContacts(UniqueContactList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code contacts}.
     * {@code contacts} must not contain duplicate contacts.
     */
    public void setContacts(List<Contact> contacts) {
        requireAllNonNull(contacts);
        if (!areContactsUnique(contacts)) {
            throw new DuplicateContactException();
        }

        internalList.setAll(contacts);
    }

    /**
     * Replaces the contact list to remove {@code tag} from any persons.
     */
    public void updateTag(Tag tag) {
        for (int i = 0; i < internalList.size(); i++) {
            Contact curr = internalList.get(i);
            Set<Tag> tagList = curr.getTags();
            Set<Tag> newTagList = new HashSet<>();
            if (tagList.contains(tag)) {
                for (Tag t: tagList) {
                    if (!t.equals(tag)) {
                        newTagList.add(t);
                    }
                }
            } else {
                newTagList = tagList;
            }
            Contact editedContact = new Contact(curr.getName(), curr.getPhone(),
                curr.getEmail(), curr.getAddress(), newTagList);
            internalList.set(i, editedContact);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Contact> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Contact> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueContactList)) {
            return false;
        }

        UniqueContactList otherUniqueContactList = (UniqueContactList) other;
        return internalList.equals(otherUniqueContactList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code contacts} contains only unique contacts.
     */
    private boolean areContactsUnique(List<Contact> contacts) {
        for (int i = 0; i < contacts.size() - 1; i++) {
            for (int j = i + 1; j < contacts.size(); j++) {
                if (contacts.get(i).isSameContact(contacts.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
