package Booking.login;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Booking.Main;
import Booking.user.UserData;
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

public class LoginSceneController {

	private Main main;

	@FXML private TableView<UserData> tableView;

	@FXML private Label loginLabel;

	@FXML private TextField username;

	@FXML private PasswordField password;

	@FXML private Button loginBtn;

	private UserData selectedUser;


	public boolean ValidateAdminLogin() throws IOException, ParserConfigurationException, SAXException {

		File fXmlFile = new File("/Users/McLaughlin/Code/Cinema-booking/src/Booking/Admins.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("\n" + "Root element : " + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("User");

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element : " + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				System.out.println("Username : " + eElement.getElementsByTagName("Username").item(0).getTextContent());
				System.out.println("Email : " + eElement.getElementsByTagName("Email").item(0).getTextContent());
				System.out.println("Password : " + eElement.getElementsByTagName("Password").item(0).getTextContent());
				String sUsername = eElement.getElementsByTagName("Username").item(0).getTextContent();
				String sPassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
				if (sUsername.equals(username.getText()) && sPassword.equals(password.getText())) {
				return true;
			}
			}
		}
		return false;
	}

	public boolean ValidateUserLogin() throws IOException, ParserConfigurationException, SAXException {

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
				String sPassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
				if (sUsername.equals(username.getText()) && sPassword.equals(password.getText())) {
					return true;
				}
			}
		}
		return false;
	}

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
