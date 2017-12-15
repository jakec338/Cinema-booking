package Booking.admin;

import java.io.IOException;
import Booking.Main;
import javafx.fxml.FXML;

/**
 * This class controls the AdminHomeScene consisting of directional buttons for all capabilities on the Admin side
 */
public class AdminHomeSceneController {

	/**
	 * This method directs to the FilmsListScene
	 */
	@FXML public void toFilmsListScene() throws IOException{
		Main.showFilmsListScene();
	}

	/**
	 * This method directs to the AddFilmScene
	 */
	@FXML public void toAddFilmScene() throws IOException{
		Main.showAddFilmScene();
	}

	/**
	 * This method directs to the CurrentUsersScene
	 */
	@FXML public void toCurrentUsersScene() throws IOException{
		Main.showCurrentUsersScene();
	}

	/**
	 * This method directs to the CurrentAdminsScene
	 */
	@FXML public void toCurrentAdminsScene() throws IOException{
		Main.showCurrentAdminsScene();
	}
	
}
