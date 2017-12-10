package Booking.user;

public class UserData {

    private String username;
    private String email;
    private String password;

    public UserData(){

        // Default values
        this.username = "";
        this.email = "";
        this.password = "";
    }

    public UserData(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String director) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String director) {
        this.password = password;
    }

}
