package application;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty username = new SimpleStringProperty("");
    private final SimpleStringProperty email = new SimpleStringProperty("");

    public User(){
        this("", "");
    }

    public User(String username, String email){
     setUsername(username);
     setEmail(email);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String fName) {
        username.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }

}
