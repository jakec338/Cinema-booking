package Booking.currentUsers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import Booking.Main;
import Booking.user.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CurrentUsersSceneController implements Initializable {

    Main main;
    @FXML private TableView<UserData> tableView;
    @FXML private TableColumn<UserData, String> username;
    @FXML private TableColumn<UserData, String> email;
    @FXML private TableColumn<UserData, String> password;


    public ObservableList<UserData> getUsers() throws IOException{

        ObservableList<UserData> users = FXCollections.observableArrayList();

        String fileName = "src/Booking/Users.txt";
        File file = new File(fileName);
        List<String> lines =  Files.readAllLines(Paths.get("src/Booking/Users.txt"), Charset.defaultCharset());
        int numberOfLines = lines.size();   // never used
        Scanner inputStream = new Scanner(file);

        while (inputStream.hasNext()){
            String data = inputStream.nextLine(); //
            String singleValues[] = data.split(";");

            users.add(new UserData(singleValues[0], singleValues[1], singleValues[2]));
            // Gets individual values from the CSV file, separated by ; [0] = Username, [1] = Email, [2] = Password.
        }inputStream.close();

        return users;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setCellValueFactory(new PropertyValueFactory<UserData, String>("username"));
        email.setCellValueFactory(new PropertyValueFactory<UserData, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
        try {
            tableView.getItems().setAll(getUsers());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    public void toAddUserScene() throws IOException{
        // if cell is not empty  TO ADD
        Main.showAddUserScene();
    }

    @FXML
    public void toSingleUserScene() throws IOException {
        Main.showSingleUserScene(tableView);
    }
}
