package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Tournament;

import javax.swing.*;
import java.util.ArrayList;

public class AdminView {

    @FXML public Button matchBtn;
    @FXML public Button tourBtn;
    @FXML public TextField tourNameTF;
    @FXML public ComboBox cb_type;
    private int id = -1;
    public DaoFactory.Type daoType;

    public void init(){
        ArrayList<String> x = new ArrayList<String>();
        x.add(Tournament.FREE);
        x.add(Tournament.PAID);

        cb_type.getItems().addAll(x);
    }

    public int getId() {
        return id;
    }

    public void viewMatch(ActionEvent ae){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            stage.setScene(playerScene);
            MatchView m = fxmlLoader.getController();
            m.daoType = daoType;
            stage.show();
            Node source = (Node)  ae.getSource();
            Stage stage2  = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTour(ActionEvent ae){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TourView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            stage.setScene(playerScene);
            stage.show();
            Node source = (Node)  ae.getSource();
            Stage stage2  = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addPlayer(ActionEvent ae){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddPlayer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            AddPlayer a = fxmlLoader.getController();
            a.daoType = daoType;
            stage.setScene(playerScene);
            stage.show();
            Node source = (Node)  ae.getSource();
            Stage stage2  = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(ActionEvent ae){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UDPlayer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            UDPlayer u = fxmlLoader.getController();
            u.daoType = daoType;
            stage.setScene(playerScene);
            stage.show();
            Node source = (Node)  ae.getSource();
            Stage stage2  = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addTournament(ActionEvent ae){
        String tourName = tourNameTF.getText();
        AdminOps a = new AdminOps(daoType);


        if(cb_type.getValue() == null){
            new Alert(Alert.AlertType.WARNING, "Select Tournament type").showAndWait();
        }
        else {
            if (tourName != null) {
                if(cb_type.getValue().equals(Tournament.FREE)) {
                    this.id = a.newTour(tourName,Tournament.FREE);
                    if (this.id != -1) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddTour.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            Scene playerScene = new Scene(root1);
                            stage.setScene(playerScene);
                            stage.show();
                            AddTour control = fxmlLoader.getController();
                            control.setTourId(this.id);
                            Node source = (Node) ae.getSource();
                            Stage stage2 = (Stage) source.getScene().getWindow();
                            //stage2.close();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Tournament already exists").showAndWait();
                    }
                }
                else if(cb_type.getValue().equals(Tournament.PAID)){
                    this.id = a.newTour(tourName,Tournament.PAID);
                    new Alert(Alert.AlertType.CONFIRMATION, "Tournament inserted").showAndWait();
                    tourNameTF.setText("");
                }

            } else {
                new Alert(Alert.AlertType.WARNING, "Insert a valid tournament name").showAndWait();
            }
        }

    }
    public void updateTournament(ActionEvent ae){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TourOps.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            stage.setScene(playerScene);
            TourOps t = fxmlLoader.getController();
            t.daoType = daoType;
            stage.show();
            Node source = (Node)  ae.getSource();
            Stage stage2  = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
