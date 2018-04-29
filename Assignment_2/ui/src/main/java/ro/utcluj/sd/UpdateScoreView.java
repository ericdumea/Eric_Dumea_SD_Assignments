package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Tournament;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateScoreView {

    private String mail;
    private GameCore gc = new GameCore();

    @FXML public ComboBox cbTour;
    @FXML public Label label11;
    @FXML public Label label12;
    @FXML public Label label13;
    @FXML public Label label14;
    @FXML public Label label15;
    @FXML public Label label16;
    @FXML public Label label17;
    @FXML public Label label18;
    @FXML public Label label21;
    @FXML public Label label22;
    @FXML public Label label23;
    @FXML public Label label24;
    @FXML public Label label31;
    @FXML public Label label32;

    private TourService tourService;

    public void chooseTour(){

        String choice = (String) cbTour.getValue();
        label11.setText("");
        label12.setText("");
        label13.setText("");
        label14.setText("");
        label15.setText("");
        label16.setText("");
        label17.setText("");
        label18.setText("");
        label21.setText("");
        label22.setText("");
        label23.setText("");
        label24.setText("");
        label31.setText("");
        label32.setText("");

        ArrayList<Match> allMatches = new ArrayList<Match>();
        ArrayList<String> qfinals = new ArrayList<String>();
        ArrayList<String> sfinals = new ArrayList<String>();


        allMatches = tourService.getTour(choice);
        System.out.println(allMatches.toString());



        if(allMatches.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Tournament in progress or has not started yet").showAndWait();
        }

        else {
            for (Match m : allMatches) {
                if (m.getTourPlace() == 4) {
                    qfinals.add(m.getP1name() + " - " + m.getP1Score());
                    qfinals.add(m.getP2name() + " - " + m.getP2Score());
                } else if (m.getTourPlace() == 2) {
                    sfinals.add(m.getP1name() + " - " + m.getP1Score());
                    sfinals.add(m.getP2name() + " - " + m.getP2Score());
                } else if (m.getTourPlace() == 1) {
                    label31.setText(m.getP1name() + " - " + m.getP1Score());
                    label32.setText(m.getP2name() + " - " + m.getP2Score());
                }
            }
            if(!qfinals.isEmpty()) {
                label11.setText(qfinals.get(0));
                label12.setText(qfinals.get(1));
                label13.setText(qfinals.get(2));
                label14.setText(qfinals.get(3));
                label15.setText(qfinals.get(4));
                label16.setText(qfinals.get(5));
                label17.setText(qfinals.get(6));
                label18.setText(qfinals.get(7));
                if(!sfinals.isEmpty()) {
                    label21.setText(sfinals.get(0));
                    label22.setText(sfinals.get(1));
                    label23.setText(sfinals.get(2));
                    label24.setText(sfinals.get(3));
                }
            }
        }

    }



    public void setMail(String mail) {
        this.mail = mail;
    }

    public void init(){

        System.out.println("hatZ"+mail);

        tourService = new TourService(App.DAO_TYPE);
        ArrayList<String> x = tourService.populateCb(mail);
        label11.setText("");
        label12.setText("");
        label13.setText("");
        label14.setText("");
        label15.setText("");
        label16.setText("");
        label17.setText("");
        label18.setText("");
        label21.setText("");
        label22.setText("");
        label23.setText("");
        label24.setText("");
        label31.setText("");
        label32.setText("");

        cbTour.getItems().addAll(x);



    }


    public void updateScorep2(ActionEvent actionEvent) {
    }
}
