package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BOB;
import static seedu.address.testutil.person.TypicalPersons.CARL;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.person.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.testutil.event.EventBuilder;
import seedu.address.testutil.person.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code LinkCommand}.
 */
public class LinkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validEventNameAndContactName_success() {
        Event event = new EventBuilder().withEventName("JobFest 2023")
                .withEventDate("2023-12-12")
                .withEventAddress("3 Temasek Blvd, Singapore 038983")
                .withEventContacts(ALICE, BOB)
                .build();
        Event expectedEvent = new EventBuilder().withEventName("JobFest 2023")
                .withEventDate("2023-12-12")
                .withEventAddress("3 Temasek Blvd, Singapore 038983")
                .withEventContacts(ALICE, BOB, CARL)
                .build();

        LinkCommand command = new LinkCommand(event.getName(), CARL.getName());
        String expectedMessage = String.format(LinkCommand.MESSAGE_SUCCESS, CARL.getName(), event.getName());
        model.addEvent(event);
        expectedModel.addEvent(expectedEvent);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedEvent.getContacts(), event.getContacts());
    }

    @Test
    public void execute_eventAlreadyLinkedToContact_throwsCommandException() {
        Event event = new EventBuilder().withEventName("JobFest 2023")
                .withEventDate("2023-12-12")
                .withEventAddress("3 Temasek Blvd, Singapore 038983")
                .withEventContacts(ALICE, BOB)
                .build();

        LinkCommand command = new LinkCommand(event.getName(), ALICE.getName());
        String expectedMessage = String.format(LinkCommand.MESSAGE_LINKED_CONTACT, ALICE.getName(), event.getName());
        model.addEvent(event);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_eventNotInTheList_throwsCommandException() {
        Event event = new EventBuilder().withEventName("JobFest 2023")
                .withEventDate("2023-12-12")
                .withEventAddress("3 Temasek Blvd, Singapore 038983")
                .withEventContacts(ALICE, BOB)
                .build();

        LinkCommand command = new LinkCommand(event.getName(), ALICE.getName());
        String expectedMessage = String.format(LinkCommand.MESSAGE_NO_SUCH_EVENT, event.getName());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_contactNotInTheList_throwsCommandException() {
        Event event = new EventBuilder().withEventName("JobFest 2023")
                .withEventDate("2023-12-12")
                .withEventAddress("3 Temasek Blvd, Singapore 038983")
                .withEventContacts(ALICE, BOB)
                .build();
        Person contact = new PersonBuilder().withName("Li Mei")
                .withAddress("123, East Coast Ave 6, #08-382").withEmail("limei@example.com")
                .withPhone("97292222")
                .withTags("friends").build();

        LinkCommand command = new LinkCommand(event.getName(), contact.getName());
        model.addEvent(event);
        String expectedMessage = String.format(LinkCommand.MESSAGE_NO_SUCH_CONTACT, contact.getName());
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        LinkCommand firstLinkCommand = new LinkCommand(JOBFEST.getName(), CARL.getName());
        LinkCommand secondLinkCommand = new LinkCommand(NTU.getName(), BENSON.getName());
        LinkCommand thirdLinkCommand = new LinkCommand(JOBFEST.getName(), BENSON.getName());
        LinkCommand fourthLinkCommand = new LinkCommand(NTU.getName(), CARL.getName());

        // same object -> returns true
        assertTrue(firstLinkCommand.equals(firstLinkCommand));

        // same values -> returns true
        LinkCommand firstLinkCommandCopy = new LinkCommand(JOBFEST.getName(), CARL.getName());
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
        LinkCommand command = new LinkCommand(JOBFEST.getName(), CARL.getName());

        String expected = LinkCommand.class.getCanonicalName()
                + "{eventToLink=" + JOBFEST.getName() + ", "
                + "contactToLink=" + CARL.getName() + "}";
        assertEquals(expected, command.toString());
    }
}
