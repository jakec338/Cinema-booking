package Booking.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Booking.Main;
import Booking.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginSceneController {

	private Main main;

	@FXML private TableView<UserData> tableView;

	@FXML private Label loginLabel;

	@FXML private TextField username;

	@FXML private PasswordField password;

	@FXML private Button loginBtn;

	private UserData selectedUser;

	@FXML
	public void LoginTest() throws IOException{
		if(username.getText().equals("admin") && password.getText().equals("pass")){
			Main.showAdminHomeScene();
		} else if(username.getText().equals("user") && password.getText().equals("pass")) {
			Main.showUserHomeScene(tableView);
		} else {
			loginLabel.setText("Login Failed");
		}
	}

	public boolean ValidateUserDetails() throws IOException {
		ArrayList<String> userData = new ArrayList<String>();
		BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/Users.txt"))));
		String fileName = "src/Booking/Users.txt";
		File file = new File(fileName);
		Scanner inputStream = new Scanner(file);
		while (inputStream.hasNext()) {
			String data = inputStream.nextLine();
			String[] tokens = data.split(";");
			String selectedUsername = tokens[0];
			String selectedPassword = tokens[2];
			if (selectedUsername.equals(username.getText()) && selectedPassword.equals(password.getText())) {
				return true;
			}}
		return false;
	}

	public boolean ValidateAdminDetails() throws IOException {
		ArrayList<String> userData = new ArrayList<String>();
		BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/Admins.txt"))));
		String fileName = "src/Booking/Admins.txt";
		File file = new File(fileName);
		Scanner inputStream = new Scanner(file);
		while (inputStream.hasNext()) {
			String data = inputStream.nextLine();
			String[] tokens = data.split(";");
			String selectedUsername = tokens[0];
			String selectedPassword = tokens[2];
			if (selectedUsername.equals(username.getText()) && selectedPassword.equals(password.getText())) {
				return true;
			}}
		return false;
	}

//	public boolean ValidateUserDetails() throws IOException{
//		BufferedReader in = new BufferedReader(new FileReader(new File("users.txt")));
//		int line=0;
//		for (String x = in.readLine(); x!=null; x=in.readLine()){
//			line++;
//			String[] tokens = x.split(";");
//			for(String token : tokens){
//				String selectedUsername = tokens[0];
//				String selectedPassword = tokens[2];
//				if(selectedUsername.equals(username.getText()) && selectedPassword.equals(password.getText())){
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}

	@FXML
	public void Login() throws IOException{
		if (ValidateUserDetails()){
			Main.showUserHomeScene(tableView);
		} else if(ValidateAdminDetails()){
			Main.showAdminHomeScene();
		} else {
			loginLabel.setText("Login Failed you failure");
		}
	}
//
//	public boolean ValidateUsername() throws IOException {
//		if(SourceDetails()){ return true;}
//		else {return false;}}
//
//	public boolean ValidatePassword(){
//		if(password.getText().equals(selectedUser.getPassword())){ return true;}
//		else {return false;}}
//
//	public boolean ValidateAdminLogin(){
//		if(username.getText().equals("admin") && password.getText().equals("pass")){
//			return true;
//		} else {
//			return false;
//		}}
}
