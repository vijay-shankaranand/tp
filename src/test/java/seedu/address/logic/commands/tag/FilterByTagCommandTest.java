package seedu.address.logic.commands.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.BENSON;
import static seedu.address.testutil.contact.TypicalContacts.DANIEL;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.ContactIsTaggedPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterByTagCommand}.
 */
public class FilterByTagCommandTest {

    private Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJobFestGo(), new UserPrefs());

    @Test
    public void execute_zeroTags_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactIsTaggedPredicate predicate =
                new ContactIsTaggedPredicate(new ArrayList<>());
        FilterByTagCommand command = new FilterByTagCommand(new ArrayList<>(), predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleTags_multipleContactsFound() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(firstTag);
        tagList.add(secondTag);

        ContactIsTaggedPredicate predicate = new ContactIsTaggedPredicate(tagList);

        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        FilterByTagCommand command = new FilterByTagCommand(tagList, predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredContactList());
    }

    @Test
    public void execute_singleTag_singleContactsFound() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("owesMoney"));
        ContactIsTaggedPredicate predicate = new ContactIsTaggedPredicate(tagList);
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        FilterByTagCommand command = new FilterByTagCommand(tagList, predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredContactList());
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

        ContactIsTaggedPredicate firstPredicate =
                new ContactIsTaggedPredicate(firstTagList);
        ContactIsTaggedPredicate secondPredicate =
                new ContactIsTaggedPredicate(secondTagList);

        FilterByTagCommand firstFilterCommand = new FilterByTagCommand(firstTagList, firstPredicate);
        FilterByTagCommand secondFilterCommand = new FilterByTagCommand(secondTagList, secondPredicate);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterByTagCommand firstFilterCommandCopy = new FilterByTagCommand(firstTagList, firstPredicate);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));

        // different contact -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));
    }

    @Test
    public void toStringMethod() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(firstTag);
        tagList.add(secondTag);

        ContactIsTaggedPredicate predicate = new ContactIsTaggedPredicate(tagList);

        FilterByTagCommand command = new FilterByTagCommand(tagList, predicate);

        String expected = FilterByTagCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
}
