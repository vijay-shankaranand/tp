package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;

import org.junit.jupiter.api.Test;

import seedu.address.model.JobFestGo;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyJobFestGo_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyJobFestGo_success() {
        Model model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
        expectedModel.setJobFestGo(new JobFestGo());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
