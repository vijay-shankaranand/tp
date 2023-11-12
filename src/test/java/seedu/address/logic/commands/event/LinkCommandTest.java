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
 * Contains integration tests (interaction with the Model) for {@code LinkCommand}.
 */
public class LinkCommandTest {
    private Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJobFestGo(), new UserPrefs());

    @Test
    public void execute_validSingleContact_success() {
        Event expectedEvent = new EventBuilder(JOBFEST).withEventContacts(ALICE, BOB, CARL).build();
        model.addEvent(JOBFEST);
        expectedModel.addEvent(expectedEvent);
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(CARL.getName());
        LinkCommand linkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);

        String expectedNameList = "[" + CARL.getName() + "]";
        String expectedMessage = String.format(LinkCommand.MESSAGE_SUCCESS, expectedNameList, JOBFEST.getName());
        expectedModel.updateFilteredContactList(new ContactIsInEventPredicate(expectedEvent));
        expectedModel.updateFilteredTaskList(new TaskIsInEventPredicate(expectedEvent));

        assertCommandSuccess(linkCommand, model, expectedMessage, expectedModel);
        assertEquals(expectedEvent, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void execute_validMultipleContacts_success() {
        Event expectedEvent = new EventBuilder(JOBFEST).withEventContacts(ALICE, BOB, CARL, BENSON).build();
        model.addEvent(JOBFEST);
        expectedModel.addEvent(expectedEvent);
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(CARL.getName());
        contactNameList.add(BENSON.getName());
        LinkCommand linkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);

        String expectedNameList = "[" + CARL.getName() + ", " + BENSON.getName() + "]";
        String expectedMessage = String.format(LinkCommand.MESSAGE_SUCCESS, expectedNameList, JOBFEST.getName());
        expectedModel.updateFilteredContactList(new ContactIsInEventPredicate(expectedEvent));
        expectedModel.updateFilteredTaskList(new TaskIsInEventPredicate(expectedEvent));

        assertCommandSuccess(linkCommand, model, expectedMessage, expectedModel);
        assertEquals(expectedEvent, model.getEvent(JOBFEST.getName()));
    }


    @Test
    public void execute_mixedValidAndInvalidContacts_throwsCommandException() {
        model.addEvent(JOBFEST);

        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(ALICE.getName());
        contactNameList.add(BENSON.getName());
        LinkCommand linkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);

        String expectedMessage = String.format(LinkCommand.MESSAGE_LINKED_CONTACT,
                ALICE.getName(), JOBFEST.getName());
        assertCommandFailure(linkCommand, model, expectedMessage);
        assertEquals(JOBFEST, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void execute_eventLinkedToContact_throwsCommandException() {
        model.addEvent(JOBFEST);
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(ALICE.getName());
        LinkCommand linkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);

        String expectedMessage = String.format(LinkCommand.MESSAGE_LINKED_CONTACT,
                ALICE.getName(), JOBFEST.getName());
        assertCommandFailure(linkCommand, model, expectedMessage);
        assertEquals(JOBFEST, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void execute_eventNotInTheList_throwsCommandException() {
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(ALICE.getName());

        LinkCommand linkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);
        String expectedMessage = String.format(LinkCommand.MESSAGE_NO_SUCH_EVENT, JOBFEST.getName());
        assertCommandFailure(linkCommand, model, expectedMessage);
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

        LinkCommand linkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);
        String expectedMessage = String.format(LinkCommand.MESSAGE_NO_SUCH_CONTACT, contact.getName());
        assertCommandFailure(linkCommand, model, expectedMessage);
        assertEquals(JOBFEST, model.getEvent(JOBFEST.getName()));
    }

    @Test
    public void equals() {
        Set<Name> firstContactNameList = new HashSet<>();
        Set<Name> secondContactNameList = new HashSet<>();
        firstContactNameList.add(CARL.getName());
        secondContactNameList.add(BENSON.getName());

        LinkCommand firstLinkCommand = new LinkCommand(JOBFEST.getName(), firstContactNameList);
        LinkCommand secondLinkCommand = new LinkCommand(NTU.getName(), secondContactNameList);
        LinkCommand thirdLinkCommand = new LinkCommand(JOBFEST.getName(), secondContactNameList);
        LinkCommand fourthLinkCommand = new LinkCommand(NTU.getName(), firstContactNameList);

        // same object -> returns true
        assertTrue(firstLinkCommand.equals(firstLinkCommand));

        // same values -> returns true
        LinkCommand firstLinkCommandCopy = new LinkCommand(JOBFEST.getName(), firstContactNameList);
        assertTrue(firstLinkCommand.equals(firstLinkCommandCopy));

        // different types -> returns false
        assertFalse(firstLinkCommand.equals(1));

        // null -> returns false
        assertFalse(firstLinkCommand.equals(null));

        // different event and different contact -> returns false
        assertFalse(firstLinkCommand.equals(secondLinkCommand));

        // same event but different contact -> returns false
        assertFalse(firstLinkCommand.equals(thirdLinkCommand));

        // same contact but different event -> returns false
        assertFalse(firstLinkCommand.equals(fourthLinkCommand));
    }

    @Test
    public void toStringMethod() {
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(CARL.getName());
        contactNameList.add(BENSON.getName());

        LinkCommand linkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);

        String expected = LinkCommand.class.getCanonicalName()
                + "{eventToLink=" + JOBFEST.getName() + ", "
                + "contactToLink=[" + CARL.getName() + ", " + BENSON.getName() + "]}";
        assertEquals(expected, linkCommand.toString());
    }
}
