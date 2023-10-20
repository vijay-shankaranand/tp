package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.PersonIsTaggedPredicate;
import seedu.address.model.tag.Tag;

/**
 * Filters persons in JobFestGo by {@code Tag}.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": filters and displays all persons who "
            + " are tagged with the input tag.\n"
            + "Parameters: "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "vendor ";

    private final List<Tag> tags;
    private final PersonIsTaggedPredicate predicate;

    /**
     * Creates a FilterCommand to filter persons in JobFestGo.
     */
    public FilterCommand(List<Tag> tags, PersonIsTaggedPredicate predicate) {
        this.tags = tags;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return tags.equals(otherFilterCommand.tags) && predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
