package Booking;

import java.io.IOException;

import Booking.addShowing.AddShowingSceneController;
import Booking.bookFilm.BookSingleFilmSceneController;
import Booking.singleUser.EditSingleUserSceneController;
import Booking.singleUser.SingleUserSceneController;
import Booking.filmList.FilmData;
import Booking.singleFilm.SingleFilmSceneController;
import Booking.user.UserData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

/**
 * Main Class to run application
 */
public class Main extends Application{
	
	private Stage primaryStage;
	private static BorderPane mainLayout;

    /**
     * Starts the GUI at the Home Scene
     * @param primaryStage launches a starting stage
     * @throws IOException if the scene cannot be loaded
     */
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Cinema Booking");
		showMainView();
		showMainItems();
		
	}

    /**
     * Loads the top bar of the GUI
     * @throws IOException if the scene cannot be loaded
     */
	private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

    /**
     * Loads the home page of the GUI
     * @throws IOException if the scene cannot be loaded
     */
	public static void showMainItems() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainItems.fxml"));
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);
	}

    /**
     * Loads the loginScene
     * @throws IOException if the scene cannot be loaded
     */
	public static void showLoginScene() throws IOException{         // Static is important. Dunno why. 
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("login/LoginScene.fxml"));
		BorderPane loginScene = loader.load();
		mainLayout.setCenter(loginScene);
	}

    /**
     * Loads the CurrentUsersScene
     * @throws IOException if the scene cannot be loaded
     */
	public static void showCurrentUsersScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("currentUsers/CurrentUsersScene.fxml"));
        BorderPane currentUsersScene = loader.load();
        mainLayout.setCenter(currentUsersScene);

        FXMLLoader adminLoader = new FXMLLoader();
        adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
        BorderPane adminHomeScene = adminLoader.load();
        mainLayout.setCenter(adminHomeScene);

        // Sets the centre view to filmList
        adminHomeScene.setCenter(currentUsersScene);
    }

    /**
     * Loads the CurrentAdminsScene
     * @throws IOException if the scene cannot be loaded
     */
    public static void showCurrentAdminsScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("currentAdmins/CurrentAdminsScene.fxml"));
        BorderPane currentAdminsScene = loader.load();
        mainLayout.setCenter(currentAdminsScene);

        FXMLLoader adminLoader = new FXMLLoader();
        adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
        BorderPane adminHomeScene = adminLoader.load();
        mainLayout.setCenter(adminHomeScene);

        // Sets the centre view to filmList
        adminHomeScene.setCenter(currentAdminsScene);
    }

    /**
     * Loads the Registration Scene
     * @throws IOException if the scene cannot be loaded
     */
    public static void showRegisterScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("register/RegisterScene.fxml"));
        BorderPane loginScene = loader.load();
        mainLayout.setCenter(loginScene);
    }

    /**
     * Loads the AddUserScene
     * @throws IOException if the scene cannot be loaded
     */
	public static void showAddUserScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("addUser/AddUserScene.fxml"));
        BorderPane registerScene = loader.load();
        mainLayout.setCenter(registerScene);

        FXMLLoader adminLoader = new FXMLLoader();
        adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
        BorderPane adminHomeScene = adminLoader.load();
        mainLayout.setCenter(adminHomeScene);

        // Sets the centre view to filmList
        adminHomeScene.setCenter(registerScene);
    }

    /**
     * Loads the AdminHomeScene
     * @throws IOException if the scene cannot be loaded
     */
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

    /**
     * Loads the User Home Scene
     * @throws IOException if the scene cannot be loaded
     * @throws ParserConfigurationException if a configuration error occurs
     * @throws SAXException if a basic parsing error occurs
     */
    public static void showUserHomeScene() throws IOException, ParserConfigurationException, SAXException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("updateProfile/updateProfileScene.fxml"));
        BorderPane updateProfileScene = loader.load();

        FXMLLoader userLoader = new FXMLLoader();
        userLoader.setLocation(Main.class.getResource("user/UserHomeScene.fxml"));
        BorderPane adminHomeScene = userLoader.load();
        mainLayout.setCenter(adminHomeScene);

        adminHomeScene.setCenter(updateProfileScene);

        EditSingleUserSceneController controller =loader.getController();
        controller.Init();
 }

    /**
     *Loads the EditSingleUserScene
     * @throws IOException if the scene cannot be loaded
     * @throws ParserConfigurationException if a configuration error occurs
     * @throws SAXException if a basic parsing error occurs
     */
    public static void showEditSingleUserScene() throws IOException, ParserConfigurationException, SAXException {

        // Loads film list scene for admin home landing page
        FXMLLoader editLoader = new FXMLLoader();
        editLoader.setLocation(Main.class.getResource("singleUser/EditSingleUserScene.fxml"));
        BorderPane editSingleUserScene = editLoader.load();

        // Loads the admin home scaffolding
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("user/UserHomeScene.fxml"));
        BorderPane adminHomeScene = loader.load();
        mainLayout.setCenter(adminHomeScene);

