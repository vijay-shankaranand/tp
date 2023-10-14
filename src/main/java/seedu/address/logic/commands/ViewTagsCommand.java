package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all tags currently used by the user to categorise contacts.
 */
public class ViewTagsCommand extends Command {

    public static final String COMMAND_WORD = "view_tags";

    public static final String MESSAGE_SUCCESS = "Listed all tags currently in use";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(Model.PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
