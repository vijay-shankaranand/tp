package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.event.LinkCommand.MESSAGE_NO_SUCH_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsInEventPredicate;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.name.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskIsInEventPredicate;

/**
 * Adds a task to the event.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "add_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the specified event. "
            + "Parameters: "
            + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION "
            + PREFIX_DATE + "TASK_DEADLINE "
            + PREFIX_EVENT + "EVENT_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "Book Venue "
            + PREFIX_DATE + "2023-12-23 "
            + PREFIX_EVENT + "NUS Career Fair 2023";

    public static final String MESSAGE_SUCCESS = "Task [%1$s] by [%2$s] added to event: %3$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the event";
    private final TaskDescription taskDescription;
    private final Date taskDeadline;
    private final Name associatedEventName;
    private Task taskToAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(TaskDescription taskDescription, Date taskDeadline, Name associatedEvent) {
        requireAllNonNull(taskDescription, taskDeadline, associatedEvent);
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        this.associatedEventName = associatedEvent;
        taskToAdd = new Task(taskDescription, taskDeadline, associatedEvent, false);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {

            if (!Date.isDateTodayOrAfter(taskDeadline)) {
                throw new CommandException(Date.MESSAGE_CONSTRAINTS);
            }

            Event eventToAddIn = model.getEvent(associatedEventName);
            taskToAdd = new Task(taskDescription, taskDeadline, eventToAddIn, false);

            if (model.hasTask(taskToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }

            model.addTask(taskToAdd);

            // Event is updated, thus need additional step to get the event.
            Event selectedEvent = model.getEvent(associatedEventName);

            // Update the respective filtered lists to show the components within the event.
            model.updateFilteredContactList(new ContactIsInEventPredicate(eventToAddIn));
            model.updateFilteredTaskList(new TaskIsInEventPredicate(eventToAddIn));

            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    taskDescription, taskDeadline, eventToAddIn.getName()),
                    taskDescription, selectedEvent, true);
        } catch (EventNotFoundException enfe) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_EVENT, associatedEventName));
        }
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
