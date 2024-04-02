package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private static final String STARTING_MESSAGE = "Welcome to SweetRewards!" + "\n"
            + "- Type 'help --commands' to see the list of commands available." + "\n"
            + "- Type 'help' to view the user guide online.";

    @FXML
    private TextArea resultDisplay;

    /**
     * Creates a {@code ResultDisplay} with the given {@code String}.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplay.setText(STARTING_MESSAGE);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

}
