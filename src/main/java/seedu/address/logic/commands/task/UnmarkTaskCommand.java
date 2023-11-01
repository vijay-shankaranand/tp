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
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskDescription;

/**
 * Marks the specified task as not completed.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as not completed. "
            + "Parameters: "
            + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION "
            + PREFIX_EVENT + "EVENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "Book Venue "
            + PREFIX_EVENT + "NUS Career Fair 2023";

    public static final String MESSAGE_SUCCESS = "Unmarked task: %1$s from event: %2$s";
    public static final String MESSAGE_MISSING_TASK = "This task does not exist";
    private final TaskDescription taskDescription;
    private final Name associatedEventName;

    /**
     * Creates a UnmarkCommand to delete the specified {@code task}.
     */
    public UnmarkTaskCommand(TaskDescription taskDescription, Name associatedEventName) {
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

        model.unmarkTask(taskDescription, associatedEventName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskDescription, associatedEventName),
                taskDescription, model.getEvent(associatedEventName), true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnmarkTaskCommand)) {
            return false;
        }

        UnmarkTaskCommand otherUnmarkTaskCommand = (UnmarkTaskCommand) other;
        return taskDescription.equals(otherUnmarkTaskCommand.taskDescription)
                && associatedEventName.equals(otherUnmarkTaskCommand.associatedEventName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toUnmark", taskDescription)
                .add("associatedEvent", associatedEventName)
                .toString();
    }
}
