package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Panel containing events list and contacts list.
 */
public class EventContactDisplay extends UiPart<Region> {

    private static final String FXML = "EventContactDisplay.fxml";

    private static final Logger logger = LogsCenter.getLogger(EventContactDisplay.class);

    private Logic logic;

    //Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private TagListPanel tagListPanel;

    @FXML
    private GridPane eventContactDisplay;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane tagListPanelPlaceholder;

    @FXML
    private StackPane tagCardPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Logic}.
     */
    public EventContactDisplay(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillInnerParts();
    }
    /**
     * Fills up all the placeholders of this window
     */
    private void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        tagListPanel = new TagListPanel(logic.getFilteredTagList());
        tagListPanelPlaceholder.getChildren().add(tagListPanel.getRoot());
    }
}
