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
		main.showFilmsListScene();
	}
	
	@FXML
	public void toAddFilmScene() throws IOException{
		main.showAddFilmScene();
	}
	
	@FXML
	public void toHomeScene() throws IOException{
		main.showMainItems();
	}
	
}
