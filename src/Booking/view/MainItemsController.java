package Booking.view;

import java.io.IOException;
import Booking.Main;
import javafx.fxml.FXML;

/**
 * Controller for MainView and MainItems Scenes
 */
public class MainItemsController {

	/**
	 * Navigates to LoginScene
	 */
	@FXML private void toLoginScene() throws IOException{
		Main.showLoginScene();
	}

	/**
	 * Navigates to RegisterScene
	 */
	@FXML private void toRegisterScene() throws IOException{
		Main.showRegisterScene();
	}

	/**
	 * Navigates to HomeScene
	 */
	@FXML private void toHomeScene() throws IOException{
		Main.showMainItems();
	}
}
