package Booking.view;

import java.io.IOException;

import Booking.Main;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;

public class MainItemsController {
	
	private Main main;
	
	@FXML
	private void toLoginScene() throws IOException{
		main.showLoginScene();
	}

	@FXML
	private void toRegisterScene() throws IOException{
		main.showRegisterScene();
	}

}
