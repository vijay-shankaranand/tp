package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.contact.ViewContactsCommand;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.commands.event.ViewEventsCommand;
import seedu.address.logic.commands.tag.ViewTagsCommand;
import seedu.address.model.event.Event;
import seedu.address.testutil.event.EventBuilder;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));
        assertEquals(commandResult.shouldDisplayTagsPanel(), false);
        assertEquals(commandResult.shouldDisplayEventsPanel(), false);
        assertEquals(commandResult.shouldReturnToHome(), false);
        assertEquals(commandResult.shouldStayOnScreen(), false);

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));

        CommandResult viewTagCommandResult = new CommandResult(ViewTagsCommand.MESSAGE_SUCCESS);
        assertEquals(viewTagCommandResult.shouldDisplayTagsPanel(), true);

        CommandResult viewEventCommandResult = new CommandResult(ViewEventsCommand.MESSAGE_SUCCESS);
        assertEquals(viewEventCommandResult.shouldDisplayEventsPanel(), true);

        CommandResult homeCommandResult = new CommandResult(HomeCommand.MESSAGE_SUCCESS);
        assertEquals(homeCommandResult.shouldReturnToHome(), true);
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void getSelectedEvent() {
        CommandResult commandResult = new CommandResult("feedback", NTU, false);
        assertEquals(commandResult.getSelectedEvent(), NTU);

        // different event but with same name -> returns false
        Event ntuCopy = new EventBuilder().withName("NTU").build();
        assertNotEquals(commandResult.getSelectedEvent(), ntuCopy);
    }

    @Test
    public void isDeleteEvent() {
        CommandResult commandResult1 = new CommandResult("feedback", NTU, false);
        assertFalse(commandResult1.isDeleteEvent());

        CommandResult commandResult2 = new CommandResult("feedback", NTU, true);
        assertTrue(commandResult2.isDeleteEvent());
    }

    @Test
    public void shouldDisplayContactsPanel_correctFeedbackToUser_returnsTrue() {
        CommandResult viewContactCommandResult = new CommandResult(ViewContactsCommand.MESSAGE_SUCCESS);
        assertTrue(viewContactCommandResult.shouldDisplayContactsPanel());
    }
    @Test
    public void shouldDisplayContactsPanel_wrongFeedbackToUser_returnsFalse() {
        CommandResult commandResult = new CommandResult("feedback");
        assertFalse(commandResult.shouldDisplayContactsPanel());
    }

    @Test
    public void shouldHideAllPanels() {
        CommandResult commandResult = new CommandResult("feedback");
        assertTrue(commandResult.shouldHideAllPanels());

        CommandResult viewContactCommandResult = new CommandResult(ViewContactsCommand.MESSAGE_SUCCESS);
        assertFalse(viewContactCommandResult.shouldHideAllPanels());

        CommandResult viewTagCommandResult = new CommandResult(ViewTagsCommand.MESSAGE_SUCCESS);
        assertFalse(viewTagCommandResult.shouldHideAllPanels());

        CommandResult viewEventCommandResult = new CommandResult(ViewEventsCommand.MESSAGE_SUCCESS);
        assertFalse(viewEventCommandResult.shouldHideAllPanels());
    }

    @Test
    public void shouldReturnToHome() {
        // no selected event and not home command -> returns false
        CommandResult commandResult = new CommandResult("feedback");
        assertFalse(commandResult.shouldReturnToHome());

        // home command -> returns true
        CommandResult homeCommandResult = new CommandResult(HomeCommand.MESSAGE_SUCCESS);
        assertTrue(homeCommandResult.shouldReturnToHome());

        // selected event but not deleted event -> returns false
        CommandResult commandResultWithSelectedEvent1 = new CommandResult("feedback", NTU, false);
        assertFalse(commandResultWithSelectedEvent1.shouldReturnToHome());

        // incorrect delete event success message -> returns false
        CommandResult commandResultWithSelectedEvent2 = new CommandResult(
                DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, NTU, false);
        assertFalse(commandResultWithSelectedEvent2.shouldReturnToHome());

        // correct delete event success message -> returns true
        String correctedDeletedEventSuccessMsg = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(NTU));
        CommandResult commandResultWithCorrectedMsg = new CommandResult(
                correctedDeletedEventSuccessMsg, NTU, false);
        assertTrue(commandResultWithCorrectedMsg.shouldReturnToHome());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
