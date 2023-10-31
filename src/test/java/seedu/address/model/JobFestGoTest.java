package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.tag.TypicalTags.VENUES;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.contact.ContactBuilder;
import seedu.address.testutil.event.EventBuilder;
import seedu.address.testutil.tag.TagBuilder;
import seedu.address.testutil.task.TaskBuilder;


public class JobFestGoTest {

    private final JobFestGo jobFestGo = new JobFestGo();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), jobFestGo.getContactList());
        assertEquals(Collections.emptyList(), jobFestGo.getTagList());
        assertEquals(Collections.emptyList(), jobFestGo.getEventList());
        assertEquals(Collections.emptyList(), jobFestGo.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobFestGo.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyJobFestGo_replacesData() {
        JobFestGo newData = getTypicalJobFestGo();
        jobFestGo.resetData(newData);
        assertEquals(newData, jobFestGo);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        List<Tag> tags = Arrays.asList(VENUES);
        JobFestGoStub newData = new JobFestGoStub(newContacts, tags);

        assertThrows(DuplicateContactException.class, () -> jobFestGo.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobFestGo.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInJobFestGo_returnsFalse() {
        assertFalse(jobFestGo.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInJobFestGo_returnsTrue() {
        jobFestGo.addContact(ALICE);
        assertTrue(jobFestGo.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInJobFestGo_returnsTrue() {
        jobFestGo.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(jobFestGo.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jobFestGo.getContactList().remove(0));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobFestGo.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInJobFestGo_returnsFalse() {
        assertFalse(jobFestGo.hasTag(VENUES));
    }

    @Test
    public void hasTag_tagInJobFestGo_returnsTrue() {
        jobFestGo.addTag(VENUES);
        assertTrue(jobFestGo.hasTag(VENUES));
    }

    @Test
    public void hasTag_tagWithSameIdentityFieldsInJobFestGo_returnsTrue() {
        jobFestGo.addTag(VENUES);
        Tag editedVenues = new TagBuilder().withTag(VENUES.getTagName())
                .build();
        assertTrue(jobFestGo.hasTag(editedVenues));
    }

    @Test
    public void deleteTag_successful_returnsTrue() {
        Contact initialAlice = new ContactBuilder(ALICE).withTags("vendor").build();
        Contact editedAlice = new ContactBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .build();
        jobFestGo.addContact(initialAlice);
        Tag tag = new Tag("vendor");
        jobFestGo.addTag(tag);
        jobFestGo.deleteTag(tag);
        assertTrue(!jobFestGo.hasTag(tag));
        assertTrue(jobFestGo.hasContact(editedAlice));
    }

    @Test
    public void getTagsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jobFestGo.getTagList().remove(0));
    }

    @Test
    public void addEvent_successful_returnsTrue() {
        jobFestGo.addEvent(JOBFEST);
        assertTrue(jobFestGo.hasEvent(JOBFEST));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobFestGo.setEvent(null, JOBFEST));
    }

    @Test
    public void setEvent_successful_returnsTrue() {
        jobFestGo.addEvent(JOBFEST);
        Event editedEvent = new EventBuilder(JOBFEST)
                .withDate("2023-12-01")
                .build();
        jobFestGo.setEvent(JOBFEST, editedEvent);
        assertTrue(jobFestGo.hasEvent(editedEvent));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobFestGo.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInJobFestGo_returnsFalse() {
        assertFalse(jobFestGo.hasEvent(JOBFEST));
    }

    @Test
    public void hasEvent_eventInJobFestGo_returnsTrue() {
        jobFestGo.addEvent(JOBFEST);
        assertTrue(jobFestGo.hasEvent(JOBFEST));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInJobFestGo_returnsTrue() {
        jobFestGo.addEvent(JOBFEST);
        Event editedEvent = new EventBuilder(JOBFEST)
                .withDate("2023-12-01")
                .build();
        assertTrue(jobFestGo.hasEvent(editedEvent));
    }

    @Test
    public void deleteEvent_successful_returnsTrue() {
        Event editedEvent = new EventBuilder(JOBFEST).withEventTasks(BOOK_VENUE).build();
        jobFestGo.addEvent(editedEvent);
        jobFestGo.addTask(BOOK_VENUE);
        jobFestGo.deleteEvent(editedEvent);
        assertFalse(jobFestGo.hasEvent(editedEvent));
        assertFalse(jobFestGo.hasTask(BOOK_VENUE));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jobFestGo.getEventList().remove(0));
    }

    @Test
    public void addTask_successful_returnsTrue() {
        jobFestGo.addTask(BOOK_VENUE);
        assertTrue(jobFestGo.hasTask(BOOK_VENUE));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobFestGo.setTask(null, BOOK_VENUE));
    }

    @Test
    public void setTask_successful_returnsTrue() {
        jobFestGo.addTask(BOOK_VENUE);
        Task editedTask = new TaskBuilder(BOOK_VENUE)
                .withDate("2023-12-01")
                .build();
        jobFestGo.setTask(BOOK_VENUE, editedTask);
        assertTrue(jobFestGo.hasTask(editedTask));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobFestGo.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInJobFestGo_returnsFalse() {
        assertFalse(jobFestGo.hasTask(BOOK_VENUE));
    }

    @Test
    public void hasTask_taskInJobFestGo_returnsTrue() {
        jobFestGo.addTask(BOOK_VENUE);
        assertTrue(jobFestGo.hasTask(BOOK_VENUE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInJobFestGo_returnsTrue() {
        jobFestGo.addTask(BOOK_VENUE);
        Task editedTask = new TaskBuilder(BOOK_VENUE)
                .withDate("2023-12-01")
                .build();
        assertTrue(jobFestGo.hasTask(editedTask));
    }

    @Test
    public void deleteTask_successful_returnsTrue() {
        jobFestGo.addTask(BOOK_VENUE);
        jobFestGo.deleteTask(BOOK_VENUE.getDescription(), BOOK_VENUE.getAssociatedEventName());
        assertFalse(jobFestGo.hasTask(BOOK_VENUE));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jobFestGo.getTaskList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = JobFestGo.class.getCanonicalName() + "{contacts=" + jobFestGo.getContactList()
                +
                ", tags=" + jobFestGo.getTagList()
                + ", events=" + jobFestGo.getEventList()
                + ", tasks=" + jobFestGo.getTaskList()
                + "}";
        assertEquals(expected, jobFestGo.toString());
    }

    /**
     * A stub ReadOnlyJobFestGo whose contacts list can violate interface constraints.
     */
    private static class JobFestGoStub implements ReadOnlyJobFestGo {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        private final ObservableList<Event> events = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();


        JobFestGoStub(Collection<Contact> contacts, Collection<Tag> tags) {
            this.contacts.setAll(contacts);
            this.tags.setAll(tags);
            this.events.setAll(events);
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }
}
