package ro.utcluj.sd.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class ServerApp extends Thread{

    public static FileWriter userWriter;

    public ServerApp() {
        connections = new ArrayList<ServerConnection>();
    }

    private static ServerApp instance;

    public List<ServerConnection> connections;

    public static ServerApp getInstance(){
        if(instance == null){
            instance = new ServerApp();
        }
        return instance;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(portNr);
            System.out.println("Server started on port: "+portNr);

            while(true){
                Socket client = serverSocket.accept();
                ServerConnection th = new ServerConnection(client);
                connections.add(th);
                th.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static final int portNr = 9990;

    public static void main(String[] args ) throws IOException {

        Gson gson = new GsonBuilder().create();




//        User user1 = new User();
//        user1.setName("Andrei Gog");
//        user1.setUsername("gogaiesefpealcool");
//        user1.setPassword("placealcool");
//
//        File file= new File("C:/users/dumea/Desktop/user.json");
//        if (file.exists())
//        {
//            userWriter = new FileWriter(file,true);//if file exists append to file. Works fine.
//        }
//        else
//        {
//            file.createNewFile();
//            userWriter = new FileWriter(file);
//        }
//
//        gson.toJson(user1,userWriter);
//        userWriter.close();
        ServerApp mainServer = ServerApp.getInstance();
        mainServer.start();


        //System.out.println( "Hello World!" );
    }
}
