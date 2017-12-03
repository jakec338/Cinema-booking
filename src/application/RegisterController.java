package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.*;

public class RegisterController {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerBtn;

    @FXML
    protected void Register(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        Window owner = registerBtn.getScene().getWindow();
        //Check username has been entered
        if(txtName.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }
        //Check email has been entered
        if(txtEmail.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        //Check password has been entered
        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        //Check matching passwords
        if(!passwordField.getText().equals(confirmPasswordField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Your passwords do not match!");
            return;
        }
        PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/application/users.txt"),true));
        writer.append(txtName.getText() + ", " + txtEmail.getText() +", " + passwordField.getText() + "\n");
        writer.close();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + txtName.getText());
    }
    public static class AlertHelper {

        public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }
    }}