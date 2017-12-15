package Booking.register;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.*;
import javafx.stage.Window;
import Booking.Main;
import javafx.fxml.FXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Controller for the Registration Scene
 */
public class RegisterSceneController {

    private String username;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private String line;
    private List<String> userDetails = new ArrayList<String>();

    @FXML private TextField usernameTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button submitDetailsBtn;

    /**
     * This Method writes User Details (Username, First Name, Surname, Email and Password) to the User XML database
     */
    private void writeToUserXML(String usernameInput, String firstNameInput, String surnameInput, String emailInput, String passwordInput) throws ParserConfigurationException, TransformerException, SAXException, IOException{

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document xmlDoc = docBuilder.parse("src/Booking/Users.xml");
        Node root=xmlDoc.getFirstChild();

        Element user = xmlDoc.createElement("User");

        Element username = xmlDoc.createElement("Username");
        username.appendChild(xmlDoc.createTextNode(usernameInput));
        user.appendChild(username);

        Element firstName = xmlDoc.createElement("FirstName");
        firstName.appendChild(xmlDoc.createTextNode(firstNameInput));
        user.appendChild(firstName);

        Element surname = xmlDoc.createElement("Surname");
        surname.appendChild(xmlDoc.createTextNode(surnameInput));
        user.appendChild(surname);

        Element email = xmlDoc.createElement("Email");
        email.appendChild(xmlDoc.createTextNode(emailInput));
        user.appendChild(email);

        Element password = xmlDoc.createElement("Password");
        password.appendChild(xmlDoc.createTextNode(passwordInput));
        user.appendChild(password);

        root.appendChild(user);


        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tran = tf.newTransformer();
        tran.setOutputProperty(OutputKeys.INDENT, "yes");
        tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(xmlDoc);

        File file = new File("src/Booking/Users.xml");
        StreamResult stream = new StreamResult(file);
        tran.transform(source, stream);
    }

    /**
     * Handles submission and calls writeToXml method to write to the User XML database.
     */
    @FXML public void handleSubmit() throws IOException, ParserConfigurationException, TransformerException, SAXException{

        username = usernameTextField.getText();
        firstName = firstNameTextField.getText();
        surname = surnameTextField.getText();
        email = emailTextField.getText();
        password = passwordField.getText();
        userDetails.add(username);
        userDetails.add(firstName);
        userDetails.add(surname);
        userDetails.add(email);
        userDetails.add(password);

        { Window owner = submitDetailsBtn.getScene().getWindow();
            //Checks username is entered
            if(usernameTextField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter a Username");
                return;
            }
            if(firstNameTextField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter your First Name");
                return;
            }
            if(surnameTextField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter your Surname");
                return;
            }
            //Checks email is entered
            if(emailTextField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter an Email");
                return;
            }
            //Checks password is entered
            if(passwordField.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter a Password");
                return;
            }
            //Verifies minimum username length
            if(usernameTextField.getLength()<4) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Username must be at least 4 characters");
                return;
            }
            //Verifies minimum password length
            if(passwordField.getLength()<8) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Password must be at least 8 characters");
                return;
            }
            //Verifies passwords match
            if(!passwordField.getText().equals(confirmPasswordField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Passwords do not match");
                return;
            }
            //Checks for valid email
            if(!validateEmail()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter a valid Email");
                return;
            }


            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "User Added!",
                    "Welcome " + firstNameTextField.getText());}


            writeToUserXML(username, firstName, surname, email, password);

            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/filename.txt"),true));
            writer.append(username + ";" + firstName + ";" + surname + ";" + email + ";" + password + "\n");
            writer.close();


        Main.showMainItems();
    }

    /**
     * Validates email entry
     */
    private boolean validateEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailTextField.getText());
        if(m.find() && m.group().equals(emailTextField.getText())){
            return true;
        }
        else {
            return false;
        }
    }
}