package ro.utcluj.sd.client.presentation;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ro.utcluj.sd.server.api.Article;
import ro.utcluj.sd.server.api.Command;
import ro.utcluj.sd.server.api.User;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class WriterView extends Observable {

    @FXML
    public TextArea article_content;
    @FXML
    public TextArea abstract_content;
    @FXML
    public TextField title_tf;
    @FXML
    public Button img_btn;
    @FXML
    public ListView<String> related_lv;
    @FXML
    public Button add_btn;
    @FXML
    public ComboBox articleCb;
    @FXML
    public Button update_btn;
    @FXML
    public Button delete_btn;


    private Socket socketClient;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private String encodedImage = null;

    private User writer;


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

    private Observer observator;

    public Observer getObservator() {
        return observator;
    }

    public void setObservator(Observer observator) {
        this.observator = observator;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void insert_img(MouseEvent mouseEvent) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG images (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this.stage);
        System.out.println(file);
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            BASE64Encoder base = new BASE64Encoder();
            encodedImage = base.encode(baos.toByteArray());
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addArticle(MouseEvent mouseEvent) {
        Command command = null;
        Article article = null;
        if (!title_tf.getText().equals("") && !article_content.getText().equals("")) {
            article = new Article(writer.getName(), title_tf.getText(), article_content.getText());

            if (!abstract_content.getText().equals("")) {
                article.setAbstractt(abstract_content.getText());
            }

            if (encodedImage != null) {
                article.setImage(encodedImage);
            }

            if (!related_lv.getSelectionModel().getSelectedItems().isEmpty()) {
                String[] aux;
                System.out.println(related_lv.getSelectionModel().getSelectedItems());
                ObservableList<String> x = related_lv.getSelectionModel().getSelectedItems();
                aux = new String[x.size()];
                for (int i = 0; i < x.size(); i++) {
                    aux[i] = x.get(i);
                }
                article.setRelated_titles(aux);
            }

        } else return;

        command = new Command("addArticle", article);

        Gson gson = new Gson();
        String response = null;

        try {
            output.writeObject(gson.toJson(command, Command.class));

            response = (String) input.readObject();

            command = gson.fromJson(response, Command.class);

            if (command.getCommand().equals("ok")) {
                new Alert(Alert.AlertType.CONFIRMATION, "Article Added").showAndWait();
                setChanged();
                notifyObservers();
                updatecb();
                return;
            } else {
                new Alert(Alert.AlertType.ERROR, "Error!").showAndWait();
                return;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }

    public void updateFields(ActionEvent actionEvent) {


        Command command = new Command("getArticle", new Article((String) articleCb.getValue()));

        Gson gson = new Gson();

        try {
            output.writeObject(gson.toJson(command, Command.class));

            String response = (String) input.readObject();
            command = gson.fromJson(response, Command.class);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (command.getCommand().equals("error")) {
            new Alert(Alert.AlertType.ERROR, "Error while fetching article").showAndWait();
            return;
        }

        Article article = command.getArticle();

        abstract_content.setText("By " + article.getWriter() + "\n" + article.getAbstractt());
        article_content.setText(article.getContent());
        title_tf.setText(article.getTitle());




    }

    public void updateArticle(ActionEvent actionEvent) {

        Command command;
        Article article = null;
        if (title_tf.getText().equals((String) articleCb.getValue()) && !article_content.getText().equals("")) {
            article = new Article(writer.getName(), title_tf.getText(), article_content.getText());

            if (!abstract_content.getText().equals("")) {
                article.setAbstractt(abstract_content.getText());
            }

            if (encodedImage != null) {
                article.setImage(encodedImage);
            }

            if (!related_lv.getSelectionModel().getSelectedItems().isEmpty()) {
                String[] aux;
                System.out.println(related_lv.getSelectionModel().getSelectedItems());
                ObservableList<String> x = related_lv.getSelectionModel().getSelectedItems();
                aux = new String[x.size()];
                for (int i = 0; i < x.size(); i++) {
                    aux[i] = x.get(i);
                }
                System.out.println(aux.toString());
                article.setRelated_titles(aux);
            }

        } else {
            System.out.println("ceva problema?");
        }

        command = new Command("updateArticle", article);

        Gson gson = new Gson();
        String response = null;

        try {
            output.writeObject(gson.toJson(command, Command.class));

            response = (String) input.readObject();

            command = gson.fromJson(response, Command.class);

            if (command.getCommand().equals("ok")) {
                new Alert(Alert.AlertType.CONFIRMATION, "Article Updated").showAndWait();

            } else {
                new Alert(Alert.AlertType.ERROR, "Error!").showAndWait();

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();
        updatecb();
    }

    void updatecb(){
        Command x = new Command("getArticlesList");
        Gson gson = new Gson();
        String response = null;

        this.addObserver(observator);
        Command lista = null;
        try {
            output.writeObject(gson.toJson(x, Command.class));

            response = (String) input.readObject();

            if (response != null) {
                System.out.println(response);
                lista = gson.fromJson(response, Command.class);
                System.out.println(lista.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        related_lv.getItems().clear();
        related_lv.getItems().addAll(lista.getStringField());
        related_lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        articleCb.getItems().clear();
        articleCb.getItems().addAll(lista.getStringField());
    }

    public void deleteArticle(ActionEvent actionEvent) {

        if (articleCb.getValue() == null) return;

        Command command = new Command("deleteArticle", new Article((String) articleCb.getValue()));

        Gson gson = new Gson();
        String response = null;

        try {
            output.writeObject(gson.toJson(command, Command.class));

            response = (String) input.readObject();

            command = gson.fromJson(response, Command.class);

            if (command.getCommand().equals("ok")) {
                new Alert(Alert.AlertType.CONFIRMATION, "Article Updated").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error!").showAndWait();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();

    }

    public void init() {
        Command x = new Command("getArticlesList");
        Gson gson = new Gson();
        String response = null;

        this.addObserver(observator);
        Command lista = null;
        try {
            output.writeObject(gson.toJson(x, Command.class));

            response = (String) input.readObject();

            if (response != null) {
                System.out.println(response);
                lista = gson.fromJson(response, Command.class);
                System.out.println(lista.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        related_lv.getItems().addAll(lista.getStringField());
        related_lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        articleCb.getItems().addAll(lista.getStringField());


    }

}
