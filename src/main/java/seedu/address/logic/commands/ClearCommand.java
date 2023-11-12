package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.JobFestGo;
import seedu.address.model.Model;

/**
 * Clears JobFestGo.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Job Fest Go has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setJobFestGo(new JobFestGo());

        // Update model to depict which screen it is on currently.
        model.switchToContactsScreen(false);
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
