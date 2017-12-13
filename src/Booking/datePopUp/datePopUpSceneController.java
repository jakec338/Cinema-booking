package Booking.datePopUp;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Booking.Main;
import Booking.singleFilm.SingleFilmSceneController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class datePopUpSceneController {

	@FXML
	DatePicker datePicker;
	@FXML
	TextField timeTextField;;
	@FXML
	Button submitBtn;
	@FXML
	Button cancelBtn;

	Stage stage;
	String selectedDate;
	String selectedTime;
	String selectedFilmTitle;

	public void initData(String selectedFilmTitle, Stage stage) {
		this.selectedFilmTitle = selectedFilmTitle;
		this.stage = stage;
	}

	@FXML
	public void addShowing() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException,
			TransformerException {
		LocalDate localDate = datePicker.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
		String selectedDate = localDate.format(formatter);
		selectedTime = timeTextField.getText();

		///
		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = db.newDocumentBuilder();
		// Document doc = dBuilder.parse("src/Booking/films.xml");
		// doc.getDocumentElement().normalize();
		Document xmlDoc = dBuilder.parse("src/Booking/films.xml");
		xmlDoc.getDocumentElement().normalize();
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		// '//' in xpath means find an element anywhere in the object tree
		// This xpath is saying -
		// 1. Find an element where the title is the seleected film title
		// 2. go up to its parent film
		// 3. go down to into the showtime that matches the buttons showtime
		// 4. get all the seats associated with that showtime
		// 5.
		XPathExpression expr2 = xpath.compile("//Title[text()='" + selectedFilmTitle + "']/parent::Film/Dates");
		NodeList result2 = (NodeList) expr2.evaluate(xmlDoc, XPathConstants.NODESET);
		Node filmNode = (Node) result2.item(0);
		///
		// Node filmNode = getNodes("//Title[text()='" + selectedFilmTitle
		// +"']/parent::Film/Dates").item(0);
		// DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
		// DocumentBuilder dBuilder = db.newDocumentBuilder();

		// Should put in for loop really if you have time
		Element seatA1 = xmlDoc.createElement("Seat");
		seatA1.setAttribute("id", "A1");
		Element seatA2 = xmlDoc.createElement("Seat");
		seatA2.setAttribute("id", "A2");
		Element seatA3 = xmlDoc.createElement("Seat");
		seatA3.setAttribute("id", "A3");
		Element seatB1 = xmlDoc.createElement("Seat");
		seatB1.setAttribute("id", "B1");
		Element seatB2 = xmlDoc.createElement("Seat");
		seatB2.setAttribute("id", "B2");
		Element seatB3 = xmlDoc.createElement("Seat");
		seatB3.setAttribute("id", "B3");

		Element seats = xmlDoc.createElement("Seats");
		seats.appendChild(seatA1);
		seats.appendChild(seatA2);
		seats.appendChild(seatA3);
		seats.appendChild(seatB1);
		seats.appendChild(seatB2);
		seats.appendChild(seatB3);

		Element showTime = xmlDoc.createElement("ShowTime");
		showTime.setAttribute("id", selectedTime);
		showTime.appendChild(seats);

		Element showTimes = xmlDoc.createElement("ShowTimes");
		showTimes.appendChild(showTime);

		Element date = xmlDoc.createElement("Date");
		date.setAttribute("id", selectedDate);
		date.appendChild(showTimes);

		filmNode.appendChild(date);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer tran = tf.newTransformer();
		tran.setOutputProperty(OutputKeys.INDENT, "yes");
		tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(xmlDoc);

		File file = new File("src/Booking/films.xml");
		StreamResult stream = new StreamResult(file);
		tran.transform(source, stream);
		
		stage.close();

	}

	@FXML
	public void closeStage() {
		stage.close();

	}

}
