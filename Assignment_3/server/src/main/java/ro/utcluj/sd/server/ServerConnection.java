package ro.utcluj.sd.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jdk.internal.org.objectweb.asm.Type;
import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;
import ro.utcluj.sd.server.command.CommandFactory;
import ro.utcluj.sd.server.command.CommandIF;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ServerConnection extends Thread {

    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    public List<ServerConnection> connections;

    private Socket socket;

    public ServerConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){


        try {
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
            while(true) {

                String recievedJson = (String) inStream.readObject();
                Gson gson = new Gson();
                Command recieved = gson.fromJson(recievedJson, new TypeToken<Command>() {
                }.getType());

                CommandIF command = CommandFactory.getCommand(recieved);
                Command x = command.execute(recieved);


                outStream.writeObject(gson.toJson(x));

                //outStream.writeObject("NOTIFY");
            }


        } catch(SocketException e){
            System.out.println("Another one disconnects");
            return;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
