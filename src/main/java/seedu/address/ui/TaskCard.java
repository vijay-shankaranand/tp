package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskCard.fxml";

    public final Task task;

    @FXML
    private HBox cardPane;

    @FXML
    private Label description;

    @FXML
    private Label id;

    @FXML
    private Label completed;

    @FXML
    private Label dueSoon;

    @FXML
    private Label overdue;

    @FXML
    private Label deadline;

    /**
     * Creates a {@code TaskCard} with the given {@code task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().toString());
        deadline.setText(task.getDate().date);

        if (task.isCompleted()) {
            completed.setText("Completed");
            overdue.setText("");
            overdue.setVisible(false);
            dueSoon.setText("");
            dueSoon.setVisible(false);
        } else {
            if (task.isDueWithinThreeDays() && !task.isOverdue()) {
                dueSoon.setText("Due Soon");
                overdue.setText("");
                overdue.setVisible(false);
            } else if (task.isOverdue()) {
                overdue.setText("Overdue");
                dueSoon.setText("");
                dueSoon.setVisible(false);
            } else {
                dueSoon.setText("");
                dueSoon.setVisible(false);
                overdue.setText("");
                overdue.setVisible(false);
            }
            completed.setText("");
            completed.setVisible(false);
        }
    }
}
