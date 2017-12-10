package Booking.filmList;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import Booking.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FilmListSceneController implements Initializable {
	
	Main main;
	@FXML private TableView<FilmData> tableView;
    @FXML private TableColumn<FilmData, String> title;
    @FXML private TableColumn<FilmData, String> director;
    
    
    public ObservableList<FilmData> getFilms() throws IOException{
    	
    	ObservableList<FilmData> films = FXCollections.observableArrayList();
    	
    	String fileName = "src/Booking/Films.txt";
		File file = new File(fileName);
    	List<String> lines =  Files.readAllLines(Paths.get("src/Booking/Films.txt"), Charset.defaultCharset());
		int numberOfLines = lines.size();   // never used
		Scanner inputStream = new Scanner(file);
		
		while (inputStream.hasNext()){
			String data = inputStream.nextLine(); //
			String singleValues[] = data.split(";");
			
			films.add(new FilmData(singleValues[0], singleValues[1]));
			  // Gets individual values from the CSV file, separated by ; [0] = Title, [1] = Director.
		}inputStream.close();
		
    	return films;
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<FilmData, String>("title"));
        director.setCellValueFactory(new PropertyValueFactory<FilmData, String>("director"));
        try {
			tableView.getItems().setAll(getFilms());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML public void toSingleFilmScene() throws IOException{
    	// if cell is not empty  TO ADD
    	Main.showSingleFilmScene(tableView);
    }
}
