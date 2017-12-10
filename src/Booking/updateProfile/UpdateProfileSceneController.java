package Booking.updateProfile;

import Booking.Main;
import Booking.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.*;
import java.util.Scanner;

public class UpdateProfileSceneController {

    private UserData selectedUser;

    @FXML Label usernameLabel;
    @FXML Label emailLabel;
    @FXML Label passwordLabel;
    @FXML Button editBtn;

    public void initData(UserData userData){
        selectedUser = userData;
        usernameLabel.setText(selectedUser.getUsername());
        emailLabel.setText(selectedUser.getEmail());
        passwordLabel.setText(selectedUser.getPassword());
    }

    public void editUser() throws IOException{

            String username = "";
            String email = "";
            String password ="";

            String tempFile = "src/Booking/temp.text";
            String oldFileName = "src/Booking/Films.txt";

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

        Main.showUserHomeScene();

        }

}
