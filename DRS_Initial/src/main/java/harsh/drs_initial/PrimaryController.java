package harsh.drs_initial;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class PrimaryController {
    
    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;
    
    @FXML
    private RadioButton radioButton4;

    @FXML
    private TextField locationField;

    @FXML
    private TextField severityField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Button submitButton;

    @FXML
    private Button clearButton;

    private ToggleGroup radioGroup;
    private List<Disaster> disasterList;  // List to store disasters

    @FXML
    public void initialize() {
        // Create a ToggleGroup
        radioGroup = new ToggleGroup();

        // Assign the ToggleGroup to the radio buttons
        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        radioButton3.setToggleGroup(radioGroup);
        radioButton4.setToggleGroup(radioGroup);

        // Set the default selected button (optional)
        radioButton1.setSelected(true);
        
         // Set up event handling for buttons
        submitButton.setOnAction(e -> handleSubmit());
        clearButton.setOnAction(e -> handleClear());
        disasterList = new ArrayList<>();
    }
    
    private void handleSubmit() {
        // Retrieve data from the form
        String selectedDisaster = ((RadioButton) radioGroup.getSelectedToggle()).getText();
        String location = locationField.getText();
        int severity = Integer.parseInt(severityField.getText());
        String description = descriptionArea.getText();

        // Process the data (for now, just print it to the console)
        // Create a Disaster object
        Disaster disaster = new Disaster(selectedDisaster, location, severity, description);

        // Add the disaster to the list
        disasterList.add(disaster);
        
        // Write the disaster to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("disasters.csv", true))) {
            writer.write(selectedDisaster + "," + location + "," + severity + "," + description + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Here you can add logic to save the data, send it to a server, etc.
        handleClear();
        prioritizeDisasters();
    }

    private void handleClear() {
        // Clear all the fields
        locationField.clear();
        severityField.clear();
        descriptionArea.clear();
        radioGroup.selectToggle(radioButton1);  // Reset radio selection to the first option
    }

     // Method to get the list of disasters
    public List<Disaster> getDisasterList() {
        return disasterList;
    }
    
     // Method to prioritize disasters
    private void prioritizeDisasters() {
        List<Disaster> prioritizedDisasters;
        prioritizedDisasters = disasterList.stream()
                .sorted(Comparator.comparingInt(Disaster::getSeverity).reversed()
                        .thenComparing(Disaster::getTimestamp))
                .collect(Collectors.toList());

        // Display or process the prioritized list (e.g., send notifications, update UI)
        System.out.println("Prioritized Disasters:");
        for (Disaster disaster : prioritizedDisasters) {
            System.out.println(disaster);
        }
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void switchToCoordinate() throws IOException {
        App.setRoot("coordinate");
    }
}
