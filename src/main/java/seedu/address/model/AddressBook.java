package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventSortByDateComparator;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskSortByDateAndCompletionComparator;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameContact comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private final UniqueContactList contacts;

    private final UniqueTagList tags;

    private final UniqueEventList events;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        contacts = new UniqueContactList();
        tags = new UniqueTagList();
        events = new UniqueEventList();
        tasks = new UniqueTaskList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //==== list overwrite operations ================================================================

    /**
     * Replaces the contents of the contact list with {@code contacts}.
     * {@code contacts} must not contain duplicate contacts.
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    /**
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setContacts(newData.getContactList());
        setTags(newData.getTagList());
        setEvents(newData.getEventList());
        setTasks(newData.getTaskList());
    }


    //==== contact-level operations ==================================================================

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the address book.
     */
    public boolean hasPerson(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Adds a contact to the address book.
     * The contact must not already exist in the address book.
     */
    public void addPerson(Contact p) {
        contacts.add(p);
    }

    /**
     * Returns the {@code Contact} with given name.
     */
    public Contact getPerson(Name name) {
        return contacts.getByName(name);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the address book.
     * The contact identity of {@code editedContact} must not be the same as another existing contact in the address
     * book.
     */
    public void setPerson(Contact target, Contact editedContact) {
        requireNonNull(editedContact);
        contacts.setContact(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Contact key) {
        contacts.remove(key);
        events.updateContacts(key);
    }

    //// util methods

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    //======Tag Operations=======================================================================

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the address book.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds a tag to the address book.
     * The tag must not already exist in the address book.
     */
    public void addTag(Tag p) {
        tags.add(p);
    }

    /**
     * Deletes a {@code tag} from the address book.
     * Deletes the tag from contacts using it as well.
     */
    public void deleteTag(Tag tagToBeDeleted) {
        tags.delete(tagToBeDeleted);
        contacts.updateTag(tagToBeDeleted);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the address book.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireNonNull(editedTag);

        tags.setTag(target, editedTag);
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    //======Event Operations=======================================================================

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Deletes a {@code event} from the address book.
     * Deletes the event from contacts using it as well.
     */
    public void deleteEvent(Event eventToDelete) {
        events.remove(eventToDelete);
        tasks.deleteTasksFromEvent(eventToDelete.getTasks());
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book.
     */
    public void addEvent(Event eventToAdd) {
        events.add(eventToAdd);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    /**
     * Returns the {@code Event} with given name.
     */
    public Event getEvent(Name name) {
        return events.getByName(name);
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList().sorted(new EventSortByDateComparator());
    }

    //======Task Operations=======================================================================

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Returns true if an existing event has the given {@code taskDescription} exists in
     * the event specified by the given {@code associatedEventName}.
     */
    public boolean hasTask(TaskDescription taskDescription, Name associatedEventName) {
        requireAllNonNull(taskDescription, associatedEventName);
        return tasks.contains(taskDescription, associatedEventName);
    }

    /**
     * Adds a task to the address book.
     * The task must not already exist in JobFestGo.
     */
    public void addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
        events.addTaskInEvent(taskToAdd);
    }

    /**
     * Deletes the task specified by the given task description from its associated event.
     * The task must already exist in JobFestGo.
     */
    public void deleteTask(TaskDescription taskDescription, Name associatedEventName) {
        Task taskToDelete = tasks.getByValues(taskDescription, associatedEventName);
        tasks.delete(taskToDelete);
        events.deleteTaskFromEvent(taskToDelete);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        tasks.setTask(target, editedTask);
    }

    /**
     * Returns the {@code Task} with given arguments.
     */
    public Task getTask(TaskDescription description, Date date, Event event) {
        return tasks.getByValues(description, date, event);
    }

    /**
     * Returns the {@code Task} with given arguments.
     */
    public Task getTask(TaskDescription description, Name eventName) {
        return tasks.getByValues(description, eventName);
    }

    /**
     * Marks the specified task as completed.
     */
    public void markTask(TaskDescription taskDescription, Name associatedEventName) {
        Task taskToMark = tasks.getByValues(taskDescription, associatedEventName);
        tasks.mark(taskToMark);
        events.markTask(taskToMark);
    }

    /**
     * Marks the specified task as not completed.
     */
    public void unmarkTask(TaskDescription taskDescription, Name associatedEventName) {
        Task taskToUnmark = tasks.getByValues(taskDescription, associatedEventName);
        tasks.unmark(taskToUnmark);
        events.unmarkTask(taskToUnmark);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList().sorted(new TaskSortByDateAndCompletionComparator());
    }
    //====== util methods ===========================================================================

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return contacts.equals(otherAddressBook.contacts);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contacts", contacts)
                .add("tags", tags)
                .add("events", events)
                .add("tasks", tasks)
                .toString();
    }

    @Override
    public int hashCode() {
        return contacts.hashCode();
    }
}
