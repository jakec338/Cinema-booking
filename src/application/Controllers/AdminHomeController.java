package application.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminHomeController {

	@FXML
	private Button toFilmsBtn;
	
	@FXML
	private Button logOutBtn;
	
	@FXML
	private Button exportBtn;
	
	@FXML
	public void handleAdminBtns(ActionEvent event) throws IOException{
		 Stage stage; 
	     Parent root;
	     if(event.getSource()==toFilmsBtn){
	        //get reference to the button's stage         
	        stage=(Stage) toFilmsBtn.getScene().getWindow();
	        //load up OTHER FXML document
	  root = FXMLLoader.load(getClass().getResource("Views/Films.fxml"));
	      }
	     else{
	       stage=(Stage) logOutBtn.getScene().getWindow();
	  root = FXMLLoader.load(getClass().getResource("Views/Main.fxml"));
	      }
	     //create a new scene with root and set the stage
	      Scene scene = new Scene(root, 540, 400);
	      stage.setScene(scene);
	      stage.show();
	    
	}
}
