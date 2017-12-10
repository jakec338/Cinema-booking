package Booking.currentUsers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Booking.Main;
import Booking.filmList.FilmData;
import Booking.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SingleUserSceneController {

    Main main;
    private UserData selectedUser;

    @FXML Label usernameLabel;
    @FXML Label emailLabel;
    @FXML Label passwordLabel;
    @FXML Button deleteBtn;

    public void initData(UserData userData){
        selectedUser = userData;
        usernameLabel.setText(selectedUser.getUsername());
        emailLabel.setText(selectedUser.getEmail());
        passwordLabel.setText(selectedUser.getPassword());
    }

/*
This method creates a new file containing all user info except the current deleted one.
It then deletes the initial file and renames the new one
 */
    public void deleteUser() throws IOException{

        String username = "";
        String email = "";
        String password = "";

        String tempFile = "src/Booking/temp.text";
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

        Main.showCurrentUsersScene();

    }
    public void deleteAdmin() throws IOException{

        String username = "";
        String email = "";
        String password = "";

        String tempFile = "src/Booking/temp.text";
        String oldFileName = "src/Booking/Admins.txt";

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

        Main.showCurrentAdminsScene();

    }
}