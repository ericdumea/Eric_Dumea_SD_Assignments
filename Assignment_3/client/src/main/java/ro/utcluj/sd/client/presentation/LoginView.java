package ro.utcluj.sd.client.presentation;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observer;

public class LoginView {
    @FXML
    public Button loginButton;
    @FXML
    public TextField emailTF;
    @FXML
    public PasswordField passTF;

    private Observer observator;
    private Socket socketClient;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Stage stage;

    public Observer getObservator() {
        return observator;
    }

    public void setObservator(Observer observator) {
        this.observator = observator;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

    public void login() {
        Command x = new Command("login", new User(emailTF.getText(), passTF.getText()));
        Gson gson = new Gson();
        String json = gson.toJson(x);
        String jsonToRead = null;
        try {
            output.writeObject(json);
            jsonToRead = (String) input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (jsonToRead != null) {
            x = gson.fromJson(jsonToRead, Command.class);
            if (x.getCommand().equals("loginok")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/WriterView.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    Scene adminScene = new Scene(root1);
                    WriterView l = fxmlLoader.getController();
                    l.setOutput(output);
                    l.setSocketClient(socketClient);
                    l.setInput(input);
                    l.setWriter(x.getUser());
                    l.setObservator(observator);
                    l.init();
                    stage.setScene(adminScene);
                    stage.show();
                    l.setStage(stage);
                    this.stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (x.getCommand().equals("admin")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminView.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    Scene adminScene = new Scene(root1);
                    AdminView l = fxmlLoader.getController();
                    l.setOutput(output);
                    l.setSocketClient(socketClient);
                    l.setInput(input);
                    //l.init();
                    stage.setScene(adminScene);
                    stage.show();
                    l.setStage(stage);
                    this.stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Error logging in, try again").showAndWait();
            }
        }

    }

}
