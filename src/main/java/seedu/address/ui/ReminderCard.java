package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task} that is due soon.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderCard.fxml";

    public final Task task;

    @FXML
    private HBox cardPane;

    @FXML
    private Label description;

    @FXML
    private Label id;

    @FXML
    private Label eventName;

    @FXML
    private Label deadline;

    /**
     * Creates a {@code ReminderCard} with the given {@code task} and index to display.
     */
    public ReminderCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().toString());
        eventName.setText(task.getAssociatedEventName().toString());
        deadline.setText(task.getDate().date);
    }
}
