package Booking.user;

import java.io.IOException;

import Booking.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class UserHomeSceneController {

    @FXML private TableView<UserData> tableView;

    private UserData selectedUser;

    @FXML private Button updateProfileBtn;

    @FXML private Button bookingHistoryBtn;

    @FXML private Button newBookingBtn;

    @FXML private Button logoutBtn;

    @FXML
    public void toUpdateProfileScene() throws IOException, ParserConfigurationException, SAXException {
        Main.showUserHomeScene();
    }

    @FXML
    public void toBookingScene() throws IOException{

    }

    @FXML
    public void toBookingHistoryScene() throws IOException{

    }

    @FXML
    public void toHomeScene() throws IOException {
        Main.showMainItems();
    }


}
