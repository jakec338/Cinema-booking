package Booking.view;

import java.io.IOException;

import Booking.Main;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MainItemsController {
	
	private Main main;
	@FXML AnchorPane imgAnchor;
	@FXML ImageView iV;


	@FXML
	private void toLoginScene() throws IOException{
		main.showLoginScene();
	}

	@FXML
	private void toRegisterScene() throws IOException{
		main.showRegisterScene();
	}

	@FXML
	private void toHomeScene() throws IOException{
		Main.showMainItems();
	}
}
