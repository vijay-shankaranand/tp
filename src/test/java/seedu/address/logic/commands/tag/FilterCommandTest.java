package seedu.address.logic.commands.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalPersons.ALICE;
import static seedu.address.testutil.contact.TypicalPersons.BENSON;
import static seedu.address.testutil.contact.TypicalPersons.DANIEL;
import static seedu.address.testutil.contact.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonIsTaggedPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_zeroTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonIsTaggedPredicate predicate =
                new PersonIsTaggedPredicate(new ArrayList<>());
        FilterCommand command = new FilterCommand(new ArrayList<>(), predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_multiplePersonsFound() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(firstTag);
        tagList.add(secondTag);

        PersonIsTaggedPredicate predicate = new PersonIsTaggedPredicate(tagList);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FilterCommand command = new FilterCommand(tagList, predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleTag_singlePersonsFound() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("owesMoney"));
        PersonIsTaggedPredicate predicate = new PersonIsTaggedPredicate(tagList);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FilterCommand command = new FilterCommand(tagList, predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void equals() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> firstTagList = new ArrayList<>();
        List<Tag> secondTagList = new ArrayList<>();

        firstTagList.add(firstTag);
        secondTagList.add(firstTag);
        secondTagList.add(secondTag);

        PersonIsTaggedPredicate firstPredicate =
                new PersonIsTaggedPredicate(firstTagList);
        PersonIsTaggedPredicate secondPredicate =
                new PersonIsTaggedPredicate(secondTagList);

        FilterCommand firstFilterCommand = new FilterCommand(firstTagList, firstPredicate);
        FilterCommand secondFilterCommand = new FilterCommand(secondTagList, secondPredicate);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(firstTagList, firstPredicate);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));

        // different person -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));
    }

    @Test
    public void toStringMethod() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(firstTag);
        tagList.add(secondTag);

        PersonIsTaggedPredicate predicate = new PersonIsTaggedPredicate(tagList);

        FilterCommand command = new FilterCommand(tagList, predicate);

        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
}
