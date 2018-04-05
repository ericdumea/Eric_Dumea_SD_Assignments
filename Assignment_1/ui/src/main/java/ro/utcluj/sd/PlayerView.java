package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayerView {
    public Button matchBtn;
    public Button tourBtn;

    private String mail;



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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void updateScore(ActionEvent ae) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UpdateScore.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            UpdateScoreView u = fxmlLoader.getController();
            u.setMail(mail);
            u.init();
            stage.setScene(playerScene);
            System.out.println(mail);
            stage.show();
            Node source = (Node)  ae.getSource();
            Stage stage2  = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