//         Sets the centre view to filmList
        adminHomeScene.setCenter(editSingleUserScene);

        EditSingleUserSceneController controller = editLoader.getController();
        controller.InitEdit();
    }

    /**
     *Loads the FilmListScene
     * @throws IOException if the scene cannot be loaded
     */
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

    /**
     *Loads the AddFilmScene
     * @throws IOException if the scene cannot be loaded
     */
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

    /**
     *Loads the Single Film Scene
     * @param tableView defines the single film to be loaded
     * @throws IOException if the scene cannot be loaded
     * @throws XPathExpressionException if the xPath encounters an error
     * @throws ParserConfigurationException if a configuration error occurs
     * @throws SAXException if a basic parsing error occurs
     */
	public static void showSingleFilmScene(TableView tableView) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
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

    /**
     * Loads the Single Film Scene
     * @param selectedFilm defines the specific film to be loaded
     * @throws IOException if the scene cannot be loaded
     * @throws XPathExpressionException if the xPath encounters an error
     * @throws ParserConfigurationException if a configuration error occurs
     * @throws SAXException if a basic parsing error occurs
     */
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

    /**
     *Loads the Single User Scene
     * @param tableView defines the specific user to be loaded
     * @throws IOException if the scene cannot be loaded
     */
	public static void showSingleUserScene(TableView tableView) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("singleUser/SingleUserScene.fxml"));
        BorderPane singleUserScene = loader.load();
        mainLayout.setCenter(singleUserScene);

        FXMLLoader adminLoader = new FXMLLoader();
        adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
        BorderPane adminHomeScene = adminLoader.load();
        mainLayout.setCenter(adminHomeScene);

        // Sets the centre view to filmList
        adminHomeScene.setCenter(singleUserScene);

        SingleUserSceneController controller = loader.getController();
        controller.initData((UserData)tableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Loads the Single Admin Scene
     * @param tableView defines the specific Admin to be loaded
     * @throws IOException if the scene cannot be loaded
     */
    public static void showSingleAdminScene(TableView tableView) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("singleUser/SingleAdminScene.fxml"));
        BorderPane singleAdminScene = loader.load();
        mainLayout.setCenter(singleAdminScene);

        FXMLLoader adminLoader = new FXMLLoader();
        adminLoader.setLocation(Main.class.getResource("admin/AdminHomeScene.fxml"));
        BorderPane adminHomeScene = adminLoader.load();
        mainLayout.setCenter(adminHomeScene);

        // Sets the centre view to filmList
        adminHomeScene.setCenter(singleAdminScene);

        SingleUserSceneController controller = loader.getController();
        controller.initData((UserData)tableView.getSelectionModel().getSelectedItem());
    }

    /**
     *Loads the Booking Scene
     * @throws IOException if the scene cannot be loaded
     */
    public static void showBookingScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("bookFilm/BookAllFilmsScene.fxml"));
        BorderPane bookFilmScene = loader.load();

        // SHOULD NOT BE LEFT LIKE THIS. FIND WAY TO PUT adminHomeScene
        // IN A SEPERATE METHOD

        FXMLLoader adminLoader = new FXMLLoader();
        adminLoader.setLocation(Main.class.getResource("user/UserHomeScene.fxml"));
        BorderPane userHomeScene = adminLoader.load();
        mainLayout.setCenter(userHomeScene);

        userHomeScene.setCenter(bookFilmScene);
    }

    /**
     * Loads the Booking History Scene
     * @throws IOException if the scene cannot be loaded
     */
    public static void showBookingHistoryScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("pastBookings/PastBookingsScene.fxml"));
        BorderPane bookFilmScene = loader.load();

        // SHOULD NOT BE LEFT LIKE THIS. FIND WAY TO PUT adminHomeScene
        // IN A SEPERATE METHOD

        FXMLLoader adminLoader = new FXMLLoader();
        adminLoader.setLocation(Main.class.getResource("user/UserHomeScene.fxml"));
        BorderPane userHomeScene = adminLoader.load();
        mainLayout.setCenter(userHomeScene);

        userHomeScene.setCenter(bookFilmScene);
    }

    /**
     *Loads the Book Single Film Scene
     * @param tableView defines the specific film to be loaded
     * @throws IOException if the scene cannot be loaded
     * @throws XPathExpressionException if the xPath encounters an error
     * @throws SAXException if a configuration error occurs
     * @throws ParserConfigurationException if a basic parsing error occurs
     */
    public static void showBookSingleFilmScene(TableView tableView) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("bookFilm/BookSingleFilmScene.fxml"));
        BorderPane bookSingleFilmScene = loader.load();

        FXMLLoader userLoader = new FXMLLoader();
        userLoader.setLocation(Main.class.getResource("user/UserHomeScene.fxml"));
        BorderPane userHomeScene = userLoader.load();
        mainLayout.setCenter(userHomeScene);

        BookSingleFilmSceneController controller = loader.getController();
        controller.initData((FilmData) tableView.getSelectionModel().getSelectedItem());

        userHomeScene.setCenter(bookSingleFilmScene);
    }

    /**
     *Loads the Add Showing Scene
     * @param selectedFilmTitle
     * @param selectedFilm
     * @throws IOException if the scene cannot be loaded
     */
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

    /**
     * Main args
     */
    public static void main(String[] args) {
		launch(args);
	}

}
