package Booking.singleUser;

import Booking.Main;
import Booking.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.w3c.dom.*;
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
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Controller for the SingleUserScene and the UpdateProfile Scene
 */
public class EditSingleUserSceneController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField surnameField;
    @FXML Label firstNameLabel;
    @FXML Label surnameLabel;
    @FXML Label usernameLabel;
    @FXML Label emailLabel;
    @FXML Label passwordLabel;

    /**
     * This method initiates the data for the UpdateProfileScene
     */
    public void Init() throws IOException, ParserConfigurationException, SAXException {

        File fXmlFile = new File("/Users/McLaughlin/Code/Cinema-booking/src/Booking/Users.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();


        NodeList nList = doc.getElementsByTagName("User");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String sUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();
                String sFirstName = eElement.getElementsByTagName("FirstName").item(0).getTextContent();
                String sSurname = eElement.getElementsByTagName("Surname").item(0).getTextContent();
                String sEmail = eElement.getElementsByTagName("Email").item(0).getTextContent();
                String sPassword = eElement.getElementsByTagName("Password").item(0).getTextContent();



                BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/CurrentSession.txt"))));
                String fileName = "src/Booking/CurrentSession.txt";
                File file = new File(fileName);
                List<String> lines = Files.readAllLines(Paths.get("src/Booking/CurrentSession.txt"), Charset.defaultCharset());
                Scanner inputStream = new Scanner(file);
                String data = inputStream.nextLine();

                if (sUsername.equals(data)) {
                    usernameLabel.setText(sUsername);
                    firstNameLabel.setText(sFirstName);
                    surnameLabel.setText(sSurname);
                    emailLabel.setText(sEmail);
                    passwordLabel.setText(sPassword);

                }
            }
        }
    }

    /**
     * This method initiates the data for the EditSingleUserScene
     */
    public void InitEdit() throws IOException, ParserConfigurationException, SAXException {

        File fXmlFile = new File("/Users/McLaughlin/Code/Cinema-booking/src/Booking/Users.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("User");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String sUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();
                String sFirstName = eElement.getElementsByTagName("FirstName").item(0).getTextContent();
                String sSurname = eElement.getElementsByTagName("Surname").item(0).getTextContent();
                String sEmail = eElement.getElementsByTagName("Email").item(0).getTextContent();
                String sPassword = eElement.getElementsByTagName("Password").item(0).getTextContent();

                BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/CurrentSession.txt"))));
                String fileName = "src/Booking/CurrentSession.txt";
                File file = new File(fileName);
                List<String> lines = Files.readAllLines(Paths.get("src/Booking/CurrentSession.txt"), Charset.defaultCharset());
                Scanner inputStream = new Scanner(file);
                String data = inputStream.nextLine();

                if (sUsername.equals(data)) {
                    usernameField.setPromptText(sUsername);
                    firstNameField.setPromptText(sFirstName);
                    surnameField.setPromptText(sSurname);
                    emailField.setPromptText(sEmail);
                    passwordField.setPromptText(sPassword);
                    usernameField.setText(sUsername);
                    firstNameField.setText(sFirstName);
                    surnameField.setText(sSurname);
                    emailField.setText(sEmail);
                    passwordField.setText(sPassword);
                }
            }
        }
    }

    /**
     * This navigates to the EditSingleUserScene
     */
    @FXML public void toEditSingleUserScene() throws IOException, ParserConfigurationException, SAXException {
        Main.showEditSingleUserScene();
    }

    /**
     * This method updates the XML database with the newly entered details
     */
    public void handleEdit() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String filePath = "/Users/McLaughlin/Code/Cinema-booking/src/Booking/Users.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        //update Element value
        updateEmail(doc);
        updateFirstName(doc);
        updateSurname(doc);
        updatePassword(doc);
        updateUsername(doc);

        //write the updated document to file or console
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("/Users/McLaughlin/Code/Cinema-booking/src/Booking/Users.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
        PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/CurrentSession.txt")));
        writer.append(usernameField.getText() + "\n");
        writer.close();
        Main.showUserHomeScene();
    }

    /**
     * This method updates the Username
     */
    private void updateUsername(Document doc) throws ParserConfigurationException, SAXException, IOException {
        NodeList users = doc.getElementsByTagName("User");
        Element emp = null;
        //loop for each user
        BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/CurrentSession.txt"))));
        String fileName = "src/Booking/CurrentSession.txt";
        File file = new File(fileName);
        List<String> lines = Files.readAllLines(Paths.get("src/Booking/CurrentSession.txt"), Charset.defaultCharset());
        Scanner inputStream = new Scanner(file);
        String data = inputStream.nextLine();
        for (int i = 0; i < users.getLength(); i++) {
            emp = (Element) users.item(i);
            if(emp.getElementsByTagName("Username").item(0).getTextContent().equals(data)){
                Node name = emp.getElementsByTagName("Username").item(0).getFirstChild();
                name.setNodeValue(usernameField.getText());
            }
        }
    }

    /**
     * This method updates the FirstName
     */
    private void updateFirstName(Document doc) throws ParserConfigurationException, SAXException, IOException {
        NodeList users = doc.getElementsByTagName("User");
        Element emp = null;
        BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/CurrentSession.txt"))));
        String fileName = "src/Booking/CurrentSession.txt";
        File file = new File(fileName);
        List<String> lines = Files.readAllLines(Paths.get("src/Booking/CurrentSession.txt"), Charset.defaultCharset());
        Scanner inputStream = new Scanner(file);
        String data = inputStream.nextLine();
        for (int i = 0; i < users.getLength(); i++) {
            emp = (Element) users.item(i);
            if(emp.getElementsByTagName("Username").item(0).getTextContent().equals(data)){
                Node name = emp.getElementsByTagName("FirstName").item(0).getFirstChild();
                name.setNodeValue(firstNameField.getText());
            }
        }
    }

    /**
     * This method updates the Surname
     */
    private void updateSurname(Document doc) throws ParserConfigurationException, SAXException, IOException {
        NodeList users = doc.getElementsByTagName("User");
        Element emp = null;
        BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/CurrentSession.txt"))));
        String fileName = "src/Booking/CurrentSession.txt";
        File file = new File(fileName);
        List<String> lines = Files.readAllLines(Paths.get("src/Booking/CurrentSession.txt"), Charset.defaultCharset());
        Scanner inputStream = new Scanner(file);
        String data = inputStream.nextLine();
        for (int i = 0; i < users.getLength(); i++) {
            emp = (Element) users.item(i);
            if(emp.getElementsByTagName("Username").item(0).getTextContent().equals(data)){
                Node name = emp.getElementsByTagName("Surname").item(0).getFirstChild();
                name.setNodeValue(surnameField.getText());
            }
        }
    }

    /**
     * This method updates the Email
     */
    private void updateEmail(Document doc) throws ParserConfigurationException, SAXException, IOException {
        NodeList users = doc.getElementsByTagName("User");
        Element emp = null;
        BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/CurrentSession.txt"))));
        String fileName = "src/Booking/CurrentSession.txt";
        File file = new File(fileName);
        List<String> lines = Files.readAllLines(Paths.get("src/Booking/CurrentSession.txt"), Charset.defaultCharset());
        Scanner inputStream = new Scanner(file);
        String data = inputStream.nextLine();
        for (int i = 0; i < users.getLength(); i++) {
            emp = (Element) users.item(i);
            if(emp.getElementsByTagName("Username").item(0).getTextContent().equals(data)){
            Node name = emp.getElementsByTagName("Email").item(0).getFirstChild();
            name.setNodeValue(emailField.getText());
        }
        }
    }

    /**
     * This method updates the Password
     */
    private void updatePassword(Document doc) throws ParserConfigurationException, SAXException, IOException {
        NodeList users = doc.getElementsByTagName("User");
        Element emp = null;
        //loop for each user
        BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/CurrentSession.txt"))));
        String fileName = "src/Booking/CurrentSession.txt";
        File file = new File(fileName);
        List<String> lines = Files.readAllLines(Paths.get("src/Booking/CurrentSession.txt"), Charset.defaultCharset());
        Scanner inputStream = new Scanner(file);
        String data = inputStream.nextLine();
        for (int i = 0; i < users.getLength(); i++) {
            emp = (Element) users.item(i);
            if(emp.getElementsByTagName("Username").item(0).getTextContent().equals(data)){
                Node name = emp.getElementsByTagName("Password").item(0).getFirstChild();
                name.setNodeValue(passwordField.getText());
            }
        }
    }
}