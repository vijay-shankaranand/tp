package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsInEventPredicate;
import seedu.address.model.event.Event;
import seedu.address.model.task.TaskIsInEventPredicate;

/**
 * Selects an event identified using its displayed index from the JobFestGo.
 */
public class SelectEventCommand extends Command {
    public static final String COMMAND_WORD = "select_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_EVENT_SUCCESS = "Selected Event: %1$s";

    private final Index targetIndex;

    /**
     * Creates a SelectEventCommand to select the specified {@code Event} by its {@code targetIndex}.
     */
    public SelectEventCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (model.isOnContactsScreen() || model.isOnTagsScreen()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_NOT_DISPLAYED);
        }

        if (targetIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        // Get the event to be selected.
        Event eventToBeSelected = lastShownEventList.get(targetIndex.getZeroBased());

        // Get the predicates necessary for the respective filtered lists.
        ContactIsInEventPredicate predicate = new ContactIsInEventPredicate(eventToBeSelected);
        TaskIsInEventPredicate taskPredicate = new TaskIsInEventPredicate(eventToBeSelected);

        // Update model to depict which screen it is on currently.
        model.switchToContactsScreen(false);
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);

        // Update the respective filtered lists to show the components within the event.
        model.updateFilteredContactList(predicate);
        model.updateFilteredTaskList(taskPredicate);

        return new CommandResult(String.format(MESSAGE_SELECT_EVENT_SUCCESS,
            Messages.format(eventToBeSelected)), eventToBeSelected, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SelectEventCommand)) {
            return false;
        }

        SelectEventCommand otherSelectEventCommand = (SelectEventCommand) other;
        return targetIndex.equals(otherSelectEventCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
