package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Returns to the home screen of the application.
 */
public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "Back to the home page...";

    @Override
    public CommandResult execute(Model model) {
        // Update model to depict which screen it is on currently.
        model.switchToContactsScreen(false);
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);

        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
