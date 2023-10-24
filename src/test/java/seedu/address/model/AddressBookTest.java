package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.tag.TypicalTags.VENUES;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.event.EventBuilder;
import seedu.address.testutil.person.PersonBuilder;
import seedu.address.testutil.tag.TagBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getTagList());
        assertEquals(Collections.emptyList(), addressBook.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Tag> tags = Arrays.asList(VENUES);
        AddressBookStub newData = new AddressBookStub(newPersons, tags);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTag(VENUES));
    }

    @Test
    public void hasTag_tagInAddressBook_returnsTrue() {
        addressBook.addTag(VENUES);
        assertTrue(addressBook.hasTag(VENUES));
    }

    @Test
    public void hasTag_tagWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTag(VENUES);
        Tag editedVenues = new TagBuilder().withTag(VENUES.getTagName())
                .build();
        assertTrue(addressBook.hasTag(editedVenues));
    }

    @Test
    public void deleteTag_successful_returnsTrue() {
        Person initialAlice = new PersonBuilder(ALICE).withTags("vendor").build();
        Person editedAlice = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .build();
        addressBook.addPerson(initialAlice);
        Tag tag = new Tag("vendor");
        addressBook.addTag(tag);
        addressBook.deleteTag(tag);
        assertTrue(!addressBook.hasTag(tag));
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getTagsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTagList().remove(0));
    }

    @Test
    public void addEvent_successful_returnsTrue() {
        addressBook.addEvent(JOBFEST);
        assertTrue(addressBook.hasEvent(JOBFEST));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setEvent(null, JOBFEST));
    }

    @Test
    public void setEvent_successful_returnsTrue() {
        addressBook.addEvent(JOBFEST);
        Event editedEvent = new EventBuilder(JOBFEST)
                .withEventDate("2023-12-01")
                .build();
        addressBook.setEvent(JOBFEST, editedEvent);
        assertTrue(addressBook.hasEvent(editedEvent));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEvent(JOBFEST));
    }

    @Test
    public void hasEvent_eventInAddressBook_returnsTrue() {
        addressBook.addEvent(JOBFEST);
        assertTrue(addressBook.hasEvent(JOBFEST));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addEvent(JOBFEST);
        Event editedEvent = new EventBuilder(JOBFEST)
                .withEventDate("2023-12-01")
                .build();
        assertTrue(addressBook.hasEvent(editedEvent));
    }

    @Test
    public void deleteEvent_successful_returnsTrue() {
        addressBook.addEvent(JOBFEST);
        addressBook.deleteEvent(JOBFEST);
        assertFalse(addressBook.hasEvent(JOBFEST));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getEventList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList()
                +
                ", tags=" + addressBook.getTagList()
                + ", events=" + addressBook.getEventList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        private final ObservableList<Event> events = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Tag> tags) {
            this.persons.setAll(persons);
            this.tags.setAll(tags);
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }
}
