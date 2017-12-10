package Booking.currentUsers;

import Booking.Main;
import Booking.filmList.FilmData;
import Booking.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Scanner;

public class EditSingleUserSceneController {

    @FXML private TableView<UserData> tableView;

    private UserData selectedUser;

    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Label usernameLabel;
    @FXML private Label emailLabel;
    @FXML private Label passwordLabel;

    public void initData(UserData userData){
        selectedUser = userData;
        usernameLabel.setText(selectedUser.getUsername());
        emailLabel.setText(selectedUser.getEmail());
        passwordLabel.setText(selectedUser.getPassword());
    }

//    public void initEditData(){
//        usernameField.setText(selectedUser.getUsername());
//        emailField.setText(selectedUser.getEmail());
//        passwordField.setText(selectedUser.getPassword());
//    }

    @FXML
    public void toEditSingleUserScene() throws IOException{
        Main.showEditSingleUserScene();
    }

    public void editUser() throws IOException {

        String username = "";
        String email = "";
        String password = "";

        String tempFile = "src/Booking/temp.txt";
        String oldFileName = "src/Booking/Users.txt";

        File oldFile = new File(oldFileName);
        File newFile = new File(tempFile);


        FileWriter fw = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter writer = new PrintWriter(bw);
        Scanner scanner = new Scanner(new File(oldFileName));
        scanner.useDelimiter("[;\n]");
        while (scanner.hasNext()){
            username = scanner.next();
            email = scanner.next();
            password = scanner.next();
            System.out.println(username + selectedUser.getUsername());
            if (!username.equals(selectedUser.getUsername())){
                System.out.println("check check");
                writer.append(username + ";" + email + ";" + password + "\n");     // DOESNT LIKE SPACES
            }
        }
        scanner.close();
        writer.flush();
        writer.close();
        oldFile.delete();
        File dump = new File(oldFileName);
        newFile.renameTo(dump);

        Main.showUserHomeScene(tableView);

    }

}
