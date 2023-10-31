package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
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

    private static final EventHandler<MouseEvent> handler = MouseEvent::consume;

    private Logic logic;

    //Independent Ui parts residing in this Ui container
    private ContactListPanel ContactListPanel;
    private EventListPanel eventListPanel;
    private ReminderListPanel reminderListPanel;
    private TaskListPanel taskListPanel;

    @FXML
    private GridPane eventContactDisplay;

    @FXML
    private StackPane ContactListPanelPlaceholder;

    @FXML
    private StackPane eventListPanelPlaceholder;

    @FXML
    private StackPane taskPanelPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Logic}.
     */
    public EventContactDisplay(Logic logic, boolean shouldReset) {
        super(FXML);
        this.logic = logic;

        // Disable mouse events for all panels
        ContactListPanelPlaceholder.addEventFilter(MouseEvent.ANY, handler);
        eventListPanelPlaceholder.addEventFilter(MouseEvent.ANY, handler);
        taskPanelPlaceholder.addEventFilter(MouseEvent.ANY, handler);

        if (shouldReset) {
            fillInnerPartsAfterReset();
        } else {
            fillInnerParts();
        }
    }
    /**
     * Fills up all the placeholders of this window.
     */
    private void fillInnerParts() {
        ContactListPanel = new ContactListPanel(logic.getFilteredContactList());
        ContactListPanelPlaceholder.getChildren().add(ContactListPanel.getRoot());

        eventListPanel = new EventListPanel(logic.getFilteredEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());

        reminderListPanel = new ReminderListPanel(logic.getTasksDueSoonList());
        taskPanelPlaceholder.getChildren().add(reminderListPanel.getRoot());
    }

    /**
     * Fills up the task list panel with the filtered task list when an event is selected.
     */
    private void fillTaskList() {
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        if (!taskPanelPlaceholder.getChildren().isEmpty()) {
            taskPanelPlaceholder.getChildren().clear();
        }
        taskPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

    /**
     * Fills up all the placeholders of this window after reset.
     */
    public void fillInnerPartsAfterReset() {
        ContactListPanel = new ContactListPanel(logic.getUnfilteredContactList());
        ContactListPanelPlaceholder.getChildren().add(ContactListPanel.getRoot());

        eventListPanel = new EventListPanel(logic.getUnfilteredEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());

        reminderListPanel = new ReminderListPanel(logic.getTasksDueSoonList());
        taskPanelPlaceholder.getChildren().add(reminderListPanel.getRoot());
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
