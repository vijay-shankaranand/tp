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
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskInReminderPredicate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Tag> filteredTags;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Task> filteredTasks;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.addressBook.getContactList());
        filteredTags = new FilteredList<>(this.addressBook.getTagList());
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    //=========== Persons ====================================================================================
    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return addressBook.hasPerson(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addContact(Contact contact) {
        addressBook.addPerson(contact);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Contact getContact(Name name) {
        return addressBook.getPerson(name);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);
        addressBook.setPerson(target, editedContact);
    }

    //=========== Tags =======================================================================================

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return addressBook.hasTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        addressBook.deleteTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void addTag(Tag tag) {
        addressBook.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);
        addressBook.setTag(target, editedTag);
    }

    //=========== Events =======================================================================================

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return addressBook.hasEvent(event);
    }
    @Override
    public void deleteEvent(Event eventToDelete) {
        addressBook.deleteEvent(eventToDelete);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void addEvent(Event eventToAdd) {
        addressBook.addEvent(eventToAdd);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public Event getEvent(Name name) {
        return addressBook.getEvent(name);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        addressBook.setEvent(target, editedEvent);
    }

    //=========== Tasks ======================================================================================
    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public boolean hasTask(TaskDescription taskDescription, Name associatedEventName) {
        requireAllNonNull(taskDescription, associatedEventName);
        return addressBook.hasTask(taskDescription, associatedEventName);
    }

    @Override
    public void deleteTask(TaskDescription taskDescription, Name associatedEventName) {
        addressBook.deleteTask(taskDescription, associatedEventName);
    }

    @Override
    public void addTask(Task task) {
        requireNonNull(task);
        addressBook.addTask(task);
    }

    @Override
    public Task getTask(TaskDescription description, Date date, Event event) {
        return addressBook.getTask(description, date, event);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        addressBook.setTask(target, editedTask);
    }

    @Override
    public void markTask(TaskDescription taskDescription, Name associatedEventName) {
        addressBook.markTask(taskDescription, associatedEventName);
    }

    @Override
    public void unmarkTask(TaskDescription taskDescription, Name associatedEventName) {
        addressBook.unmarkTask(taskDescription, associatedEventName);
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public ObservableList<Contact> getUnfilteredContactList() {
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return filteredContacts;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    //======== Filtered Tag List Accessors ===============================================================

    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    //======== Filtered Event List Accessors ===============================================================

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public ObservableList<Event> getUnfilteredEventList() {
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    //======== Filtered Task List Accessors ===============================================================

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public ObservableList<Task> getTasksDueSoonList() {
        // Creation of a new filtered list to prevent the original filtered list
        // from not showing accurately when filtered further
        FilteredList<Task> taskDueSoonList = new FilteredList<>(filteredTasks);
        taskDueSoonList.setPredicate(new TaskInReminderPredicate());
        return taskDueSoonList;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        filteredTasks.setPredicate(PREDICATE_SHOW_ALL_TASKS);
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredContacts.equals(otherModelManager.filteredContacts)
                && filteredTags.equals(otherModelManager.filteredTags)
                && filteredEvents.equals(otherModelManager.filteredEvents);
    }

}
