package Booking.currentAdmins;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * Controller for CurrentAdminsScene.
 */
public class CurrentAdminsSceneController implements Initializable {

    @FXML private TableView<UserData> tableView;
    @FXML private TableColumn<UserData, String> username;
    @FXML private TableColumn<UserData, String> firstName;
    @FXML private TableColumn<UserData, String> surname;
    @FXML private TableColumn<UserData, String> email;
    @FXML private TableColumn<UserData, String> password;

    /**
     * This method reads the XML database to extract the information needed for the table view.
     */
    public ObservableList<UserData> parseXml() throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = db.newDocumentBuilder();
        Document doc = dBuilder.parse("src/Booking/Admins.xml");
        doc.getDocumentElement().normalize();
        ObservableList<UserData> data = FXCollections.observableArrayList();
        NodeList nList = doc.getElementsByTagName("User");


        int j = 1;
        for(int i = 0; i < nList.getLength(); i++){
            UserData ds = new UserData();
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

                ds.setFirstName(element
                        .getElementsByTagName("FirstName")
                        .item(0)
                        .getTextContent());
                System.out.println(element
                        .getElementsByTagName("FirstName")
                        .item(0)
                        .getTextContent());

                ds.setSurname(element
                        .getElementsByTagName("Surname")
                        .item(0)
                        .getTextContent());
                System.out.println(element
                        .getElementsByTagName("Surname")
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

    /**
     * This method initialises the data for the table view of all Current Admins.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setCellValueFactory(new PropertyValueFactory<UserData, String>("username"));
        firstName.setCellValueFactory(new PropertyValueFactory<UserData, String >("firstName"));
        surname.setCellValueFactory(new PropertyValueFactory<UserData, String >("surname"));
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

    /**
     * This method directs to the AddUserScene.
     */
    @FXML public void toAddUserScene() throws IOException{
        // if cell is not empty  TO ADD
        Main.showAddUserScene();
    }

    /**
     * This method directs to the SingleAdminScene.
     */
    @FXML public void toSingleUserScene() throws IOException {
        Main.showSingleAdminScene(tableView);
    }
}
