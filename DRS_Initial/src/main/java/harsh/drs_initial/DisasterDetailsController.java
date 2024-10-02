package harsh.drs_initial;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author harsh
 */
public class DisasterDetailsController {
    @FXML
    private Label typeLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label severityLabel;
    @FXML
    private TextArea logTextArea;

    public void setDisaster(Disaster disaster) {
        typeLabel.setText(disaster.getDisasterType());
        locationLabel.setText(disaster.getLocation());
        severityLabel.setText(String.valueOf(disaster.getSeverity()));
        logTextArea.setText(disaster.getLogs());
    }
    
     @FXML
    private void switchToPrimary() throws IOException {
         System.out.println("Switching to Primary");
        App.setRoot("primary");
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
//        initialize();
        App.setRoot("secondary");
    }
    @FXML
    private void switchToCoordinate() throws IOException {
        App.setRoot("coordinate");
    }
}