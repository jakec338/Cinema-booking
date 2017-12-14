package Booking;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import Booking.addShowing.AddShowingSceneController;
import Booking.filmList.FilmData;
import Booking.singleFilm.SingleFilmSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Stage primaryStage;
	private Stage dateStage;
	private static BorderPane mainLayout;


	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Cinema Booking");
		showMainView();
		showMainItems();
		
	}
	
	private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showMainItems() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainItems.fxml"));
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);
	}
	
	public static void showLoginScene() throws IOException{         // Static is important. Dunno why. 
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("login/LoginScene.fxml"));
		BorderPane loginScene = loader.load();
		mainLayout.setCenter(loginScene);
	}
	
	
	public static void showAdminHomeScene() throws IOException{
		
		// Loads film list scene for admin home landing page
		FXMLLoader filmListLoader = new FXMLLoader();
		filmListLoader.setLocation(Main.class.getResource("filmList/FilmListScene.fxml"));     
		BorderPane filmListScene = filmListLoader.load();
		
		// Loads the admin home scaffolding
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
		BorderPane adminHomeScene = loader.load();
		mainLayout.setCenter(adminHomeScene);
		
		// Sets the centre view to filmList
		adminHomeScene.setCenter(filmListScene);
	}
	
	public static void showFilmsListScene() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("filmList/FilmListScene.fxml"));     
		BorderPane filmListScene = loader.load();
																							
		// SHOULD NOT BE LEFT LIKE THIS. FIND WAY TO PUT adminHomeScene
		// IN A SEPERATE METHOD
		
		FXMLLoader adminLoader = new FXMLLoader();
		adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
		BorderPane adminHomeScene = adminLoader.load();
		mainLayout.setCenter(adminHomeScene);
	
		adminHomeScene.setCenter(filmListScene);
	}
	
	
	public static void showAddFilmScene() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("addFilm/AddFilmScene.fxml"));     
		BorderPane addFilmScene = loader.load();
																							
		// SHOULD NOT BE LEFT LIKE THIS. FIND WAY TO PUT adminHomeScene
		// IN A SEPERATE METHOD
		
		FXMLLoader adminLoader = new FXMLLoader();
		adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
		BorderPane adminHomeScene = adminLoader.load();
		mainLayout.setCenter(adminHomeScene);
	
		adminHomeScene.setCenter(addFilmScene);
		
	}
	
	
	public static void showSingleFilmScene(TableView tableView) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("singleFilm/SingleFilmScene.fxml"));     
		BorderPane singleFilmScene = loader.load();
		
		FXMLLoader adminLoader = new FXMLLoader();
		adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
		BorderPane adminHomeScene = adminLoader.load();
		mainLayout.setCenter(adminHomeScene);
		
		SingleFilmSceneController controller = loader.getController();
		controller.initData((FilmData) tableView.getSelectionModel().getSelectedItem());
		
		adminHomeScene.setCenter(singleFilmScene);
	}
	
	public static void showSingleFilmScene(FilmData selectedFilm) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("singleFilm/SingleFilmScene.fxml"));     
		BorderPane singleFilmScene = loader.load();
		
		FXMLLoader adminLoader = new FXMLLoader();
		adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
		BorderPane adminHomeScene = adminLoader.load();
		mainLayout.setCenter(adminHomeScene);
		
		SingleFilmSceneController controller = loader.getController();
		controller.initData(selectedFilm);
		
		adminHomeScene.setCenter(singleFilmScene);
	}
	
	
	public static void showAddShowingScene(String selectedFilmTitle, FilmData selectedFilm) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("addShowing/AddShowingScene.fxml"));     
		AnchorPane addPopUpScene = loader.load();
		AddShowingSceneController controller = loader.getController();
		controller.initData(selectedFilmTitle, selectedFilm);
		// SHOULD NOT BE LEFT LIKE THIS. FIND WAY TO PUT adminHomeScene
		// IN A SEPERATE METHOD
		
		FXMLLoader adminLoader = new FXMLLoader();
		adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
		BorderPane adminHomeScene = adminLoader.load();
		mainLayout.setCenter(adminHomeScene);
	
		adminHomeScene.setCenter(addPopUpScene);
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
