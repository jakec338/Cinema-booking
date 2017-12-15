package Booking.singleUser;

import java.io.*;
import Booking.Main;
import Booking.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Controller for the SingleUserScene
 */
public class SingleUserSceneController {

    private UserData selectedUser;

    @FXML Label usernameLabel;
    @FXML Label firstNameLabel;
    @FXML Label surnameLabel;
    @FXML Label emailLabel;
    @FXML Label passwordLabel;
    @FXML Button deleteBtn;

    /**
     * This method initiates the Data from the User XML database to be displayed as the User's current details.
     */
    public void initData(UserData userData){
        selectedUser = userData;
        usernameLabel.setText(selectedUser.getUsername());
        firstNameLabel.setText(selectedUser.getFirstName());
        surnameLabel.setText(selectedUser.getSurname());
        emailLabel.setText(selectedUser.getEmail());
        passwordLabel.setText(selectedUser.getPassword());
    }

    /**
     * This method removes a User by Username from the Users XML database.
     */
    @FXML public void deleteUser() throws ParserConfigurationException, SAXException, IOException, TransformerException {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    Document doc = factory.newDocumentBuilder().parse(new File("src/Booking/Users.xml"));
    DocumentTraversal traversal = (DocumentTraversal) doc;
    Node a = doc.getDocumentElement();
    String userDelete = selectedUser.getUsername();
    NodeIterator iterator = traversal.createNodeIterator(a, NodeFilter.SHOW_ELEMENT, null, true);
    Element b = null;
    for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {
        System.out.println(n.getTextContent());
        Element e = (Element) n;
        if (userDelete.equals(e.getTextContent())) {
            b = e;
            a.removeChild(b.getParentNode());
        }
    }
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer tran = tf.newTransformer();
    tran.setOutputProperty(OutputKeys.INDENT, "yes");
    tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    DOMSource source = new DOMSource(a);

    File file = new File("src/Booking/Users.xml");
    StreamResult stream = new StreamResult(file);
    tran.transform(source, stream);

    Main.showCurrentUsersScene();

}

    /**
     * This method removes an Admin by Username from the Admins XML database.
     */
    @FXML public void deleteAdmin() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().parse(new File("src/Booking/Admins.xml"));
        DocumentTraversal traversal = (DocumentTraversal) doc;
        Node a = doc.getDocumentElement();
        String adminDelete = selectedUser.getUsername();
        NodeIterator iterator = traversal.createNodeIterator(a, NodeFilter.SHOW_ELEMENT, null, true);
        Element b = null;
        for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {
            System.out.println(n.getTextContent());
            Element e = (Element) n;
            if (adminDelete.equals(e.getTextContent())) {
                b = e;
                a.removeChild(b.getParentNode());
            }
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tran = tf.newTransformer();
        tran.setOutputProperty(OutputKeys.INDENT, "yes");
        tran.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(a);

        File file = new File("src/Booking/Admins.xml");
        StreamResult stream = new StreamResult(file);
        tran.transform(source, stream);

        Main.showCurrentAdminsScene();

    }
}