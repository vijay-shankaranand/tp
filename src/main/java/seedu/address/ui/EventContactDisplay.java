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

    @FXML
    private GridPane eventContactDisplay;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane eventListPanelPlaceholder;

    @FXML
    private StackPane eventCardPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Logic}.
     */
    public EventContactDisplay(Logic logic, boolean shouldReset) {
        super(FXML);
        this.logic = logic;
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        eventListPanel = new EventListPanel(logic.getFilteredEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());
    }

    /**
     * Fills up all the placeholders of this window after reset.
     */
    public void fillInnerPartsAfterReset() {
        personListPanel = new PersonListPanel(logic.getUnfilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        eventListPanel = new EventListPanel(logic.getUnfilteredEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());
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
        }
    }
}
