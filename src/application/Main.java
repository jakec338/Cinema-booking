package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	Stage stage;
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;

		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			
			
			Scene scene = new Scene(root, 400, 400);
			
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
