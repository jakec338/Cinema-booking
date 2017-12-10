package Booking.admin;

import java.io.IOException;

import Booking.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminHomeController {
	
	private Main main;
	
	@FXML private Button filmListBtn;
	
	@FXML private Button addFilmBtn;
	
	@FXML private Button logoutBtn;
	
	@FXML
	public void toFilmsListScene() throws IOException{
		Main.showFilmsListScene();
	}
	
	@FXML
	public void toAddFilmScene() throws IOException{
		Main.showAddFilmScene();
	}
	
	@FXML
	public void toHomeScene() throws IOException{
		Main.showMainItems();
	}

	@FXML
	public void toCurrentUsersScene() throws IOException{
		Main.showCurrentUsersScene();
	}

	@FXML
	public void toCurrentAdminsScene() throws IOException{
		Main.showCurrentAdminsScene();
	}
	
}
