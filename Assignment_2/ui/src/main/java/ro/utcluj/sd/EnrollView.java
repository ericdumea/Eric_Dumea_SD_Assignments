package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;

public class EnrollView {



    @FXML public Button enroll_btn;
    @FXML public ComboBox cb;
    @FXML public Label label_tour;

    private TourService tourService;

    private String userMail;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void updateCb(){
        String choice = (String) cb.getValue();
        label_tour.setText(tourService.fillEnrollLabel(choice));

    }

    public void enroll(){
        if(tourService.enroll(this.getUserMail(),(String)cb.getValue())==true){
            new Alert(Alert.AlertType.CONFIRMATION, "You have been enrolled").showAndWait();
        }
    }


    public void init(){
        tourService = new TourService(App.DAO_TYPE);
        tourService.populateEnrollCb();
        ArrayList<String> x = tourService.populateCb();
        label_tour.setText("");
        cb.getItems().addAll(x);




    }
}
