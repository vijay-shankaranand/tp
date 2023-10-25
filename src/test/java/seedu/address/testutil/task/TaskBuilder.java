package seedu.address.testutil.task;

import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.event.EventBuilder;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_TASK_NAME = "Call Manager";
    public static final String DEFAULT_TASK_DATE = "2023-12-10";
    private TaskDescription description;
    private Date date;
    private Event event;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        description = new TaskDescription(DEFAULT_TASK_NAME);
        date = new Date(DEFAULT_TASK_DATE);
        event = new EventBuilder().build();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        date = taskToCopy.getDate();
        event = taskToCopy.getAssociatedEvent();
    }

    /**
     * Sets the {@code Event} of the {@code Task} that we are building.
     */
    public TaskBuilder withEvent(Event event) {
        this.event = event;
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    public Task build() {
        return new Task(description, date, event);
    }
}
