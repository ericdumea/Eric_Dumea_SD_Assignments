package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class AdminView {

    public Button matchBtn;
    public Button tourBtn;
    @FXML public TextField tourNameTF;
    private int id = -1;

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
        if(tourName!=null){
            this.id = AdminOps.newTour(tourName);
            if( this.id != -1) {

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
            }
             else{
               new Alert(Alert.AlertType.WARNING, "Tournament already exists").showAndWait();
           }

        } else{
            new Alert(Alert.AlertType.WARNING, "Insert a valid tournament name").showAndWait();
        }

    }
    public void updateTournament(ActionEvent ae){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TourOps.fxml"));
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
}
