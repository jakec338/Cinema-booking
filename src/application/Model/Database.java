package application.Model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database ourInstance;

    static {
        try {
            ourInstance = new Database();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, User> userInfo = new HashMap<>();

    public static Database getinstance(){
        return ourInstance;
    }

    private Database() throws IOException {
        readFromDatabase();
    }

    public void createUserInfo(String username, User user) throws IOException {
        userInfo.put(username, user);
        updateDatabase(userInfo);
    }

    public User getUser(String username){
        return userInfo.get(username);
    }

    private void updateDatabase(Map<String, User> userInfo) throws IOException {
        FileWriter writer = new FileWriter("users.txt");
        for(Map.Entry info :userInfo.entrySet()){
            writer.write(info.toString() + "\n");
        }
        writer.close();
    }

    private void readFromDatabase() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) !=null){
                String[] tokens = line.split("=");
                String key = tokens[0];
                String[] valueTokens = tokens[1].split(",");

                UserDetails userDetails = new UserDetails(valueTokens[0], valueTokens[1], valueTokens[2]);

            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}
