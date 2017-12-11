package Booking.addFilm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import Booking.Main;
import Booking.filmList.FilmData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddFilmSceneController {
	
	  Main main;
	  String title;
	  String director;
	  String line;
	  public List<String> filmDetails = new ArrayList<String>();
	
	  @FXML
	  private Label titleLabel;
	  @FXML
	  private TextField titleTextField;
	  @FXML
	  private Label directorLabel;
	  @FXML
	  private TextField directorTextField;  
	  @FXML
	  private Button submitFilmBtn;


      private void writeToXML(String titleInput, String directorInput) throws ParserConfigurationException, TransformerException, SAXException, IOException{
    	  
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
    	  
    	  root.appendChild(film);

    	  
    	  TransformerFactory tf = TransformerFactory.newInstance();
    	  Transformer tran = tf.newTransformer();
    	  tran.setOutputProperty(OutputKeys.INDENT, "yes");
    	  tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    	  DOMSource source = new DOMSource(xmlDoc);
    	  
    	  File file = new File("src/Booking/films.xml");
    	  StreamResult stream = new StreamResult(file);
    	  tran.transform(source, stream);
    	  System.out.println("?????");

      }

	  
	  @FXML
	  public void handleSubmit() throws IOException, ParserConfigurationException, TransformerException, SAXException{
	    	
		  title = titleTextField.getText();
		  director = directorTextField.getText();
		  filmDetails.add(title);
		  filmDetails.add(director);
		  
		  writeToXML(title, director);
		  
		  PrintWriter writer = new PrintWriter(new FileOutputStream(new File("src/Booking/filename.txt"),true));
	      writer.append(title + ";" + director + "\n");
	      writer.close();
	      
	      
	       
	       main.showFilmsListScene();
		}
}
