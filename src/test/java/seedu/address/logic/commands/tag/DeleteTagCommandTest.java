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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.person.DeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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
        Tag test2 = new Tag("personal");
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
        DeleteCommand diffCommand = new DeleteCommand(INDEX_FIRST);
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPerson(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getUnfilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
        public void updateFilteredEventList(Predicate<Event> predicate) {
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
