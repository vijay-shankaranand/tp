package seedu.address.logic.commands.event;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ViewEventsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
        expectedModel = new ModelManager(model.getJobFestGo(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewEventsCommand(), model, ViewEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
