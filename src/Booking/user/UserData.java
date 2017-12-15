package Booking.user;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class defines the UserData and creates setters and getters as necessary
 */
public class UserData {

    public SimpleIntegerProperty serialNo = new SimpleIntegerProperty();
    public SimpleStringProperty username = new SimpleStringProperty("");;
    public SimpleStringProperty firstName = new SimpleStringProperty("");;
    public SimpleStringProperty surname = new SimpleStringProperty("");;
    public SimpleStringProperty email = new SimpleStringProperty("");;
    public SimpleStringProperty password = new SimpleStringProperty("");;

    public UserData(){this(1,"","","","","");}

    public UserData(int serial,String username, String firstName, String surname, String email, String password){
        setSerialNo(serial);
        setFirstName(firstName);
        setSurname(surname);
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }


    /**
     * Setter for User SerialNo
     */
    public void setSerialNo(Integer serialNum){
        serialNo.set(serialNum);
    }

    /**
     * Getter for Username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * Setter for Username
     */
    public void setUsername(String u) {
        username.set(u);
    }

    /**
     * Getter for First Name
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Setter for First Name
     */
    public void setFirstName(String f) {
        firstName.set(f);
    }

    /**
     * Getter for Surname
     */
    public String getSurname() {
        return surname.get();
    }

    /**
     * Setter for Surname
     */
    public void setSurname(String s) {
        surname.set(s);
    }

    /**
     * Getter for Email
     */
    public String getEmail(){
        return email.get();
    }

    /**
     * Setter for Email
     */
    public void setEmail(String e){
        email.set(e);
    }

    /**
     * Getter for Password
     */
    public String getPassword(){
        return password.get();
    }

    /**
     * Setter for Password
     */
    public void setPassword(String p){
        password.set(p);
    }
}
