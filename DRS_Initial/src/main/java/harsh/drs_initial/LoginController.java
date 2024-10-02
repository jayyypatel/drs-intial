
package harsh.drs_initial;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author harsh
 */
public class LoginController {
     @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    // Handle the login action
    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Simple login validation (username and password both must be "admin")
        if (username.equals("admin") && password.equals("admin")) {
            messageLabel.setText("Login successful!");
            App.setRoot("primary");

        } else {
            messageLabel.setText("Login failed. Invalid username or password.");
        }
    }

}
