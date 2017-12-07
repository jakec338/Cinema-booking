package application.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFilmController {
	
	 String title;
	 String director;
	 String line = null;
	 List<String> filmDetails = new ArrayList<String>();
	
	  @FXML
	  private Label titleLabel;

	  @FXML
	  private TextField titleTextField;
	  
	  @FXML
	  private Label dirLabel;

	  @FXML
	  private TextField dirTextField;
	    
	  @FXML
	  private Button submitFilmBtn;
	  
	  @FXML
	  private Button readBtn;
	  
	  
	  
	  @FXML
	  public void handleSubmit(ActionEvent event) throws IOException{
	    	
		  title = titleTextField.getText();
		  director = dirTextField.getText();
		  filmDetails.add(title);
		  filmDetails.add(director);
		  try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
	          new FileOutputStream("src/application/filename.txt"), "utf-8"))) {
			  for (int i = 0; i < filmDetails.size(); i++){
				  writer.println(filmDetails.get(i));
			  }
		  }
		  
		  titleLabel.setText(title);
		    
		}
	  
	  
	  @FXML
	  public void handleRead(ActionEvent event) throws IOException{
          // FileReader reads text files in the default encoding.
          FileReader fileReader = 
              new FileReader("src/application/filename.txt");

          // Always wrap FileReader in BufferedReader.
          BufferedReader bufferedReader = 
              new BufferedReader(fileReader);

          while((line = bufferedReader.readLine()) != null) {
              System.out.println(line);
          }   

          // Always close files.
          bufferedReader.close(); 
	   
		}
	  

}
