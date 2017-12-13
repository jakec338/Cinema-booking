package Booking.bookFilm;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BookSingleFilmSceneController {

    Main main;
    private FilmData selectedFilm;

    @FXML
    Label titleLabel;
    @FXML
    Label directorLabel;
    @FXML
    Button deleteBtn;
    @FXML
    Button editBtn;
    @FXML
    Button getTimes;

    @FXML
    GridPane ShowingsGridPane;
    @FXML
    FlowPane flowPane;
    @FXML
    VBox seatsVbox;
    @FXML
    VBox vBox;
    @FXML
    AnchorPane seatsAnchorPane;

    @FXML
    ComboBox<String> dateComboBox;
    @FXML
    Button addShowingBtn;

    private List<Button> buttons;

    @FXML
    Rectangle seatA1;
    @FXML
    Rectangle seatA2;

    String defaultDate = new String("");

    public void initData(FilmData filmData) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        selectedFilm = filmData;
        titleLabel.setText(selectedFilm.getTitle());
        directorLabel.setText(selectedFilm.getDirector());
        datePicker();
        dateComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldDate, String newDate) {
                NodeList nodes2;
                defaultDate = newDate;
                System.out.println(defaultDate + " kjsbkjfb");
                try {
                    nodes2 = getNodes("//Title[text()='" + selectedFilm.getTitle() + "']/parent::Film/Dates/Date[@id='"
                            + newDate + "']/ShowTimes/ShowTime");

                    int numOfTimes = nodes2.getLength();
                    System.out.println(numOfTimes);
                    int numShowTimes = nodes2.getLength();
                    buttons = new ArrayList<>();
                    for (int i = 0; i < numShowTimes; i++) {
                        Button button = new Button(nodes2.item(i).getAttributes().item(0).getTextContent());
                        buttons.add(button);
                    }
                    vBox.getChildren().clear();
                    vBox.getChildren().addAll(buttons);

                    timeBtns(newDate);
                } catch (XPathExpressionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        });

    }

    public NodeList getNodes(String xpathString) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
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
        // 5.
        XPathExpression expr2 = xpath.compile(xpathString);
        NodeList result2 = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes2 = (NodeList) result2;
        return nodes2;
    }

    public void datePicker() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        ObservableList<String> options = FXCollections.observableArrayList();
        NodeList dateNodes = getNodes("//Title[text()='" + selectedFilm.getTitle() + "']/parent::Film/Dates/Date");
        int numDates = dateNodes.getLength();
        System.out.println(numDates + "number of dates");
        if (numDates != 0) {
            for (int i = 0; i < numDates; i++) {
                String date = dateNodes.item(i).getAttributes().item(0).getTextContent();
                options.add(date);
            }
        } else {
            return;
        }
        dateComboBox.getItems().addAll(options);
    }

    public void timeBtns(String selectedDate) {
        for (int i = 0; i < buttons.size(); i++) {
            final Button timeBtn = buttons.get(i);
            final int a = i;
            timeBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    try {

                        NodeList showTimeNodes = getNodes("//Title[text()='" + selectedFilm.getTitle()
                                + "']/parent::Film/Dates/Date[@id='" + selectedDate + "']/ShowTimes/ShowTime");
                        String time = showTimeNodes.item(a).getAttributes().item(0).getTextContent();

                        NodeList seatNodes = getNodes(
                                "//Title[text()='" + selectedFilm.getTitle() + "']/parent::Film/Dates/Date[@id='"
                                        + selectedDate + "']/ShowTimes/ShowTime[@id='" + time + "']/Seats/Seat");

                        int numSeats = seatNodes.getLength();
                        int columns = 2, rows = 3, horizontal = 60, vertical = 60;

                        Rectangle rect = null;
                        Label seatLabel = null;
                        for (int i = 0; i < columns; ++i) {// Iterate through
                            // columns
                            for (int j = 0; j < rows; ++j) {// Iterate through
                                // rows
                                // Color choice = chooseColor(rectColors);
                                // Method that chooses a color

                                rect = new Rectangle(horizontal * j, vertical * i, horizontal, vertical);
                                seatLabel = new Label();

                                seatLabel.setLayoutX(horizontal * (j));
                                seatLabel.setLayoutY(vertical * (i));

                                // Create a new
                                // rectangle(PosY,PosX,width,height)
                                rect.setFill(Color.AZURE);
                                rect.setStroke(Color.BLUE);
                                // Give rectangles an outline so I can see
                                // rectangles
                                String seat = null;
                                switch (i) {
                                    case 0:
                                        switch (j) {
                                            case 0:
                                                seat = "A1";
                                                break;
                                            case 1:
                                                seat = "A2";
                                                break;
                                            case 2:
                                                seat = "A3";
                                                break;
                                        }
                                        break;
                                    case 1:
                                        switch (j) {
                                            case 0:
                                                seat = "B1";
                                                break;
                                            case 1:
                                                seat = "B2";
                                                break;
                                            case 2:
                                                seat = "B3";
                                                break;
                                        }
                                        break;
                                    case 2:
                                }

                                String occupant = getNodes("//Title[text()='" + selectedFilm.getTitle()
                                        + "']/parent::Film/Dates/Date[@id='" + selectedDate
                                        + "']/ShowTimes/ShowTime[@id='" + time + "']/Seats/Seat[@id='" + seat + "']")
                                        .item(0).getTextContent();
                                if (!occupant.equals("")) {
                                    rect.setFill(Color.LIGHTGREY);
                                } else {
                                    rect.setFill(Color.ALICEBLUE);
                                }
                                seatLabel.setText(seat);

                                seatsAnchorPane.getChildren().add(rect);
                                seatsAnchorPane.getChildren().add(seatLabel);
                                // Add Rectangle to board


                            }
                        }
                    } catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

//    public void makeBooking() throws IOException, SAXException, TransformerException, ParserConfigurationException {
//        String filePath = "/Users/McLaughlin/Code/Cinema-booking/src/Booking/Users.xml";
//        File xmlFile = new File(filePath);
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder;
//        dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(xmlFile);
//        doc.getDocumentElement().normalize();
//        getNodes("//Title[text()='" + selectedFilm.getTitle()
//                + "']/parent::Film/Dates/Date[@id='" + selectedDate
//                + "']/ShowTimes/ShowTime[@id='" + time + "']/Seats/Seat[@id='" + seat + "']")
//                .item(0).getTextContent();
//
//        //update Element value
//        book(doc);
//
//
//        //write the updated document to file or console
//        doc.getDocumentElement().normalize();
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        DOMSource source = new DOMSource(doc);
//        StreamResult result = new StreamResult(new File("/Users/McLaughlin/Code/Cinema-booking/src/Booking/Users.xml"));
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.transform(source, result);
//        System.out.println("Booking made");
//        Main.showUserHomeScene();
//    }

    private void book(Document doc) throws ParserConfigurationException, SAXException, IOException {
        NodeList films = doc.getElementsByTagName("Film");
        Element emp = null;
        //loop for each film
        BufferedReader in = new BufferedReader((new FileReader(new File("src/Booking/films.txt"))));
        String fileName = "src/Booking/films.txt";
        File file = new File(fileName);
        for (int i = 0; i < films.getLength(); i++) {
            NodeList dates = doc.getElementsByTagName("Dates");

            }
        }
    }
