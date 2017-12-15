package Booking.pastBookings;

import Booking.filmList.FilmData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PastBookingsSceneController implements Initializable{
    @FXML
    private TableView<BookingData> tableView;
    @FXML
    private TableColumn<BookingData, String> title;
    @FXML
    private TableColumn<BookingData, String> showDate;
    @FXML
    private TableColumn<BookingData, String> showTime;
    @FXML
    private TableColumn<BookingData, String> seat;


    @FXML
    public ObservableList<BookingData> parseXml() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = db.newDocumentBuilder();
        Document doc = dBuilder.parse("src/Booking/films.xml");
        doc.getDocumentElement().normalize();
        ObservableList<BookingData> data = FXCollections.observableArrayList();
        NodeList nList = doc.getElementsByTagName("Film");

        /// XPATH shit
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile("//Root/Film/ShowTimes/ShowTime/Seats/Seat");
        NodeList result = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;


        for (int i = 0; i < nodes.getLength(); i++) {
            NamedNodeMap atts = nodes.item(i).getAttributes();
            System.out.println(atts.item(0).getTextContent() + nodes.item(i).getTextContent());
            Element eElement = (Element) nodes.item(i);
        }

        //// end of XPATH shit


        int j = 1;
        for(int i = 0; i < nList.getLength(); i++){
            BookingData ds = new BookingData();
            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element : "
                    + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                String fileName = "src/Booking/CurrentSession.txt";
                File currentSession = new File(fileName);
                Scanner inputStream = new Scanner(currentSession);
                String user = inputStream.nextLine();
                if(element.getElementsByTagName("Seat").item(0).getTextContent().equals(user)){
                    ds.setTitle(element
                            .getElementsByTagName("Title")
                            .item(0)
                            .getTextContent());
                    ds.setShowDate(element.getChildNodes().item(0).getTextContent());
                    System.out.println(element.getChildNodes().item(0).getTextContent());


                }

                ds.setSerialNo(j);


                ++j;
            }
            data.add(ds);
        }
        return data;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<BookingData, String>("Title"));              // ("[Whatever]")THIS MUST REFLECT THE 'get' methods in the data class
        showDate.setCellValueFactory(new PropertyValueFactory<BookingData, String>("Date"));
        showTime.setCellValueFactory(new PropertyValueFactory<BookingData, String>("Showtime"));                                                                                                    // Fucking stupid, not declared anywhere
        seat.setCellValueFactory(new PropertyValueFactory<BookingData,String>("Seat"));
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

    @FXML
    public void toSingleBookingScene(){

    }


}

