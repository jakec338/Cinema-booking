package Booking.currentUsers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Booking.register.AlertHelper;
import javafx.scene.control.*;
import javafx.stage.Window;
import Booking.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddUserSceneController {

    Main main;
    String username;
    String email;
    String password;
    String line;
    List<String> userDetails = new ArrayList<String>();

    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button submitDetailsBtn;
    @FXML
    private CheckBox staffConfirm;



    @FXML
    public void handleSubmit() throws IOException{

        username = usernameTextField.getText();
        email = emailTextField.getText();
        password = passwordField.getText();
        userDetails.add(username);
        userDetails.add(email);
        userDetails.add(password);


        { Window owner = submitDetailsBtn.getScene().getWindow();
            //Checks username is entered
            if(usernameTextField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please choose a Username");
                return;
            }
            //Checks email is entered
            if(emailTextField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter an Email");
                return;
            }
            //Checks password is entered
            if(passwordField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter a Password");
                return;
            }
            //Verifies minimum username length
            if(usernameTextField.getLength()<4) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Username must be at least 4 characters");
                return;
            }
            //Verifies minimum password length
            if(passwordField.getLength()<8) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Password must be at least 8 characters");
                return;
            }
            //Verifies passwords match
            if(!passwordField.getText().equals(confirmPasswordField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Passwords do not match");
                return;
            }
            //Checks for valid email
            if(!validateEmail()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter a valid Email");
                return;
            }


            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "User Added!",
                    "Welcome " + usernameTextField.getText()); }


        if(staffConfirm.isSelected()) {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/Admins.txt"), true));

            writer.append(username + ";" + email + ";" + password + "\n");
            writer.close();
        } else{
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/Users.txt"), true));

            writer.append(username + ";" + email + ";" + password + "\n");
            writer.close();
        }

        main.showCurrentUsersScene();
    }

    private boolean validateEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailTextField.getText());
        if(m.find() && m.group().equals(emailTextField.getText())){
            return true;
        }
        else {
            return false;
        }
    }
}