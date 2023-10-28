package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

public class CompletedTaskCard extends UiPart<Region> {
    
    private static final String FXML = "CompletedTaskCard.fxml";

    public final Task task;

    @FXML
    private HBox completedCardPane;

    @FXML
    private Label description;

    @FXML
    private Label id;

    @FXML
    private Label deadline;

    /**
     * Creates a {@code TaskCard} with the given {@code task} and index to display.
     */
    public CompletedTaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().toString());
        deadline.setText(task.getDate().date);
    }
}
