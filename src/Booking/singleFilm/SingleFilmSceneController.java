package Booking.singleFilm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import Booking.Main;
import Booking.filmList.FilmData;
import Booking.filmList.FilmListSceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SingleFilmSceneController {
	
	Main main;
	FilmListSceneController cont;
	private FilmData selectedFilm;
	
	@FXML Label titleLabel;
	@FXML Label directorLabel;
	@FXML Button deleteBtn;
	@FXML Button editBtn;
	@FXML Button getTimes;
	
	@FXML GridPane ShowingsGridPane;
	@FXML FlowPane flowPane;
	@FXML VBox seatsVbox;
	@FXML VBox vBox;
	@FXML AnchorPane seatsAnchorPane;
	
	private List<Button> buttons;
	
	
	@FXML Rectangle seatA1;
	@FXML Rectangle seatA2;
	
	
	
	
	public void initData(FilmData filmData) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		selectedFilm = filmData;
		titleLabel.setText(selectedFilm.getTitle());
		directorLabel.setText(selectedFilm.getDirector());
		
			NodeList nodes2 = getShowTimeNodes();
			
	        int numOfTimes = nodes2.getLength();
	        System.out.println(numOfTimes);
	        int numShowTimes = nodes2.getLength();
	        buttons = new ArrayList<>();
	        for (int i =0; i <  numShowTimes; i++){
	        	Button button = new Button(nodes2.item(i).getAttributes().item(0).getTextContent());
	            buttons.add(button);
	        }
	        vBox.getChildren().addAll(buttons);
	        
	        ///
			timeBtns();
	        ///
	}
	
	public NodeList getShowTimeNodes() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException{
		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = db.newDocumentBuilder();
        Document doc = dBuilder.parse("src/Booking/films.xml");
        doc.getDocumentElement().normalize();
        
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        
        // '//' in xpath means find an element anywhere in the object tree
        XPathExpression expr2 = xpath.compile("//Title[text()='" + selectedFilm.getTitle() +"']/parent::Film/ShowTimes/ShowTime");
        NodeList result2 = (NodeList)expr2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes2 = (NodeList) result2;
		return nodes2;
	}
	
	// throwing into completely different methods not ideal, should only be one with the xpath string as an input 
	// gets fucked up when it is used in Main class so will stick with each list of nodes having their own method for now
	public NodeList getSeatNodes(String time) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException{
		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = db.newDocumentBuilder();
        Document doc = dBuilder.parse("src/Booking/films.xml");
        doc.getDocumentElement().normalize();
        
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        // '//' in xpath means find an element anywhere in the object tree
        // This xpath is saying -
        // 1. Find an element where the title is the seleected film title 
        // 2. go up to its parent film 
        // 3. go down to into the showtime that matches the buttons showtime 
        // 4. get all the seats associated with that showtime
        XPathExpression expr2 = xpath.compile("//Title[text()='" + selectedFilm.getTitle()
        										+"']/parent::Film/ShowTimes/ShowTime[@id='" + time +"']/Seats/Seat");
        NodeList result2 = (NodeList)expr2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes2 = (NodeList) result2;
		return nodes2;
	}

	public void timeBtns(){
		for (int i = 0; i < buttons.size(); i++){
			final Button timeBtn = buttons.get(i);
			final int a  = i;
			timeBtn.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	            	try {
						NodeList nodes = getShowTimeNodes();
						String b = nodes.item(a).getAttributes().item(0).getTextContent();
							//
							NodeList seatNodes = getSeatNodes(b);
							int numSeats = seatNodes.getLength();
							
							int columns = 2, rows = 3 , horizontal = 60, vertical = 60;
							
					        Rectangle rect = null;
					        Label seatLabel = null;
					        for(int i = 0; i < columns; ++i)
					        {//Iterate through columns
					            for(int j = 0; j < rows; ++j)
					            {//Iterate through rows
//					              Color choice = chooseColor(rectColors);
					                //Method that chooses a color

					                rect = new Rectangle(horizontal*j, vertical*i, horizontal, vertical);
					                seatLabel = new Label();
					                
					                seatLabel.setLayoutX(horizontal*(j+1));
					                seatLabel.setLayoutY(vertical*(i+1));
					               
					                //Create a new rectangle(PosY,PosX,width,height)
					                rect.setFill(Color.AZURE);
					                rect.setStroke(Color.BLUE);
					                //Give rectangles an outline so I can see rectangles
					                String seat = null;
					                switch (i){
					                case 0:
					                	switch(j){
					                	case 0:
					                		seat = "A1";
					                		break;
					                	case 1:
					                		seat = "B1";
					                		break;
					                	}
					                case 1: 
					                	switch(j){
					                	case 0:
					                		seat = "A2";
					                		break;
					                	case 1:
					                		seat = "B2";
					                		break;
					                	}
					                case 2:
					                	switch(j){
					                	case 0:
					                		seat = "A3";
					                		break;
					                	case 1:
					                		seat = "B3";
					                		break;
					                	}
					                }
					                
					                seatLabel.setText(seat);

					                seatsAnchorPane.getChildren().add(rect);
					                seatsAnchorPane.getChildren().add(seatLabel);
					                //Add Rectangle to board

					            }
					        }
							
							
//							seats = new ArrayList<>();
//							for (int i =0; i < numSeats; i++){
//								Rectangle seat = new Rectangle();
//								seats.add(seat);
//							}
//							seatsVbox.getChildren().clear();
//							seatsVbox.getChildren().addAll(seats);
//							for (int i =0; i < seatNodes.getLength(); i++)
//								System.out.println(seatNodes.item(i).getAttributes().item(0).getTextContent());
							
//							USE XPATH TO GET ALL THE SEATS WHERE THE FILM IS SELECTED FILM AND THE SHOW TIME IS THE SELECTED ONE
							

					} catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	                
	            }
	        });
		}
	}
	

	
	public static Node searchByTitle(String title, Document doc){
		NodeList node = doc.getElementsByTagName("Title");
		for (int i = 0; i < node.getLength(); i++){
			String content = node.item(i).getTextContent();
			if (content.equalsIgnoreCase(title)){                    // title will refer to selectedFilm.getTitle
				Node p = node.item(i).getParentNode();
				System.out.println(node.item(0).getTextContent()+ "please?");
				// correct node for sure
				return p;     
			}
		}
		return null;
	}
	
	@FXML
	public void deleteNode() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, TransformerException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(new File("src/Booking/films.xml"));
		DocumentTraversal traversal = (DocumentTraversal) doc;
		Node a = doc.getDocumentElement();
		String titleDelete = selectedFilm.getTitle();
		NodeIterator iterator = traversal.createNodeIterator(a, NodeFilter.SHOW_ELEMENT, null, true);
		Element b = null;
		for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {
			System.out.println(n.getTextContent());
		    Element e = (Element) n;
		    if (titleDelete.equals(e.getTextContent())) {
		    	System.out.println(e.getTextContent() + "WWWWWWWWWWWWWW");
		        b = e;
		        a.removeChild(b.getParentNode());
		    }
		}
	System.out.println("checkers");
  	  TransformerFactory tf = TransformerFactory.newInstance();
  	  Transformer tran = tf.newTransformer();
  	  tran.setOutputProperty(OutputKeys.INDENT, "yes");
  	  tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
  	  DOMSource source = new DOMSource(a);
  	
  	  
  	  File file = new File("src/Booking/films.xml");
  	  StreamResult stream = new StreamResult(file);
  	  tran.transform(source, stream);
  	  System.out.println("?????");
  	  
  	 main.showFilmsListScene();

	}
	
}

