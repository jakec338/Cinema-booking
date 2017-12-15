package Booking.addFilm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import Booking.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This class controls the add films scene, drawing the text entry fields and storing them in the XML database
 */
public class AddFilmSceneController {

	String title;
	String director;
	String description;
	String imageURL;
	public List<String> filmDetails = new ArrayList<String>();

	@FXML private TextField titleTextField;
	@FXML private TextField directorTextField;
	@FXML private TextArea descriptionTextField;
	@FXML private TextField imageField;

	/**
	 * This method writes Film details (Title, Director, Description and ImageURL) to the XML database
	 */
	private void writeToXML(String titleInput, String directorInput, String descriptionInput, String imageURLInput) throws ParserConfigurationException, TransformerException, SAXException, IOException{

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document xmlDoc = docBuilder.parse("src/Booking/films.xml");
		Node root=xmlDoc.getFirstChild();

		Element film = xmlDoc.createElement("Film");

		Element title = xmlDoc.createElement("Title");
		title.appendChild(xmlDoc.createTextNode(titleInput));
		film.appendChild(title);

		Element director = xmlDoc.createElement("Director");
		director.appendChild(xmlDoc.createTextNode(directorInput));
		film.appendChild(director);

		Element description = xmlDoc.createElement("Description");
		description.appendChild(xmlDoc.createTextNode(descriptionInput));
		film.appendChild(description);

		Element imageURL = xmlDoc.createElement("imageURL");
		imageURL.appendChild(xmlDoc.createTextNode(imageURLInput));
		film.appendChild(imageURL);

		Element dates = xmlDoc.createElement("Dates");

		film.appendChild(dates);

		root.appendChild(film);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer tran = tf.newTransformer();
		tran.setOutputProperty(OutputKeys.INDENT, "yes");
		tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(xmlDoc);

		File file = new File("src/Booking/films.xml");
		StreamResult stream = new StreamResult(file);
		tran.transform(source, stream);


	}

	/**
	 * This method handles the action of the submission button, setting the parameters equal to the entry fields and using the above writeToXML method to store these in the XML database
	 */
	@FXML public void handleSubmit() throws IOException, ParserConfigurationException, TransformerException, SAXException{

		title = titleTextField.getText();
		director = directorTextField.getText();
		description = descriptionTextField.getText();
		imageURL = imageField.getText();
		filmDetails.add(title);
		filmDetails.add(director);
		filmDetails.add(description);
		filmDetails.add(imageURL);

		writeToXML(title, director, description, imageURL);

		PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/filename.txt"),true));
		writer.append(title + ";" + director + "\n");
		writer.close();



		Main.showFilmsListScene();
	}
}