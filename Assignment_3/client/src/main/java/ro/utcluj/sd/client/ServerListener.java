package ro.utcluj.sd.client;

import com.google.gson.Gson;
import ro.utcluj.sd.server.api.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerListener extends Thread {
    private ObjectInputStream input;


    public ServerListener(ObjectInputStream input) throws UnknownHostException, IOException {
        this.input=input;

    }


//    public void run() {
//        try {
//            while (true) {
//                String recievedString = (String) input.readObject();
//                if(recievedString.equals("NOTIFY")){
//                    System.out.println("hola");
//                }
//
//            }
//        }
//        catch(SocketException e){
//            System.out.println("Server shutting down");
//            System.exit(0);
//        }
//        catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }


}