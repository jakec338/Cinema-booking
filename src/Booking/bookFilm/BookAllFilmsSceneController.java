package Booking.bookFilm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import Booking.filmList.FilmData;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class controls the BookAllFilmsScene, calling up the FilmData from the XML database and displaying it in a table.
 * It also serves to navigate through to a selected individual film.
 */
public class BookAllFilmsSceneController implements Initializable {

    @FXML private TableView<FilmData> tableView;
    @FXML private TableColumn<FilmData, String> title;
    @FXML private TableColumn<FilmData, String> director;
    @FXML private TableColumn<FilmData, String> description;

    /**
     * This method directs to the FilmsListScene
     */
    @FXML public ObservableList<FilmData> parseXml() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
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
            NamedNodeMap atts = nodes.item(i).getAttributes();
            System.out.println(atts.item(0).getTextContent() + nodes.item(i).getTextContent());
            Element eElement = (Element) nodes.item(i);
        }


        int j = 1;
        for(int i = 0; i < nList.getLength(); i++){
            FilmData ds = new FilmData();
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                ds.setTitle(element
                        .getElementsByTagName("Title")
                        .item(0)
                        .getTextContent());

                ds.setDirector(element
                        .getElementsByTagName("Director")
                        .item(0)
                        .getTextContent());

                ds.setDescription(element
                        .getElementsByTagName("Description")
                        .item(0)
                        .getTextContent());

                ds.setImageURL(element
                        .getElementsByTagName("imageURL")
                        .item(0)
                        .getTextContent());

                ds.setSerialNo(j);
                ++j;
            }
            data.add(ds);
        }
        return data;
    }

    /**
     * This method initialises the data for all available films for the tableview in BookAllFilmsScene
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<FilmData, String>("Title"));
        director.setCellValueFactory(new PropertyValueFactory<FilmData, String>("Director"));
        description.setCellValueFactory(new PropertyValueFactory<FilmData,String>("Description"));
        try {
            tableView.getItems().setAll(parseXml());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method directs to book a SingleFilm
     */
    @FXML public void toSingleBookingScene() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException{
        Main.showBookSingleFilmScene(tableView);
    }
}