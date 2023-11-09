package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.BENSON;
import static seedu.address.testutil.contact.TypicalContacts.BOB;
import static seedu.address.testutil.contact.TypicalContacts.CARL;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactIsInEventPredicate;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskIsInEventPredicate;
import seedu.address.testutil.contact.ContactBuilder;
import seedu.address.testutil.event.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code UnlinkCommand}.
 */
public class UnlinkCommandTest {
    private Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJobFestGo(), new UserPrefs());

    @Test
    public void execute_validSingleContact_success() {
        Event expectedEvent = new EventBuilder(JOBFEST).withEventContacts(BOB).build();
        model.addEvent(JOBFEST);
        expectedModel.addEvent(expectedEvent);
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(ALICE.getName());
        UnlinkCommand command = new UnlinkCommand(JOBFEST.getName(), contactNameList);

        String expectedNameList = "[" + ALICE.getName() + "]";
        String expectedMessage = String.format(UnlinkCommand.MESSAGE_SUCCESS, expectedNameList, JOBFEST.getName());
        expectedModel.updateFilteredContactList(new ContactIsInEventPredicate(expectedEvent));
        expectedModel.updateFilteredTaskList(new TaskIsInEventPredicate(expectedEvent));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedEvent, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void execute_validMultipleContacts_success() {
        model.addContact(BOB);
        expectedModel.addContact(BOB);
        Event expectedEvent = new EventBuilder(JOBFEST).withEventContacts().build();
        model.addEvent(JOBFEST);
        expectedModel.addEvent(expectedEvent);
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(ALICE.getName());
        contactNameList.add(BOB.getName());
        UnlinkCommand command = new UnlinkCommand(JOBFEST.getName(), contactNameList);

        expectedModel.updateFilteredContactList(new ContactIsInEventPredicate(expectedEvent));
        expectedModel.updateFilteredTaskList(new TaskIsInEventPredicate(expectedEvent));
        String expectedNameList = "[" + ALICE.getName() + ", " + BOB.getName() + "]";
        String expectedMessage = String.format(UnlinkCommand.MESSAGE_SUCCESS, expectedNameList, JOBFEST.getName());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedEvent, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void execute_mixedValidAndInvalidContacts_throwsCommandException() {
        model.addContact(BOB);
        model.addEvent(JOBFEST);

        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(ALICE.getName());
        contactNameList.add(BOB.getName());
        contactNameList.add(BENSON.getName());
        UnlinkCommand command = new UnlinkCommand(JOBFEST.getName(), contactNameList);

        String expectedMessage = String.format(UnlinkCommand.MESSAGE_UNLINKED_CONTACT,
                BENSON.getName(), JOBFEST.getName());
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(JOBFEST, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void execute_eventNotLinkedToContact_throwsCommandException() {
        model.addEvent(JOBFEST);
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(BENSON.getName());
        UnlinkCommand command = new UnlinkCommand(JOBFEST.getName(), contactNameList);

        String expectedMessage = String.format(UnlinkCommand.MESSAGE_UNLINKED_CONTACT,
                BENSON.getName(), JOBFEST.getName());
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(JOBFEST, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void execute_eventNotInTheList_throwsCommandException() {
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(ALICE.getName());

        UnlinkCommand command = new UnlinkCommand(JOBFEST.getName(), contactNameList);
        String expectedMessage = String.format(UnlinkCommand.MESSAGE_NO_SUCH_EVENT, JOBFEST.getName());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_contactNotInTheList_throwsCommandException() {
        Contact contact = new ContactBuilder().withName("Li Mei")
                .withAddress("123, East Coast Ave 6, #08-382").withEmail("limei@example.com")
                .withPhone("97292222")
                .withTags("friends").build();
        model.addEvent(JOBFEST);
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(contact.getName());

        UnlinkCommand command = new UnlinkCommand(JOBFEST.getName(), contactNameList);
        String expectedMessage = String.format(UnlinkCommand.MESSAGE_NO_SUCH_CONTACT, contact.getName());
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(JOBFEST, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void equals() {
        Set<Name> firstContactNameList = new HashSet<>();
        Set<Name> secondContactNameList = new HashSet<>();
        firstContactNameList.add(CARL.getName());
        secondContactNameList.add(BENSON.getName());

        UnlinkCommand firstUnlinkCommand = new UnlinkCommand(JOBFEST.getName(), firstContactNameList);
        UnlinkCommand secondUnlinkCommand = new UnlinkCommand(NTU.getName(), secondContactNameList);
        UnlinkCommand thirdUnlinkCommand = new UnlinkCommand(JOBFEST.getName(), secondContactNameList);
        UnlinkCommand fourthUnlinkCommand = new UnlinkCommand(NTU.getName(), firstContactNameList);

        // same object -> returns true
        assertTrue(firstUnlinkCommand.equals(firstUnlinkCommand));

        // same values -> returns true
        UnlinkCommand firstUnlinkCommandCopy = new UnlinkCommand(JOBFEST.getName(), firstContactNameList);
        assertTrue(firstUnlinkCommand.equals(firstUnlinkCommandCopy));

        // different types -> returns false
        assertFalse(firstUnlinkCommand.equals(1));

        // null -> returns false
        assertFalse(firstUnlinkCommand.equals(null));

        // different event and different contact -> returns false
        assertFalse(firstUnlinkCommand.equals(secondUnlinkCommand));

        // same event but different contact -> returns false
        assertFalse(firstUnlinkCommand.equals(thirdUnlinkCommand));

        // same contact but different event -> returns false
        assertFalse(firstUnlinkCommand.equals(fourthUnlinkCommand));
    }

    @Test
    public void toStringMethod() {
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(CARL.getName());
        contactNameList.add(BENSON.getName());

        UnlinkCommand command = new UnlinkCommand(JOBFEST.getName(), contactNameList);

        String expected = UnlinkCommand.class.getCanonicalName()
                + "{eventToUnlink=" + JOBFEST.getName() + ", "
                + "contactToUnlink=[" + CARL.getName() + ", " + BENSON.getName() + "]}";
        assertEquals(expected, command.toString());
    }
}
