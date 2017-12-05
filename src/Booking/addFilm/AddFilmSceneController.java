package Booking.addFilm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Booking.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddFilmSceneController {
	
	  Main main;
	  String title;
	  String director;
	  String line;
	  List<String> filmDetails = new ArrayList<String>();
	
	  @FXML
	  private Label titleLabel;
	  @FXML
	  private TextField titleTextField;
	  @FXML
	  private Label directorLabel;
	  @FXML
	  private TextField directorTextField;  
	  @FXML
	  private Button submitFilmBtn;
	  
	  
	  
	  @FXML
	  public void handleSubmit() throws IOException{
	    	
		  title = titleTextField.getText();
		  director = directorTextField.getText();
		  filmDetails.add(title);
		  filmDetails.add(director);
		  
//		  List<String> lines =  Files.readAllLines(Paths.get("src/Booking/filename.txt"), Charset.defaultCharset());
//		  int onLine = lines.size();
		  
		  
		  
		  PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/filename.txt"),true));
	      writer.append(title + ";" + director + "\n");
	      writer.close();
	       
	       main.showFilmsListScene();
		}
}
