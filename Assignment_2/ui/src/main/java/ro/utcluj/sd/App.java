package ro.utcluj.sd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.utcluj.sd.dal.impl.Dao;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.dal.impl.GameDao;
import ro.utcluj.sd.dal.impl.hibernate.HibernateGameDAO;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static Stage getStage() {
        return stage;
    }

    private static Stage stage;

    public boolean login_ready = false;
    public static DaoFactory.Type DAO_TYPE;

    public void start(Stage primaryStage) throws Exception {
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            fxmlLoader= new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            root = (Parent) fxmlLoader.load();
        }
        catch (Exception ex){
            ex.printStackTrace();

            return;
        }



        //DAO_TYPE = DaoFactory.Type.HIBERNATE;
        LoginView l = fxmlLoader.getController();
        l.DAO_TYPE = DAO_TYPE;
        primaryStage.setTitle("Ping-Pong Tournament");
        primaryStage.setScene(new Scene(root));

        primaryStage.show();

        stage = primaryStage;


    }

    public static void main( String[] args ) throws IOException {

        String line;
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(App.class.getResource("/config.txt").getFile()));

            while((line = bf.readLine()) != null){
                if(line.contains("HIBERNATE")){
                    DAO_TYPE = DaoFactory.Type.HIBERNATE;
                } else if(line.contains("JDBC")){
                    DAO_TYPE = DaoFactory.Type.JDBC;
                } else
                    throw new Exception();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
           bf.close();
        }
        launch(args);
        //System.out.println( "Hello World!" );
    }
}
