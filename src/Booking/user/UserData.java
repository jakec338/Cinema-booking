package Booking.user;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserData {

    public SimpleIntegerProperty serialNo = new SimpleIntegerProperty();
    public SimpleStringProperty username = new SimpleStringProperty("");;
    public SimpleStringProperty firstName = new SimpleStringProperty("");;
    public SimpleStringProperty surname = new SimpleStringProperty("");;
    public SimpleStringProperty email = new SimpleStringProperty("");;
    public SimpleStringProperty password = new SimpleStringProperty("");;

    public UserData(){this(1,"","","","","");}

    public UserData(int serial,String username, String firstname, String surname, String email, String password){
        setSerialNo(serial);
        setUsername(username);
        setEmail(email);
        setPassword(password);    }

    public Integer getSerialNo(){
        return serialNo.get();
    }

    public void setSerialNo(Integer serialNum){
        serialNo.set(serialNum);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String u) {
        username.set(u);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String f) {
        firstName.set(f);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String s) {
        surname.set(s);
    }

    public String getEmail(){
        return email.get();
    }

    public void setEmail(String e){
        email.set(e);
    }

    public String getPassword(){
        return password.get();
    }

    public void setPassword(String p){
        password.set(p);
    }
}
