package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalPersons.ALICE;
import static seedu.address.testutil.contact.TypicalPersons.BENSON;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.tag.TypicalTags.VENUES;
import static seedu.address.testutil.task.TypicalTasks.BOOK_VENUE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.name.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasContact(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() throws CommandException {
        modelManager.addTag(new Tag("friends"));
        modelManager.addContact(ALICE);
        assertTrue(modelManager.hasContact(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getUnfilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getUnfilteredPersonList().remove(0));
    }

    @Test
    public void deleteTag_returnsTrue() {
        Tag tag = new Tag("vendor");
        modelManager.addTag(tag);
        modelManager.deleteTag(tag);
        assertFalse(modelManager.hasTag(tag));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTag(VENUES));
    }

    @Test
    public void hasTag_tagInAddressBook_returnsTrue() {
        modelManager.addTag(VENUES);
        assertTrue(modelManager.hasTag(VENUES));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTag(null, VENUES));
    }

    @Test
    public void setTag_nullEditedTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTag(VENUES, null));
    }

    @Test
    public void setTag_editedTagIsSameTag_success() {
        modelManager.addTag(VENUES);
        modelManager.setTag(VENUES, VENUES);
        assertTrue(modelManager.hasTag(VENUES));
    }

    @Test
    public void setTag_success() {
        modelManager.addTag(VENUES);
        modelManager.setTag(VENUES, new Tag("vendor"));
        assertFalse(modelManager.hasTag(VENUES));
        assertTrue(modelManager.hasTag(new Tag("vendor")));
    }

    @Test
    public void getFilteredTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTagList().remove(0));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEvent(JOBFEST));
    }

    @Test
    public void hasEvent_eventInAddressBook_returnsTrue() {
        modelManager.addEvent(JOBFEST);
        assertTrue(modelManager.hasEvent(JOBFEST));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEvent(null, JOBFEST));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEvent(JOBFEST, null));
    }

    @Test
    public void setEvent_targetEventNotInAddressBook_throwsCommandException() {
        assertThrows(EventNotFoundException.class, () -> modelManager.setEvent(JOBFEST, JOBFEST));
    }

    @Test
    public void setEvent_editedEventIsSameEvent_success() throws CommandException {
        modelManager.addEvent(JOBFEST);
        modelManager.setEvent(JOBFEST, JOBFEST);
        assertTrue(modelManager.hasEvent(JOBFEST));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void updateFilteredEventList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredEventList(null));
    }

    @Test
    public void getUnfilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getUnfilteredEventList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTask(BOOK_VENUE));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        modelManager.addTask(BOOK_VENUE);
        assertTrue(modelManager.hasTask(BOOK_VENUE));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTask(null, BOOK_VENUE));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTask(BOOK_VENUE, null));
    }

    @Test
    public void setTask_targetTaskNotInAddressBook_throwsCommandException() {
        assertThrows(TaskNotFoundException.class, () -> modelManager.setTask(BOOK_VENUE, BOOK_VENUE));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() throws CommandException {
        modelManager.addTask(BOOK_VENUE);
        modelManager.setTask(BOOK_VENUE, BOOK_VENUE);
        assertTrue(modelManager.hasTask(BOOK_VENUE));
    }

    @Test
    public void getTask_success() {
        modelManager.addTask(BOOK_VENUE);
        assertEquals(BOOK_VENUE, modelManager.getTask(BOOK_VENUE.getDescription(), BOOK_VENUE.getDate(),
                BOOK_VENUE.getAssociatedEvent()));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void updateFilteredTaskList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredTaskList(null));
    }

    @Test
    public void updatedFilteredTaskList_success() {
        modelManager.addTask(BOOK_VENUE);
        modelManager.updateFilteredTaskList(task -> task.getDescription().equals(BOOK_VENUE.getDescription()));
        assertEquals(BOOK_VENUE, modelManager.getFilteredTaskList().get(0));
    }

    @Test
    public void getUnfilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }



}
