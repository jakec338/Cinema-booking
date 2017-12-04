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

public class UpdateProfilePageController {

    @FXML
    private Button EditBtn;

    @FXML public void handleEdit(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        if(event.getSource()==EditBtn){
            //get reference to the button's stage
            stage=(Stage) EditBtn.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("EditProfilePage.fxml"));
            Scene scene = new Scene(root, 540, 400);
            stage.setScene(scene);
            stage.show();
        }
    }
}
