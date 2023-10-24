package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the event.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "add_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an task to the specified event. "
            + "Parameters: "
            + PREFIX_NAME + "TASK_DESCRIPTION "
            + PREFIX_DATE + "TASK_DEADLINE "
            + PREFIX_EVENT + "EVENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Book Venue "
            + PREFIX_DATE + "2023-12-23 "
            + PREFIX_EVENT + "NUS Career Fair 2023";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the event";
    private final Task taskToAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        taskToAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(taskToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.addTask(taskToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(taskToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }
        AddTaskCommand otherAddCommand = (AddTaskCommand) other;
        return taskToAdd.equals(otherAddCommand.taskToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", taskToAdd)
                .toString();
    }
}
