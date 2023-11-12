package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.contact.ContactBuilder;

public class UniqueContactListTest {

    private final UniqueContactList uniqueContactList = new UniqueContactList();

    @Test
    public void contains_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.contains(null));
    }

    @Test
    public void contains_contactNotInList_returnsFalse() {
        assertFalse(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_contactInList_returnsTrue() {
        uniqueContactList.add(ALICE);
        assertTrue(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_contactWithSameIdentityFieldsInList_returnsTrue() {
        uniqueContactList.add(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueContactList.contains(editedAlice));
    }

    @Test
    public void add_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.add(null));
    }

    @Test
    public void add_duplicateContact_throwsDuplicateContactException() {
        uniqueContactList.add(ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.add(ALICE));
    }

    @Test
    public void setContact_nullTargetContact_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(null, ALICE));
    }

    @Test
    public void setContact_nullEditedContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(ALICE, null));
    }

    @Test
    public void setContact_targetContactNotInList_throwsContactNotFoundException() {

        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.setContact(ALICE, ALICE));
    }

    @Test
    public void setContact_editedContactIsSameContact_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(ALICE);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasSameIdentity_success() {
        uniqueContactList.add(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueContactList.setContact(ALICE, editedAlice);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(editedAlice);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasDifferentIdentity_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, BOB);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BOB);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasNonUniqueIdentity_throwsDuplicateContactException() {
        uniqueContactList.add(ALICE);
        uniqueContactList.add(BOB);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContact(ALICE, BOB));
    }

    @Test
    public void remove_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.remove(null));
    }

    @Test
    public void remove_contactDoesNotExist_throwsContactNotFoundException() {
        assertThrows(ContactNotFoundException.class, () -> uniqueContactList.remove(ALICE));
    }

    @Test
    public void remove_existingContact_removesContact() {
        uniqueContactList.add(ALICE);
        uniqueContactList.remove(ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullUniqueContactList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((UniqueContactList) null));
    }

    @Test
    public void setContacts_uniqueContactList_replacesOwnListWithProvidedUniqueContactList() {
        uniqueContactList.add(ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BOB);
        uniqueContactList.setContacts(expectedUniqueContactList);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((List<Contact>) null));
    }

    @Test
    public void setContacts_list_replacesOwnListWithProvidedList() {
        uniqueContactList.add(ALICE);
        List<Contact> contactList = Collections.singletonList(BOB);
        uniqueContactList.setContacts(contactList);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BOB);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_listWithDuplicateContacts_throwsDuplicateContactException() {
        List<Contact> listWithDuplicateContacts = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContacts(listWithDuplicateContacts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueContactList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void updateTag_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.updateTag(new Tag("friends"));
        Contact editedAlice = new ContactBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .build();
        assertTrue(uniqueContactList.contains(editedAlice));
    }

    @Test
    public void updateTag_sameTag_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.updateTag(new Tag("family"));
        uniqueContactList.updateTag(new Tag("friends"));
        // update with same tag
        uniqueContactList.updateTag(new Tag("friends"));
        Contact editedAlice = new ContactBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .build();
        assertTrue(uniqueContactList.contains(editedAlice));
    }

    @Test
    public void iterator_iteration_success() {
        UniqueContactList list = new UniqueContactList();
        list.add(ALICE);
        list.add(BOB);

        int count = 0;
        for (Contact contact : list) {
            if (contact.equals(ALICE) || contact.equals(BOB)) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void equals_sameList_returnsTrue() {
        UniqueContactList list = new UniqueContactList();
        list.add(ALICE);
        list.add(BOB);
        assertTrue(list.equals(list));
    }

    @Test
    public void equals_differentLists_returnsFalse() {
        UniqueContactList firstList = new UniqueContactList();
        firstList.add(ALICE);
        UniqueContactList secondList = new UniqueContactList();
        secondList.add(BOB);
        assertFalse(firstList.equals(secondList));
    }

    @Test
    public void equals_null_returnsFalse() {
        UniqueContactList list = new UniqueContactList();
        list.add(ALICE);
        assertFalse(list.equals(null));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        UniqueContactList list = new UniqueContactList();
        list.add(ALICE);
        assertFalse(list.equals(ALICE));
    }

    @Test
    public void hashCode_sameList_equalHashCodes() {
        UniqueContactList firstList = new UniqueContactList();
        firstList.add(ALICE);
        firstList.add(BOB);
        UniqueContactList secondList = new UniqueContactList();
        secondList.add(ALICE);
        secondList.add(BOB);
        assertEquals(firstList.hashCode(), secondList.hashCode());
    }

    @Test
    public void hashCode_differentLists_differentHashCodes() {
        UniqueContactList firstList = new UniqueContactList();
        firstList.add(ALICE);
        UniqueContactList secondList = new UniqueContactList();
        secondList.add(BOB);
        assertNotEquals(firstList.hashCode(), secondList.hashCode());
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueContactList.asUnmodifiableObservableList().toString(), uniqueContactList.toString());
    }
}
