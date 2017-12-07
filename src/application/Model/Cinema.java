package application.Model;

import java.io.IOException;

public class Cinema {
    private Register register;
    private User currentUser = null;

    public Cinema(){
        register = new Register();

    }

    public void registerUser(String username, String email, String password) throws IOException {
        register.registerUser(username, email, password);
    }
}
