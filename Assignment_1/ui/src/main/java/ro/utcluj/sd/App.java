package ro.utcluj.sd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public void start(Stage primaryStage) throws Exception {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        }
        catch (Exception ex){
            ex.printStackTrace();

            return;
        }
        primaryStage.setTitle("Ping-Pong Tournament");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        stage = primaryStage;


    }

    public static void main( String[] args ) {
        launch(args);
        //System.out.println( "Hello World!" );
    }
}
