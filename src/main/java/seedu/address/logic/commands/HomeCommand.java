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
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
