module harsh.drs_initial {
    requires javafx.controls;
    requires javafx.fxml;

    opens harsh.drs_initial to javafx.fxml;
    exports harsh.drs_initial;
}
