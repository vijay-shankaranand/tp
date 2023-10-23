package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.event.Event;

/**
 * Panel containing events list and contacts list.
 */
public class EventContactDisplay extends UiPart<Region> {

    private static final String FXML = "EventContactDisplay.fxml";

    private static final Logger logger = LogsCenter.getLogger(EventContactDisplay.class);

    private Logic logic;

    //Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private EventListPanel eventListPanel;
    private ReminderListPanel reminderListPanel;
    private TaskListPanel taskListPanel;

    @FXML
    private GridPane eventContactDisplay;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane eventListPanelPlaceholder;

    @FXML
    private StackPane reminderTaskListPanelPlaceholder;

    @FXML
    private StackPane eventCardPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Logic}.
     */
    public EventContactDisplay(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillInnerParts();
    }
    /**
     * Fills up all the placeholders of this window.
     */
    private void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        eventListPanel = new EventListPanel(logic.getFilteredEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());

        reminderListPanel = new ReminderListPanel(logic.getFilteredEventList());
        reminderTaskListPanelPlaceholder.getChildren().add(reminderListPanel.getRoot());
    }

    /**
     * Fills up the task list panel with the filtered task list when an event is selected.
     */
    private void fillTaskList() {
        taskListPanel = new TaskListPanel(logic.getFilteredEventList());
        if (!reminderTaskListPanelPlaceholder.getChildren().isEmpty()) {
                reminderTaskListPanelPlaceholder.getChildren().clear();
        }
        reminderTaskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

    /**
     * Sets the feedback to user in the case that there is a change in UI.
     *
     * @param commandResult the command result of command executed.
     */
    public void setFeedbackToUser(CommandResult commandResult) {
        Event selectedEvent = commandResult.getSelectedEvent();
        if (selectedEvent != null) {
            eventListPanel.selectEvent(selectedEvent);
            this.fillTaskList();
        }
    }
}
