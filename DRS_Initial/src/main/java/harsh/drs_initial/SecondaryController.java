package harsh.drs_initial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.fxml.FXML;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class SecondaryController {
    @FXML
    private TableView<Disaster> disasterTable;
    @FXML
    private TableColumn<Disaster, String> typeColumn;
    @FXML
    private TableColumn<Disaster, String> locationColumn;
    @FXML
    private TableColumn<Disaster, Integer> severityColumn;
    @FXML
    private TableColumn<Disaster, Integer> priorityColumn;
    @FXML
    private TableColumn<Disaster, Integer> descriptionColumn;

     private List<Disaster> disasterList;
     

private void openDisasterDetails(Disaster disaster) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("disasterDetails.fxml"));
        Parent root = loader.load();

        DisasterDetailsController detailsController = loader.getController();
        detailsController.setDisaster(disaster);

        // Get the current scene and set the new root
        Scene currentScene = disasterTable.getScene();
        currentScene.setRoot(root);  // Only change the root, not the entire scene

        // Optionally update the window title if needed
        Stage stage = (Stage) currentScene.getWindow();
        stage.setTitle("Disaster Details");
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    public void initialize() {
        System.out.println("secondary called");
        // Create a ToggleGroup
        disasterList = new ArrayList<Disaster>();

        // Read the disaster data from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("disasters.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String type = fields[0];
                String location = fields[1];
                int severity = Integer.parseInt(fields[2]);
                String description = fields[3];
                
                // Create a Disaster object and add it to the list
                Disaster disaster = new Disaster(type, location, severity, description);
                disasterList.add(disaster);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(disasterList);
        
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("DisasterType"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        severityColumn.setCellValueFactory(new PropertyValueFactory<>("Severity"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        List<Disaster> prioritizedDisasters;
        prioritizedDisasters = disasterList.stream()
                .sorted(Comparator.comparingInt(Disaster::getSeverity).reversed()
                        .thenComparing(Disaster::getTimestamp))
                .collect(Collectors.toList());
        // Convert ArrayList to ObservableList
        ObservableList<Disaster> observableList = FXCollections.observableArrayList(prioritizedDisasters);

        // Set the items of the TableView
        disasterTable.setItems(observableList);
        
        // Add click event to table rows
        disasterTable.setRowFactory(tv -> {
            TableRow<Disaster> row = new TableRow<>(){
                @Override
        protected void updateItem(Disaster disaster, boolean empty) {
            super.updateItem(disaster, empty);
            
            if (disaster == null || empty) {
                setStyle("");  // Reset style for empty rows
            } else {
                // Check the priority and apply a style
                if ("High Priority".equalsIgnoreCase(disaster.getPriority())) {
                    setStyle("-fx-background-color: red;");  // High priority rows are red
                } else if ("Medium Priority".equalsIgnoreCase(disaster.getPriority())) {
                    setStyle("-fx-background-color: orange;");  // Medium priority rows are orange
                } else if ("Low Priority".equalsIgnoreCase(disaster.getPriority())) {
                    setStyle("-fx-background-color: lightgreen;");  // Low priority rows are green
                } else {
                    setStyle("");  // Reset for any other priority
                }
            }
        }
            };
            
            
            
            
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Disaster disaster = row.getItem();
                    openDisasterDetails(disaster);
                    System.out.println("hehllo this is table row" + disaster);
                }
            });
            return row;
        });
    }
    @FXML
    private void switchToPrimary() throws IOException {
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