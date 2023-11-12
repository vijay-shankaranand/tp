package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

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
     * Returns the user prefs' JobFestGo file path.
     */
    Path getJobFestGoFilePath();

    /**
     * Sets the user prefs' JobFestGo file path.
     */
    void setJobFestGoFilePath(Path jobFestGoFilePath);

    /**
     * Replaces JobFestGo data with the data in {@code JobFestGo}.
     */
    void setJobFestGo(ReadOnlyJobFestGo jobFestGo);

    /** Returns the JobFestGo */
    ReadOnlyJobFestGo getJobFestGo();

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in JobFestGo.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given contact.
     * The contact must exist in JobFestGo.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in JobFestGo.
     */
    void addContact(Contact contact);

    /**
     * Returns the {@code Contact} with given name.
     */
    Contact getContact(Name name);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in JobFestGo.
     * The contact identity of {@code editedContact} must not be the same as another existing contact in the address
     * book.
     */
    void setContact(Contact target, Contact editedContact);

    /**
     * Verifies that the {@code editedContact} is valid and does not contain any repeated name
     * or phone number, apart from that of {@code target}.
     * Returns true if the {@code editedContact} is valid.
     */
    boolean verifyContact(Contact target, Contact editedContact);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the unfiltered contact list */
    ObservableList<Contact> getUnfilteredContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    /** Returns the predicate used previously on the task list */
    Predicate<Contact> getContactListPredicate();

    /**
     * Returns true if an existing tag with the same identity as {@code tag} exists in the tag list.
     */
    boolean hasTag(Tag tag);

    /**
     * Deletes the given tag.
     * {@code tag} must exist in the tag list.
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

    /**
     * Returns true if an existing event with the same identity as {@code event} exists in the event list.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in JobFestGo.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the event list.
     */
    void addEvent(Event event);

    /**
     * Returns the {@code Event} with given name.
     */
    Event getEvent(Name name);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the event list.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the event list.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Checks whether the given {@code contact} is linked to the given {@code event}.
     */
    boolean isContactLinkedToEvent(Contact contact, Event event);

    /**
     * Links the given {@code contact} to the given {@code event}.
     */
    void linkContactToEvent(Contact contact, Event event);

    /**
     * Unlinks the given {@code contact} from the given {@code event}.
     */
    void unlinkContactFromEvent(Contact contact, Event event);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the unfiltered event list */
    ObservableList<Event> getUnfilteredEventList();

    /**
     * Updates the filter of the filtered tag list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Returns true if an existing event with the same identity as {@code task} exists in the event.
     */
    boolean hasTask(Task task);

    /**
     * Returns true if an existing event has the given {@code taskDescription} exists in
     * the event specified by the given {@code associatedEventName}.
     */
    boolean hasTask(TaskDescription taskDescription, Name associatedEventName);

    /**
     * Deletes the task specified by the task description from its associated event.
     * The task must exist in JobFestGo.
     */
    void deleteTask(TaskDescription taskDescription, Name associatedEventName);

    /**
     * Adds the given task.
     * {@code event} must not already exist in the task.
     */
    void addTask(Task task);

    /**
     * Returns the {@code task} with given description.
     */
    Task getTask(TaskDescription description, Date date, Event event);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the event.
     * The event identity of {@code editedTask} must not be the same as another existing task in the event.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Marks the specified task as completed.
     */
    void markTask(TaskDescription taskDescription, Name associatedEventName);

    /**
     * Marks the specified task as not completed.
     */
    void unmarkTask(TaskDescription taskDescription, Name associatedEventName);

    /** Returns an unmodifiable view of the filtered tasks in an event */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the tasks due soon */
    ObservableList<Task> getTasksDueSoonList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /** Returns true if contact list is being shown. Returns false otherwise. */
    boolean isOnContactsScreen();

    /** Returns true if events list is being shown. Returns false otherwise. */
    boolean isOnEventsScreen();

    /** Returns true if tags list is being shown. Returns false otherwise. */
    boolean isOnTagsScreen();

    /**
     * Changes the boolean value of isOnContactsScreen to the given {@code isOnContactsScreen}.
     */
    void switchToContactsScreen(boolean onContactsScreen);

    /**
     * Changes the boolean value of isOnEventsScreen to the given {@code isOnEventsScreen}.
     */
    void switchToEventsScreen(boolean onEventsScreen);

    /**
     * Changes the boolean value of isOnTagsScreen to the given {@code isOnTagsScreen}.
     */
    void switchToTagsScreen(boolean onTagsScreen);
}
