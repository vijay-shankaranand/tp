package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.TypicalEvents;

public class SelectEventCommandTest {

    private Model model = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SelectEventCommand(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        SelectEventCommand selectEventCommand = new SelectEventCommand(INDEX_FIRST_EVENT);
        Event event = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        String expectedMessage = String.format(SelectEventCommand.MESSAGE_SELECT_EVENT_SUCCESS, Messages.format(event));
        assertCommandSuccess(selectEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SelectEventCommand selectEventCommand = new SelectEventCommand(INDEX_FIRST_EVENT);
        assertThrows(NullPointerException.class, () -> selectEventCommand.execute(null));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        SelectEventCommand selectEventCommand = new SelectEventCommand(INDEX_SECOND_EVENT);
        assertThrows(CommandException.class, () -> selectEventCommand.execute(model));
    }

    @Test
    public void equals() {
        SelectEventCommand firstSelect = new SelectEventCommand(INDEX_FIRST_EVENT);
        SelectEventCommand secondSelect = new SelectEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertEquals(firstSelect, firstSelect);

        // same values -> returns true
        SelectEventCommand firstSelectCopy = new SelectEventCommand(INDEX_FIRST_EVENT);
        assertEquals(firstSelect, firstSelectCopy);

        // different types -> returns false
        assertNotEquals(firstSelect, 1);

        // null -> returns false
        assertNotEquals(firstSelect, null);

        // different event -> returns false
        assertNotEquals(firstSelect, secondSelect);
    }

    @Test
    public void toString_testEqual() {
        SelectEventCommand selectEventCommand = new SelectEventCommand(INDEX_FIRST_EVENT);
        String expectedtoString = new ToStringBuilder(selectEventCommand)
            .add("targetIndex", INDEX_FIRST_EVENT).toString();
        assertEquals(selectEventCommand.toString(), expectedtoString);
    }
}
