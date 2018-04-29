package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.dal.impl.PlayerDao;
import ro.utcluj.sd.dal.impl.jdbc.PlayerDAO;

public class PlayerView {
    @FXML
    public Button matchBtn;
    @FXML
    public Button tourBtn;
    @FXML
    public Button updateBtn;
    @FXML
    public Button btn_enroll;
    @FXML
    public Button btn_account;
    @FXML
    public Label label_main;
    public DaoFactory.Type daoType = App.DAO_TYPE;
    private String mail;


    public void viewMatch(ActionEvent ae) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            stage.setScene(playerScene);
            stage.show();
            Node source = (Node) ae.getSource();
            Stage stage2 = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTour(ActionEvent ae) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TourView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            stage.setScene(playerScene);
            stage.show();
            Node source = (Node) ae.getSource();
            Stage stage2 = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMail(String mail) {
        this.mail = mail;
        PlayerDao p = DaoFactory.getInstance(daoType).getPlayerDao();
        label_main.setText("Hello, " + p.findByEmail(mail).getName());
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
            Node source = (Node) ae.getSource();
            Stage stage2 = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enroll(ActionEvent ae) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EnrollView.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            EnrollView e = fxmlLoader.getController();
            e.setUserMail(mail);
            e.init();

            stage.setScene(playerScene);
            System.out.println(mail);
            stage.show();
            Node source = (Node) ae.getSource();
            Stage stage2 = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accountManage(ActionEvent ae) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            Scene playerScene = new Scene(root1);
            AccountView acc = fxmlLoader.getController();
            acc.setMail(mail);
            acc.init();
            stage.setScene(playerScene);
            System.out.println(mail);
            stage.show();
            Node source = (Node) ae.getSource();
            Stage stage2 = (Stage) source.getScene().getWindow();
            //stage2.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
