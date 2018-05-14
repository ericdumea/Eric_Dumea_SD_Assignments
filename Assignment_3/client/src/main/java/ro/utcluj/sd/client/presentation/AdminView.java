package ro.utcluj.sd.client.presentation;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminView {
    public TextField emailTF;
    public TextField nameTF;
    public Button btnUpd;
    public PasswordField passTF;
    public Button btnAdd;
    public ComboBox userCb;
    public Button btnDel1;

    private Socket socketClient;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    private String encodedImage = null;

    private Stage stage;

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

        userCb.setVisible(false);
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

    public void updUser(ActionEvent actionEvent) {
        Command command = null;
        if(emailTF.getText()!=null && passTF.getText()!=null && nameTF.getText()!=null){

            User user = new User(nameTF.getText(),emailTF.getText(),passTF.getText(),false);
            command = new Command("updateUser", user);
            Gson gson = new Gson();
            String response = null;
            try {
                output.writeObject(gson.toJson(command, Command.class));
                response = (String) input.readObject();
                command = gson.fromJson(response, Command.class);
                if (command.getCommand().equals("ok")) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User Updated").showAndWait();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error!").showAndWait();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    public boolean emailValidator(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void addUser(ActionEvent actionEvent) {
        Command command = null;
        if(emailTF.getText()!=null && passTF.getText()!=null && nameTF.getText()!=null){
            if(!emailValidator(emailTF.getText())){
                new Alert(Alert.AlertType.ERROR, "Insert valid email address").showAndWait();
                return;
            }
            User user = new User(nameTF.getText(),emailTF.getText(),passTF.getText(),false);
            command = new Command("addUser", user);
            Gson gson = new Gson();
            String response = null;
            try {
                output.writeObject(gson.toJson(command, Command.class));
                response = (String) input.readObject();
                command = gson.fromJson(response, Command.class);
                if (command.getCommand().equals("ok")) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User Added").showAndWait();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error!").showAndWait();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void delUser(ActionEvent actionEvent) {
        Command command = null;
        if(emailTF.getText()!=null && passTF.getText()!=null && nameTF.getText()!=null){

            User user = new User();
            user.setUsername(emailTF.getText());

            command = new Command("deleteUser", user);
            Gson gson = new Gson();
            String response = null;
            try {
                output.writeObject(gson.toJson(command, Command.class));
                response = (String) input.readObject();
                command = gson.fromJson(response, Command.class);
                if (command.getCommand().equals("ok")) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User Deleted").showAndWait();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error!").showAndWait();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillTF(ActionEvent actionEvent) {

    }
}
