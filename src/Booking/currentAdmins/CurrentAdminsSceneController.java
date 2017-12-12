package Booking.currentAdmins;


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
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

public class CurrentAdminsSceneController implements Initializable {

    Main main;
    @FXML private TableView<UserData> tableView;
    @FXML private TableColumn<UserData, String> username;
    @FXML private TableColumn<UserData, String> email;
    @FXML private TableColumn<UserData, String> password;


    public ObservableList<UserData> parseXml() throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = db.newDocumentBuilder();
        Document doc = dBuilder.parse("/Users/McLaughlin/Code/Cinema-booking/src/Booking/Admins.xml");
        doc.getDocumentElement().normalize();
        ObservableList<UserData> data = FXCollections.observableArrayList();
        NodeList nList = doc.getElementsByTagName("User");
        UserData ds = new UserData();

        int j = 1;
        for(int i = 0; i < nList.getLength(); i++){
            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element :"
                    + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                ds.setUsername(element
                        .getElementsByTagName("Username")
                        .item(0)
                        .getTextContent());
                System.out.println(element
                        .getElementsByTagName("Username")
                        .item(0)
                        .getTextContent());

                ds.setEmail(element
                        .getElementsByTagName("Email")
                        .item(0)
                        .getTextContent());
                System.out.println(element
                        .getElementsByTagName("Email")
                        .item(0)
                        .getTextContent());

                ds.setPassword(element
                        .getElementsByTagName("Password")
                        .item(0)
                        .getTextContent());
                System.out.println(element
                        .getElementsByTagName("Password")
                        .item(0)
                        .getTextContent());

                ds.setSerialNo(j);
                ++j;
            }
            //list.add(ds);
            data.add(ds);
        }
        //data = FXCollections.observableArrayList(list);
        return data;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setCellValueFactory(new PropertyValueFactory<UserData, String>("username"));
        email.setCellValueFactory(new PropertyValueFactory<UserData, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
        try {
            tableView.getItems().setAll(parseXml());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
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
        Main.showSingleAdminScene(tableView);
    }
}
