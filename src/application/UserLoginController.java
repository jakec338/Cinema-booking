package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginController {

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPass;

    @FXML
    private Button loginBtn;

    @FXML
    private Button backButton;

    @FXML
    public void handleUserLogin(ActionEvent event) throws IOException {
        if (event.getSource()==backButton){
            Stage stage;
            Parent root;
            stage=(Stage) backButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root, 540, 400);
            stage.setScene(scene);
            stage.show();
        }
        else if (txtName.getText().equals("user") && txtPass.getText().equals("pass")) {
            Stage stage;
            Parent root;
            lblStatus.setText("Login successful");

            stage = (Stage) loginBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("UserHome.fxml"));
            Scene scene = new Scene(root, 540, 400);
            stage.setScene(scene);
            stage.show();

        } else {
            lblStatus.setText("Login Failed you failure");
        }
    }
}
