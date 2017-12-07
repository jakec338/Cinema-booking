package application.Model;


import java.io.IOException;

public class Register {
    private Database database;

    Register(){
        this.database = Database.getinstance();
    }

    void registerUser(String username, String email, String password) throws IOException {
        User user = new Customer(new UserDetails(username,email,password));
        database.createUserInfo(user.getUsername(),user);
    }


}
