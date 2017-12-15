package Booking.bookFilm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Booking.Main;
import Booking.filmList.FilmData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Controller for the Single Film booking scene
 */
public class BookSingleFilmSceneController {

    private FilmData selectedFilm;

    @FXML Label titleLabel;
    @FXML Label directorLabel;
    @FXML TextFlow descriptionTextFlow;
    @FXML FlowPane flowPane;
    @FXML VBox seatsVbox;
    @FXML VBox vBox;
    @FXML AnchorPane seatsAnchorPane;
    @FXML AnchorPane imgAnchor;
    @FXML ComboBox<String> dateComboBox;

    private List<Button> buttons;
    private List<Button> seatButtons;
    private List<String> dateList;
    private List<String> timeList;
    private List<String> seatList;
    private Label label;
    private String defaultDate = new String("");

    /**
     * This method initiates the data from the selected film as selected from the previous screen (BookAllFilmsScene).
     */
    public void initData(FilmData filmData) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        selectedFilm = filmData;
        titleLabel.setText(selectedFilm.getTitle());
        directorLabel.setText(selectedFilm.getDirector());
        Text desText = new Text(selectedFilm.getDescription());
        AtomicReference<String> imageURL = new AtomicReference<>(selectedFilm.getImageURL());
        descriptionTextFlow.getChildren().add(desText);

        System.out.println(imageURL.get());
        Image img = new Image(imageURL.get());
        ImageView iV = new ImageView();
        iV.setImage(img);
        iV.setFitWidth(img.getWidth()/3);
        iV.setFitHeight(img.getHeight()/3);

        imgAnchor.getChildren().add(iV);
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

    /**
     * This method navigates the XML database requiring only an xPath string for further use.
     */
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

    /**
     * This method provides the dates for the drop down dates box in the booking scene.
     */
    public void datePicker() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        ObservableList<String> options = FXCollections.observableArrayList();
        NodeList dateNodes = getNodes("//Title[text()='" + selectedFilm.getTitle() + "']/parent::Film/Dates/Date");
        int numDates = dateNodes.getLength();
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

    /**
     * This method controls the creation and action of the buttons on the booking scene.
     */
    public void timeBtns(String selectedDate) throws IOException {
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
                        seatButtons = new ArrayList<>();
                        dateList = new ArrayList<>();
                        timeList = new ArrayList<>();
                        seatList = new ArrayList<>();
                        int x = 0;
                        for (int i = 0; i < columns; ++i) {
                            for (int j = 0; j < rows; ++j) {

                                rect = new Rectangle(horizontal * j, vertical * i, horizontal, vertical);
                                seatLabel = new Label();

                                seatLabel.setLayoutX(horizontal * (j));
                                seatLabel.setLayoutY(vertical * (i));

                                rect.setFill(Color.AZURE);
                                rect.setStroke(Color.BLUE);

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
                                }


                                Button seatButton = new Button(seatNodes.item(x).getAttributes().item(0).getTextContent());
                                seatButton.setText(seat);
                                seatButtons.add(seatButton);

                                seatButtons.get(x).setLayoutX(horizontal * (j));
                                seatButtons.get(x).setLayoutY(vertical * (i));


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


                                dateList.add(selectedDate);
                                timeList.add(time);
                                seatList.add(seat);
                                seatsAnchorPane.getChildren().add(rect);
                                seatsAnchorPane.getChildren().add(seatLabel);
                                seatsAnchorPane.getChildren().addAll(seatButtons.get(x));
                                // Add Rectangle to board

                                x++;
                            }
                        }
//                        showConfirmation();
                        addUserToShowing(); // ADD AFTER FOR LOOP
                    } catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * This method books the current User into a film in the seat clicked, the Confirmation pop up is called.
     */
    public void addUserToShowing() throws IOException {
        int x = seatButtons.size();

        for (int i = 0; i < x; i++) {
            final int ii = i;
            String date = dateList.get(ii);
            String time = timeList.get(ii);
            String seat = seatList.get(ii);


            seatButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    try {
                        showConfirmation(date, time, seat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        NodeList seatNode = getNodes("//Title[text()='" + selectedFilm.getTitle()
                                + "']/parent::Film/Dates/Date[@id='" + date
                                + "']/ShowTimes/ShowTime[@id='" + time + "']/Seats/Seat[@id='" + seat + "']");

                        String fileName = "src/Booking/CurrentSession.txt";
                        File currentSession = new File(fileName);
                        Scanner inputStream = new Scanner(currentSession);
                        String user = inputStream.nextLine();
                        seatNode.item(0).setTextContent(user);



                        TransformerFactory tf = TransformerFactory.newInstance();
                        Transformer tran = tf.newTransformer();
                        tran.setOutputProperty(OutputKeys.INDENT, "yes");
                        tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                        DOMSource source = new DOMSource(seatNode.item(0).getOwnerDocument());

                        File file = new File("src/Booking/films.xml");
                        StreamResult stream = new StreamResult(file);
                        tran.transform(source, stream);
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
                    } catch (TransformerConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (TransformerException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            });
        }
    }

    /**
     * This method calls a confirmation pop up upon clicking to book a seat.
     * When Ok is clicked, this pop up disappears and the booking is completed.
     * If cancel is clicked the pop up disappears but the booking is not completed.
     */
    private void showConfirmation(String date, String time, String seat) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Booking Confirmation");
        alert.setHeaderText("Are these details correct?");
        alert.setContentText("Film : " + selectedFilm.getTitle() + "\n" + "Date : " + date + "\n" + "Time : " + time + "\n" + "Seat : " + seat);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("No selection!");
        } else if (option.get() == ButtonType.OK) {
            addUserToShowing();
            Main.showBookingScene();
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Cancelled!");
        } else {
            this.label.setText("-");
        }
    }
}