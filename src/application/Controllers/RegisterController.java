package application.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import application.Model.*;

import java.io.*;

public class RegisterController {
    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button registerBtn;

    @FXML
    protected void Register(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Window owner = registerBtn.getScene().getWindow();
//        //Check username has been entered
//        if(username.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter your username");
//            return;
//        }
//        //Check email has been entered
//        if(email.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter your email id");
//            return;
//        }
//        //Check password has been entered
//        if(password.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter a password");
//            return;
//        }
//        //Check matching passwords
//        if(!confirmPassword.getText().equals(confirmPassword.getText())) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Your passwords do not match!");
//            return;
//        }
//        try {
            if (event.getSource() == registerBtn) {
                registerUser(username.getText(), email.getText(), password.getText());
            }

        }

    private Database database;

    RegisterController(){
        this.database = Database.getinstance();
    }
    void registerUser(String username, String email, String password) throws IOException {
        User user = new Customer(new UserDetails(username,email,password));
        database.createUserInfo(user.getUsername(),user);

//    public static class AlertHelper {
//
//        public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
//            Alert alert = new Alert(alertType);
//            alert.setTitle(title);
//            alert.setHeaderText(null);
//            alert.setContentText(message);
//            alert.initOwner(owner);
//            alert.show();
//        }
//    }
    }
}