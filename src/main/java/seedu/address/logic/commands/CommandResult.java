package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.contact.ViewContactsCommand;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.commands.event.ViewEventsCommand;
import seedu.address.logic.commands.tag.ViewTagsCommand;
import seedu.address.model.event.Event;
import seedu.address.model.task.TaskDescription;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Event selected by the user through command. */
    private Event selectedEvent = null;

    /** Boolean that represents whether the event passed through is being deleted. */
    private boolean isEventDeleted = false;

    /** Description of task marked/unmarked by user through command. */
    private TaskDescription taskDescription = null;

    /** Boolean that represents whether the UI should go to a Select Event screen. */
    private boolean isSelectScreen = false;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and {@code Event} selected by the user.
     */
    public CommandResult(String feedbackToUser, Event eventSelected, boolean isEventDeleted) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.selectedEvent = eventSelected;
        this.isEventDeleted = isEventDeleted;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and {@code TaskDescription} and {@code Name} of {@code Event} associated with task,
     * as well as {@code isSelectScreen} to determine whether to go to Select Event screen.
     * Used for the respective task commands.
     */
    public CommandResult(String feedbackToUser, TaskDescription desc, Event event, boolean isSelectScreen) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.taskDescription = desc;
        this.selectedEvent = event;
        this.isSelectScreen = isSelectScreen;

    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * Returns true if the tags panel should be displayed to the user, else false.
     * @return true if the tags panel should be displayed to the user, else false
     */
    public boolean shouldDisplayTagsPanel() {
        if (feedbackToUser.equals(ViewTagsCommand.MESSAGE_SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the contacts panel should be displayed to the user, else false.
     * @return true if the contacts panel should be displayed to the user, else false
     */
    public boolean shouldDisplayContactsPanel() {
        if (feedbackToUser.equals(ViewContactsCommand.MESSAGE_SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the events panel should be displayed to the user, else false.
     * @return true if the events panel should be displayed to the user, else false
     */
    public boolean shouldDisplayEventsPanel() {
        if (feedbackToUser.equals(ViewEventsCommand.MESSAGE_SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if all panels should be hidden from the user, else false.
     * @return true if all panels should be hidden from the user, else false
     */
    public boolean shouldHideAllPanels() {
        return !shouldDisplayTagsPanel() && !shouldDisplayContactsPanel() && !shouldDisplayEventsPanel();
    }

    /**
     * Returns true if the event passed through is being deleted.
     * @return true if the event passed through is being deleted
     */
    public boolean isDeleteEvent() {
        return isEventDeleted;
    }

    /**
     * Returns true if the home panel should be displayed to the user, else false.
     * @return true if the home panel should be displayed to the user, else false
     */
    public boolean shouldReturnToHome() {
        if (feedbackToUser.equals(HomeCommand.MESSAGE_SUCCESS)) {
            return true;
        }
        if (selectedEvent != null) {
            String deleteFeedback = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(selectedEvent));
            if (feedbackToUser.equals(deleteFeedback)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Upon marking a task as completed, remain on the selected event.
     * @return true if the current screen should remain displayed, else false
     */
    public boolean shouldStayOnScreen() {
        return this.isSelectScreen;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }
}
