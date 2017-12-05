package Booking.login;

import java.io.IOException;

import Booking.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController {

	private Main main;
	
	@FXML private Label loginLabel;
	
	@FXML private TextField userName;
	
	@FXML private PasswordField password;
	
	@FXML private Button loginBtn;
	
	
	
	
	@FXML
	public void Login() throws IOException{
		if (userName.getText().equals("user") && password.getText().equals("pass") ){
			main.showAdminHomeScene();
		} else{
			loginLabel.setText("Login Failed you failure");
		}
	}; 
}
