package harsh.drs_initial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author harsh
 */
public class CoordinateController {
    @FXML
    private TableView<String[]> disasterTable;
    @FXML
    private TableColumn<String[], String> actionColumn;
    @FXML
    private TableColumn<String[], String> locationColumn;
    @FXML
    private TableColumn<String[], String> priorityColumn;
    @FXML
    private TableColumn<String[], String> typeColumn;

    @FXML
    public void initialize() {
        // Set up the columns to work with String[] data
        actionColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));
        locationColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));
        priorityColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));

        // Load CSV data into the TableView
        loadCSVData();
    }

    private void loadCSVData() {
        ObservableList<String[]> data = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader("disasterRequest.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                data.add(fields);  // Add the row to the data list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the items of the TableView
        disasterTable.setItems(data);
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
}
