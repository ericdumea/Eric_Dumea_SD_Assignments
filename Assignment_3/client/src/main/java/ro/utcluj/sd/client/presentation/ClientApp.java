package ro.utcluj.sd.client.presentation;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.utcluj.sd.client.ServerListener;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class ClientApp extends Application {

    private Socket socketClient;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerListener listener;



    public ClientApp(){
        super();
        try {
            socketClient = new Socket("localhost", 9990);
            output = new ObjectOutputStream(socketClient.getOutputStream());
            input = new ObjectInputStream(socketClient.getInputStream());
            listener = new ServerListener(input);
            listener.start();
        } catch (IOException e) {

            //aia e
        }
    }

    @Override
    public void start(Stage primaryStage){
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            fxmlLoader= new FXMLLoader(getClass().getResource("/MainView.fxml"));
            MainView view = fxmlLoader.getController();
            root = (Parent) fxmlLoader.load();

            //view.setOutput(output);
            //view.setSocketClient(socketClient);
            //view.init();


        }
        catch (Exception ex){
            ex.printStackTrace();
            return;
        }

        //DAO_TYPE = DaoFactory.Type.HIBERNATE;

        primaryStage.setTitle("Article Reader");
        primaryStage.setScene(new Scene(root));
        MainView view = fxmlLoader.getController();
        view.setOutput(output);
        view.setSocketClient(socketClient);
        view.setInput(input);
        view.init();
        primaryStage.show();
    }


    public static void main( String[] args ) throws IOException {

        ClientApp x = new ClientApp();
       // x.sendSmth();

        x.launch(args);
        //launch(args);

       // System.out.println( "Hello World!" );
    }


}
