package ro.utcluj.sd.server.mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import ro.utcluj.sd.server.api.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Users {


    public Type userListType = new TypeToken<ArrayList<User>>() {
    }.getType();
    private ArrayList<User> users = new ArrayList<>();


    public Users() {
        this.getUserLibrary();
    }

    public void addToListRandomPeople() {
        User user1 = new User();
        user1.setName("Andrei Gog");
        user1.setUsername("gogaiesefpealcool");
        user1.setPassword("placealcool");
        user1.setAdmin(false);
        users.add(user1);

        users.add(new User("Pantis Vlad", "pantisvlad@bucuresti.ro", "tietata", false));
        users.add(new User("Adrian Crisan", "ambmw@bmw.com", "spaniolica", false));
        users.add(new User("Coman Necu", "plake@bota.com", "tietata", false));
    }

    public void saveUserLibrary() {

        Gson gson = new GsonBuilder().create();

        FileWriter userWriter = null;


        File file = new File(getClass().getResource("/users.json").toString().substring(6));
        try {
            userWriter = new FileWriter(file, false);//if file exists append to file. Works fine.
        } catch (IOException e) {
            e.printStackTrace();
        }

        //addToListRandomPeople();
        gson.toJson(users, userWriter);
        try {
            userWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUserLibrary() {
        Gson gson = new Gson();
        File file = new File(getClass().getResource("/users.json").toString().substring(6));
        try {
            users = gson.fromJson(new FileReader(file), userListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

//    public static void main(String[] args) throws IOException,FileNotFoundException {
//        Users x = new Users();
//        x.addToListRandomPeople();
//        x.users.add(new User("admin","admin","admin",true));
//        x.saveUserLibrary();
//        x.getUserLibrary();
//        System.out.println(x.users);
//    }
}
