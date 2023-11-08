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
 * Represents the in-memory model of the JobFestGo data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final JobFestGo jobFestGo;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Tag> filteredTags;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Task> filteredTasks;


    /**
     * Initializes a ModelManager with the given jobfestgo and userPrefs.
     */
    public ModelManager(ReadOnlyJobFestGo jobFestGo, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(jobFestGo, userPrefs);

        logger.fine("Initializing with job fest go: " + jobFestGo + " and user prefs " + userPrefs);

        this.jobFestGo = new JobFestGo(jobFestGo);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.jobFestGo.getContactList());
        filteredTags = new FilteredList<>(this.jobFestGo.getTagList());
        filteredEvents = new FilteredList<>(this.jobFestGo.getEventList());
        filteredTasks = new FilteredList<>(this.jobFestGo.getTaskList());
    }

    public ModelManager() {
        this(new JobFestGo(), new UserPrefs());
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
    public Path getJobFestGoFilePath() {
        return userPrefs.getJobFestGoFilePath();
    }

    @Override
    public void setJobFestGoFilePath(Path jobFestGoFilePath) {
        requireNonNull(jobFestGoFilePath);
        userPrefs.setJobFestGoFilePath(jobFestGoFilePath);
    }

    //=========== JobFestGo ================================================================================

    //=========== Contacts ====================================================================================
    @Override
    public void setJobFestGo(ReadOnlyJobFestGo jobFestGo) {
        this.jobFestGo.resetData(jobFestGo);
    }

    @Override
    public ReadOnlyJobFestGo getJobFestGo() {
        return jobFestGo;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return jobFestGo.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        jobFestGo.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        jobFestGo.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public Contact getContact(Name name) {
        return jobFestGo.getContact(name);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);
        jobFestGo.setContact(target, editedContact);
    }

    @Override
    public boolean verifyContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);
        return jobFestGo.verifyContact(target, editedContact);
    }

    //=========== Tags =======================================================================================

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return jobFestGo.hasTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        jobFestGo.deleteTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void addTag(Tag tag) {
        jobFestGo.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);
        jobFestGo.setTag(target, editedTag);
    }

    //=========== Events =======================================================================================

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return jobFestGo.hasEvent(event);
    }
    @Override
    public void deleteEvent(Event eventToDelete) {
        jobFestGo.deleteEvent(eventToDelete);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void addEvent(Event eventToAdd) {
        jobFestGo.addEvent(eventToAdd);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public Event getEvent(Name name) {
        return jobFestGo.getEvent(name);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        jobFestGo.setEvent(target, editedEvent);
    }

    @Override
    public boolean isContactLinkedToEvent(Contact contact, Event event) {
        requireAllNonNull(contact, event);
        return jobFestGo.isContactLinkedToEvent(contact, event);
    }

    @Override
    public void linkContactToEvent(Contact contact, Event event) {
        requireAllNonNull(contact, event);
        jobFestGo.linkContactToEvent(contact, event);
    }

    @Override
    public void unlinkContactFromEvent(Contact contact, Event event) {
        requireAllNonNull(contact, event);
        jobFestGo.unlinkContactFromEvent(contact, event);
    }

    //=========== Tasks ======================================================================================
    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return jobFestGo.hasTask(task);
    }

    @Override
    public boolean hasTask(TaskDescription taskDescription, Name associatedEventName) {
        requireAllNonNull(taskDescription, associatedEventName);
        return jobFestGo.hasTask(taskDescription, associatedEventName);
    }

    @Override
    public void deleteTask(TaskDescription taskDescription, Name associatedEventName) {
        jobFestGo.deleteTask(taskDescription, associatedEventName);
    }

    @Override
    public void addTask(Task task) {
        requireNonNull(task);
        jobFestGo.addTask(task);
    }

    @Override
    public Task getTask(TaskDescription description, Date date, Event event) {
        return jobFestGo.getTask(description, date, event);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        jobFestGo.setTask(target, editedTask);
    }

    @Override
    public void markTask(TaskDescription taskDescription, Name associatedEventName) {
        jobFestGo.markTask(taskDescription, associatedEventName);
    }

    @Override
    public void unmarkTask(TaskDescription taskDescription, Name associatedEventName) {
        jobFestGo.unmarkTask(taskDescription, associatedEventName);
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedJobFestGo}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public ObservableList<Contact> getUnfilteredContactList() {
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return filteredContacts;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Predicate<Contact> getContactListPredicate() {
        // Since the predicate is always of type Predicate<Contact>, we can safely cast it
        return (Predicate<Contact>) filteredContacts.getPredicate();
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
        return jobFestGo.equals(otherModelManager.jobFestGo)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredContacts.equals(otherModelManager.filteredContacts)
                && filteredTags.equals(otherModelManager.filteredTags)
                && filteredEvents.equals(otherModelManager.filteredEvents);
    }

}
