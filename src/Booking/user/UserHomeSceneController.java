package Booking.user;

import java.io.IOException;

import Booking.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Controller for the UserHomeScene. Consisting of navigational buttons.
 */
public class UserHomeSceneController {

    /**
     * Navigates to UpdateProfileScene
     */
    @FXML public void toUpdateProfileScene() throws IOException, ParserConfigurationException, SAXException {
        Main.showUserHomeScene();
    }

    /**
     * Navigates to BookingScene
     */
    @FXML public void toBookingScene() throws IOException{
        Main.showBookingScene();
    }

    /**
     * Navigates to BookingHistoryScene
     */
    @FXML public void toBookingHistoryScene() throws IOException{
        Main.showBookingHistoryScene();
    }
}
