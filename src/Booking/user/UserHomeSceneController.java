package Booking.user;

import java.io.IOException;

import Booking.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class UserHomeSceneController {

    @FXML private TableView<UserData> tableView;

    private UserData selectedUser;

    @FXML private Button updateProfileBtn;

    @FXML private Button bookingHistoryBtn;

    @FXML private Button newBookingBtn;

    @FXML private Button logoutBtn;

    @FXML Label usernameLabel;
    @FXML Label emailLabel;
    @FXML Label passwordLabel;

    public void initData(UserData userData){
        selectedUser = userData;
        usernameLabel.setText(selectedUser.getUsername());
        emailLabel.setText(selectedUser.getEmail());
        passwordLabel.setText(selectedUser.getPassword());
    }

    @FXML
    public void toUpdateProfileScene() throws IOException{
        Main.showUserHomeScene(tableView);
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
