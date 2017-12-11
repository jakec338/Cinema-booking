package Booking.filmList;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.activation.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Booking.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FilmListSceneController implements Initializable {
	
	Main main;
	@FXML private TableView<FilmData> tableView;
    @FXML private TableColumn<FilmData, String> title;
    @FXML private TableColumn<FilmData, String> director;

    
    @FXML
    public ObservableList<FilmData> parseXml() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = db.newDocumentBuilder();
        Document doc = dBuilder.parse("src/Booking/films.xml");
        doc.getDocumentElement().normalize();
        ObservableList<FilmData> data = FXCollections.observableArrayList();
        NodeList nList = doc.getElementsByTagName("Film");
        
        /// XPATH shit
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile("//Root/Film/ShowTimes/ShowTime/Seats/Seat");
        NodeList result = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        System.out.println(nodes.getLength());
        
        
        for (int i = 0; i < nodes.getLength(); i++) {
        	NamedNodeMap atts = nodes.item(i).getAttributes();;
        	System.out.println(atts.item(0).getTextContent() + nodes.item(i).getTextContent());
            Element eElement = (Element) nodes.item(i);
            
//            System.out.println("First Name : " + eElement.getElementsByTagName("Title").item(0).getTextContent());
        }
        
        //// end of XPATH shit
        
        
        int j = 1;
        for(int i = 0; i < nList.getLength(); i++){
        	FilmData ds = new FilmData();
            Node nNode = nList.item(i);
             System.out.println("\nCurrent Element :" 
                       + nNode.getNodeName());

             if (nNode.getNodeType() == Node.ELEMENT_NODE){
                 Element element = (Element) nNode;
                 ds.setTitle(element
                          .getElementsByTagName("Title")
                          .item(0)
                          .getTextContent());
                 System.out.println(element
                          .getElementsByTagName("Title")
                          .item(0)
                          .getTextContent());

                 ds.setDirector(element
                          .getElementsByTagName("Director")
                          .item(0)
                          .getTextContent());
                 System.out.println(element
                          .getElementsByTagName("Director")
                          .item(0)
                          .getTextContent());

                 ds.setSerialNo(j);
                 ++j;
             }
             data.add(ds);
        }    
		return data;
    }
    

   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<FilmData, String>("Title"));              // ("[Whatever]")THIS MUST REFLECT THE 'get' methods in the data class
        director.setCellValueFactory(new PropertyValueFactory<FilmData, String>("Director"));        // Fucking stupid, not declared anywhere
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
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML public void toSingleFilmScene() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException{
    	// if cell is not empty  TO ADD
    	main.showSingleFilmScene(tableView);
    }
}
