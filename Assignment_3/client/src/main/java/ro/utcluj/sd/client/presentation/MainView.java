package ro.utcluj.sd.client.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observer;

public class MainView implements Observer {

    @FXML public Button login_btn;
    @FXML public ListView<String> article_lv;

    private Socket socketClient;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    @FXML private TextArea abstract_ta;

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

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }

    public void login(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene adminScene = new Scene(root1);
            LoginView l = fxmlLoader.getController();
            l.setOutput(output);
            l.setSocketClient(socketClient);
            l.setInput(input);
            l.setObservator(this);
            stage.setScene(adminScene);
            stage.show();
            l.setStage(stage);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(){
        String sel = article_lv.getSelectionModel().getSelectedItem();
        System.out.println(sel);

        Command command = new Command("getArticle",new Article(sel));

        Gson gson = new Gson();

        try {
            output.writeObject(gson.toJson(command,Command.class));

            String response = (String) input.readObject();
            command = gson.fromJson(response, Command.class);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(command.getCommand().equals("error")){
            new Alert(Alert.AlertType.ERROR, "Error while fetching article").showAndWait();
            return;
        }

        abstract_ta.setText(command.getArticle().getAbstractt());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArticleView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene adminScene = new Scene(root1);
            ArticleView l = fxmlLoader.getController();
            l.setOutput(output);
            l.setSocketClient(socketClient);
            l.setInput(input);
            l.setArticle(command.getArticle());
            l.init();
            stage.setScene(adminScene);
            stage.show();
            l.setStage(stage);
        } catch(Exception e) {
            e.printStackTrace();
        }




    }

    public void init(){

        Command x = new Command("getArticlesList");
        Gson gson = new Gson();
        String response = null;
        Command lista=null;
        try {
            output.writeObject(gson.toJson(x,Command.class));

            response = (String) input.readObject();

            if(response!=null){
                System.out.println(response);
                lista = gson.fromJson(response,Command.class);
                System.out.println(lista.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        article_lv.getItems().addAll(lista.getStringField());
        article_lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {



        Command x = new Command("getArticlesList");
        Gson gson = new Gson();
        String response = null;
        Command lista=null;
        try {
            output.writeObject(gson.toJson(x,Command.class));

            response = (String) input.readObject();

            if(response!=null){
                System.out.println(response);
                lista = gson.fromJson(response,Command.class);
                System.out.println(lista.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        article_lv.getItems().clear();
        article_lv.getItems().addAll(lista.getStringField());
        article_lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
}
