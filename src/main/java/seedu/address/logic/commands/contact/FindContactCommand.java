package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.name.NameContainsKeywordsPredicate;

/**
 * Finds and lists all contacts in JobFestGo whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindContactCommand extends Command {
    public static final String COMMAND_WORD = "find_contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alex david charlotte";

    private final NameContainsKeywordsPredicate predicate;

    public FindContactCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isOnEventsScreen() || model.isOnTagsScreen()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_NOT_DISPLAYED);
        }
        // Update the filtered lists accordingly.
        // Flow of command allows app to stay on its current screen.
        Predicate<Contact> prevContactPred = model.getContactListPredicate();

        // Update model to depict which screen it is on currently.
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);

        if (prevContactPred != null) {
            model.updateFilteredContactList(predicate.and(prevContactPred));
        } else {
            model.updateFilteredContactList(predicate);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW,
                model.getFilteredContactList().size()), true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindContactCommand)) {
            return false;
        }

        FindContactCommand otherFindCommand = (FindContactCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
