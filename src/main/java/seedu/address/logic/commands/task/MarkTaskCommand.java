package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsInEventPredicate;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.exceptions.TaskIsCompletedException;
import seedu.address.model.task.TaskIsInEventPredicate;

/**
 * Marks the specified task as completed.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as completed. "
            + "Parameters: "
            + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION "
            + PREFIX_EVENT + "EVENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "Book Venue "
            + PREFIX_EVENT + "NUS Career Fair 2023";

    public static final String MESSAGE_SUCCESS = "Marked task: %1$s from event: %2$s as completed";
    public static final String MESSAGE_MISSING_TASK = "This task does not exist";
    public static final String MESSAGE_COMPLETED_TASK = "This task has already been completed.";
    private final TaskDescription taskDescription;
    private final Name associatedEventName;

    /**
     * Creates a MarkCommand to delete the specified {@code task}.
     */
    public MarkTaskCommand(TaskDescription taskDescription, Name associatedEventName) {
        requireAllNonNull(taskDescription, associatedEventName);
        this.taskDescription = taskDescription;
        this.associatedEventName = associatedEventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTask(taskDescription, associatedEventName)) {
            throw new CommandException(MESSAGE_MISSING_TASK);
        }

        try {
            model.markTask(taskDescription, associatedEventName);
        } catch (TaskIsCompletedException tice) {
            throw new CommandException(MESSAGE_COMPLETED_TASK);
        }

        // Get the selected event after marking task
        Event selectedEvent = model.getEvent(associatedEventName);

        // Update the respective filtered lists to show the components within the event
        model.updateFilteredTaskList(new TaskIsInEventPredicate(selectedEvent));
        model.updateFilteredContactList(new ContactIsInEventPredicate(selectedEvent));

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskDescription, associatedEventName),
                taskDescription, selectedEvent, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkTaskCommand)) {
            return false;
        }

        MarkTaskCommand otherMarkTaskCommand = (MarkTaskCommand) other;
        return taskDescription.equals(otherMarkTaskCommand.taskDescription)
                && associatedEventName.equals(otherMarkTaskCommand.associatedEventName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toMark", taskDescription)
                .add("associatedEvent", associatedEventName)
                .toString();
    }
}
