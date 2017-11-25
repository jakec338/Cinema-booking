package application;


import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Button userButton;
	
	@FXML
	private Button adminButton;

	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtPass;
	
	@FXML
	private Button loginBtn;
	
	@FXML
	public void Login(ActionEvent event) throws IOException{
		if (txtName.getText().equals("user") && txtPass.getText().equals("pass") ){
			Stage stage; 
		    Parent root;
			lblStatus.setText("Login successful");
			
			stage=(Stage) loginBtn.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("adminHome.fxml"));
			Scene scene = new Scene(root, 1440, 900);
		    stage.setScene(scene);
		    stage.show();
			
		} else{
			lblStatus.setText("Login Failed you failure");
		}
	}; 
	
	@FXML
	public void genRand(ActionEvent event){
		Random rand = new Random();
		int myRand = rand.nextInt(50) + 1;
		System.out.println(Integer.toString(myRand));
	};
	
	@FXML
	public void handleAdminLogin(ActionEvent event) throws IOException{
		 Stage stage; 
	     Parent root;
	     if(event.getSource()==adminButton){
	        //get reference to the button's stage         
	        stage=(Stage) adminButton.getScene().getWindow();
	        //load up OTHER FXML document
	  root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
	      }
	     else{
	       stage=(Stage) userButton.getScene().getWindow();
	  root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
	      }
	     //create a new scene with root and set the stage
	      Scene scene = new Scene(root, 1440, 900);
	      stage.setScene(scene);
	      stage.show();
	    
	}
	
	
}
