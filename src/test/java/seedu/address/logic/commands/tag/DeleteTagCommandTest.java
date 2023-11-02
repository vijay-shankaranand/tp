package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Contains integration tests (interactions with the Model) and unit tests for
 * {@code DeleteTagCommand}.
 */
public class DeleteTagCommandTest {

    /**
     * Tests the event in which a null tag is provided. Such a case throws a NullPointer.
     */
    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null));
    }

    /**
     * Tests when tag to be deleted is valid.
     * @throws Exception when commandResult is invalid
     */
    @Test
    public void execute_validTagToDelete() throws Exception {
        Tag test = new Tag("vendor");
        ModelStubWithTags testModel = new ModelStubWithTags();
        testModel.addTag(test);
        CommandResult commandResult = new DeleteTagCommand(test).execute(testModel);

        assertEquals(String.format(DeleteTagCommand.MESSAGE_SUCCESS, test),
            commandResult.getFeedbackToUser());

    }

    /**
     * Tests when tag to be deleted is invalid (not found in the list).
     * @throws Exception when commandResult is invalid
     */
    @Test
    public void execute_invalidTagToDelete() throws CommandException {
        Tag test1 = new Tag("vendor");
        Tag test2 = new Tag("Contactal");
        Tag test3 = new Tag("private");
        ModelStubWithTags testModel = new ModelStubWithTags();
        testModel.addTag(test1);
        testModel.addTag(test2);
        DeleteTagCommand command = new DeleteTagCommand(test3);

        assertThrows(CommandException.class, () -> command.execute(testModel));
    }

    /**
     * Tests to see that DeleteTagCommands with the same tag is the same.
     */
    @Test
    public void equals_sameTag() {
        Tag test = new Tag("vendor");
        ModelStubWithTags testModel = new ModelStubWithTags();
        testModel.addTag(test);
        DeleteTagCommand same1 = new DeleteTagCommand(test);
        DeleteTagCommand same2 = new DeleteTagCommand(test);
        DeleteContactCommand diffCommand = new DeleteContactCommand(INDEX_FIRST);
        assertEquals(same1, same2);
        assertEquals(same1, same1);
        assertFalse(same1.equals(diffCommand));
    }

    @Test
    public void toString_testEqual() {
        DeleteTagCommand test = new DeleteTagCommand(new Tag("vendor"));
        assertEquals(test.toString(), test.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
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
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Contact getContact(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJobFestGo(ReadOnlyJobFestGo newData) {
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
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
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
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(TaskDescription taskDescription, Name associatedEventName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(TaskDescription taskDescription, Name associatedEventName) {
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
        public void markTask(TaskDescription taskDescription, Name associatedEventName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkTask(TaskDescription taskDescription, Name associatedEventName) {
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
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getUnfilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getTasksDueSoonList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a list of tags.
     */
    private class ModelStubWithTags extends ModelStub {
        private ArrayList<Tag> tags;

        ModelStubWithTags() {
            this.tags = new ArrayList<>();
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            for (int i = 0; i < tags.size(); i++) {
                if (tags.get(i).isSameTag(tag)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            if (!hasTag(tag)) {
                this.tags.add(tag);
            }
        }

        @Override
        public void deleteTag(Tag tag) {
            requireNonNull(tag);
            if (hasTag(tag)) {
                tags.remove(tag);
            }
        }
    }
}
