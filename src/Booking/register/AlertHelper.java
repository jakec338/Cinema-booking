package Booking.register;

import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * Alert Helper for form validation in the Registration Scene
 */
public class AlertHelper {

    /**
     * Alert Helper for form validation in the Registration Scene
     */
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();
}
}
