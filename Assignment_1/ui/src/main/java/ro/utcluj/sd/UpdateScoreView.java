package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateScoreView {

    private String mail;
    private GameCore gc = new GameCore();

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void init(){

        System.out.println("hatZ"+mail);




    }

    public void updateScorep1(ActionEvent actionEvent) {
    }

    public void updateScorep2(ActionEvent actionEvent) {
    }
}
