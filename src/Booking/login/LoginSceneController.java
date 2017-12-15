package Booking.login;

import java.io.*;
import Booking.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Controller for Login Scene
 */
public class LoginSceneController {
	@FXML private Label loginLabel;
	@FXML private TextField username;
	@FXML private PasswordField password;

	/**
	 * Boolean to check if Login Details match those of Users stored in the Admins XML database
	 */
	public boolean ValidateAdminLogin() throws IOException, ParserConfigurationException, SAXException {

		File fXmlFile = new File("/Users/McLaughlin/Code/Cinema-booking/src/Booking/Admins.xml");
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
				String sPassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
				if (sUsername.equals(username.getText()) && sPassword.equals(password.getText())) {
				return true;
			}
			}
		}
		return false;
	}

	/**
	 * Boolean to check if Login Details match those of Users stored in the User XML database
	 */
	public boolean ValidateUserLogin() throws IOException, ParserConfigurationException, SAXException {

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
				String sPassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
				if (sUsername.equals(username.getText()) && sPassword.equals(password.getText())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method divides the page flow based on if the details match that of a User or of an Admin.
	 */
	@FXML public void Login() throws IOException, ParserConfigurationException, SAXException {
		if (ValidateUserLogin()){
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/CurrentSession.txt")));
            writer.append(username.getText() + "\n");
            writer.close();
			Main.showUserHomeScene();
		} else if(ValidateAdminLogin()){
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/CurrentSession.txt")));
            writer.append(username.getText() + "\n");
            writer.close();
			Main.showAdminHomeScene();
		} else {
			loginLabel.setText("Login Failed you failure");
		}
	}

}
