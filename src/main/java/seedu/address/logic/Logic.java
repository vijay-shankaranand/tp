package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the JobFestGo.
     *
     * @see seedu.address.model.Model#getJobFestGo()
     */
    ReadOnlyJobFestGo getJobFestGo();

    /** Returns an unmodifiable view of the filtered list of contacts */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the unfiltered list of contacts */

    ObservableList<Contact> getUnfilteredContactList();

    /** Returns an unmodifiable view of the filtered list of tags */
    ObservableList<Tag> getFilteredTagList();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the unfiltered list of events */
    ObservableList<Event> getUnfilteredEventList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the tasks due soon */
    ObservableList<Task> getTasksDueSoonList();

    /**
     * Returns the user prefs' JobFestGo file path.
     */
    Path getJobFestGoFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
