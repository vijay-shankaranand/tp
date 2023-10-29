package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Person;
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
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Returns the {@code Person} with given name.
     */
    Person getPerson(Name name);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the unfiltered person list */
    ObservableList<Person> getUnfilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

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
     * The event must exist in the address book.
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
     * The task must exist in the address book.
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
}
