package Booking.view;

import java.io.IOException;

import Booking.Main;
import javafx.fxml.FXML;

public class MainItemsController {
	
	private Main main;
	
	@FXML
	private void toLoginScene() throws IOException{
		main.showLoginScene();
	}

}
