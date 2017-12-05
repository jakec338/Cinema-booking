package Booking.singleFilm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Booking.Main;
import Booking.filmList.FilmData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SingleFilmSceneController {
	
	Main main;
	private FilmData selectedFilm;
	
	@FXML Label titleLabel;
	@FXML Label directorLabel;
	@FXML Button deleteBtn;
	
	public void initData(FilmData filmData){
		selectedFilm = filmData;
		titleLabel.setText(selectedFilm.getTitle());
		directorLabel.setText(selectedFilm.getDirector());
	}
	
	
	// THIS METHOD FIRST CREATES A NEW FILE CONTINAUNG INFO OF ALL FILMS EXCEPT THE CURRENT ONE
	// IT THEN DELETES THE INITIAL CSV AND RENAMES THE NEW ONE 
	public void deleteFilm() throws IOException{
		
		String title = "";
		String director = "";
		
		String tempFile = "src/Booking/temp.text";
		String oldFileName = "src/Booking/filename.txt";
		
		File oldFile = new File(oldFileName);
		File newFile = new File(tempFile);
		
		
		FileWriter fw = new FileWriter(tempFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter writer = new PrintWriter(bw);
		Scanner scanner = new Scanner(new File(oldFileName));
		scanner.useDelimiter("[;\n]");
		while (scanner.hasNext()){
			title = scanner.next();
			director = scanner.next();
			System.out.println(title + selectedFilm.getTitle());
			if (!title.equals(selectedFilm.getTitle())){
				System.out.println("check check");
				writer.append(title + ";" + director + "\n");     // DOESNT LIKE SPACES
			}
		}
		scanner.close();
		writer.flush();
		writer.close();
		oldFile.delete();
		File dump = new File(oldFileName);
		newFile.renameTo(dump);
		
		main.showFilmsListScene();
			
		}
}

