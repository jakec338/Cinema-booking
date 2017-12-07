package application.Model;

public abstract class User {

    private UserDetails userDetails;

    User(UserDetails userDetails){
        this.userDetails = userDetails;
    }

    public String getUsername(){
        return userDetails.getUsername();
    }

    public String getEmail(){
        return userDetails.getEmail();
    }

    public String getPassword(){
        return userDetails.getPassword();
    }

    public void setUsername(String username){
        userDetails.setUsername(username);
    }

    public void setEmail(String email){
        userDetails.setEmail(email);
    }

    public void setPassword(String password){
        userDetails.setPassword(password);
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    @Override
    public String toString(){
        return userDetails.toString;
    }
}
