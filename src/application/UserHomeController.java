package application;

import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserHomeController {

    @FXML
    private Button updateProfileBtn;

    @FXML
    private Button bookingHistoryBtn;

    @FXML
    private Button newBookingBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    public void handleUserBtns(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if(event.getSource()==updateProfileBtn){
            //get reference to the button's stage
            stage=(Stage) updateProfileBtn.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("UpdateProfilePage.fxml"));
            Scene scene = new Scene(root, 540, 400);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==bookingHistoryBtn){
            stage=(Stage) bookingHistoryBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("BookingHistoryPage.fxml"));
            Scene scene = new Scene(root, 540, 400);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==newBookingBtn){
            stage=(Stage) newBookingBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("NewBookingPage.fxml"));
            Scene scene = new Scene(root, 540, 400);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==logoutBtn){
            stage=(Stage) logoutBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root, 540, 400);
            stage.setScene(scene);
            stage.show();
        }

    }
}
