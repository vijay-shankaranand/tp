package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.event.LinkCommand.MESSAGE_NO_SUCH_EVENT;
import static seedu.address.logic.commands.task.AddTaskCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.commands.task.AddTaskCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.JobFestGo;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.task.TaskBuilder;


public class AddTaskCommandTest {
    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, null, null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();

        modelStub.addEvent(NTU);
        Task validTask = new TaskBuilder().withEvent(NTU).build();

        CommandResult commandResult = new AddTaskCommand(validTask.getDescription(),
                validTask.getDate(), validTask.getAssociatedEventName()).execute(modelStub);

        assertEquals(String.format(MESSAGE_SUCCESS, validTask.getDescription(),
                        validTask.getDate(), validTask.getAssociatedEventName()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_noSuchEvent_throwsCommandException() {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask.getDescription(),
                validTask.getDate(), validTask.getAssociatedEventName());
        System.out.println(addTaskCommand);

        assertThrows(CommandException.class, String.format(MESSAGE_NO_SUCH_EVENT,
                validTask.getAssociatedEventName()), () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {

        Task validTask = new TaskBuilder().withEvent(NTU).build();
        ModelStub modelStub = new ModelStubWithTask(validTask);

        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask.getDescription(),
                validTask.getDate(), validTask.getAssociatedEventName());

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_TASK, () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Task validTask = new TaskBuilder().withEvent(NTU).build();
        Task validTask2 = new TaskBuilder().withEvent(JOBFEST).build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask.getDescription(),
                validTask.getDate(), validTask.getAssociatedEventName());
        AddTaskCommand addTaskCommand2 = new AddTaskCommand(validTask2.getDescription(),
                validTask2.getDate(), validTask2.getAssociatedEventName());

        // same object -> returns true
        assertTrue(addTaskCommand.equals(addTaskCommand));

        // same values -> returns true
        AddTaskCommand addTaskCommandCopy = new AddTaskCommand(validTask.getDescription(),
                validTask.getDate(), validTask.getAssociatedEventName());
        assertTrue(addTaskCommand.equals(addTaskCommandCopy));

        // different types -> returns false
        assertFalse(addTaskCommand.equals(1));

        // null -> returns false
        assertFalse(addTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(addTaskCommand.equals(addTaskCommand2));
    }

    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getJobFestGoFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJobFestGoFilePath(Path jobFestGoFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJobFestGo(ReadOnlyJobFestGo jobFestGo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyJobFestGo getJobFestGo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Contact getContact(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getUnfilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTag(Tag target, Tag editedTag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagList(Predicate<Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Event getEvent(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isContactLinkedToEvent(Contact contact, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void linkContactToEvent(Contact contact, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unlinkContactFromEvent(Contact contact, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getUnfilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(TaskDescription taskDescription, Name associatedTaskName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(TaskDescription taskDescription, Name associatedTaskName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task getTask(TaskDescription description, Date date, Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markTask(TaskDescription taskDescription, Name associatedTaskName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkTask(TaskDescription taskDescription, Name associatedTaskName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getTasksDueSoonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Contact> getContactListPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean verifyContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        private final ArrayList<Event> eventsAdded = new ArrayList<>();

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
            eventsAdded.add(NTU);
        }

        @Override
        public boolean hasTask(Task task) {
            return this.task.equals(task);
        }

        @Override
        public Event getEvent(Name eventName) {
            requireNonNull(eventName);
            return eventsAdded.stream().filter(event -> event.getName().equals(eventName)).findFirst().get();
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }
    }
    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        private final ArrayList<Task> tasksAdded = new ArrayList<>();
        private ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task :: equals);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public Event getEvent(Name eventName) {
            requireNonNull(eventName);
            try {
                return eventsAdded.stream().filter(event -> event.getName().equals(eventName)).findFirst().get();

            } catch (Exception e) {
                throw new EventNotFoundException();
            }
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            requireNonNull(predicate);
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            requireNonNull(predicate);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyJobFestGo getJobFestGo() {
            return new JobFestGo();
        }
    }
}
