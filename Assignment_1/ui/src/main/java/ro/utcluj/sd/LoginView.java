package ro.utcluj.sd;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginView {

    public Button loginButton ;
    public TextField emailTF;
    public PasswordField passTF;
    private Login login;

    public int loginButtonClicked(){
        System.out.println("Hello!!!!");

        String uipass, uiemail;

        login = new Login();

        uipass = passTF.getText().toString();
        uiemail = emailTF.getText().toString();

        int action;
        action = login.loginWrap(uiemail, uipass);

        if(action == 0){
            System.out.println("Nu e ok parola!");
            passTF.setText("");
            new Alert(Alert.AlertType.ERROR, "Password not correct, try again!").showAndWait();
        }
        if(action == -1){
            System.out.println("Username not existent");
            passTF.setText("");
            emailTF.setText("");
            new Alert(Alert.AlertType.ERROR, "Username not existent!").showAndWait();

        }
        if(action == 1){
            System.out.println("Player");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Player.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                Scene playerScene = new Scene(root1);
                PlayerView p = fxmlLoader.getController();
                p.setMail(uiemail);
                stage.setScene(playerScene);
                stage.show();
                App.getStage().close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(action == 2){
            System.out.println("Admin");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Admin.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                Scene adminScene = new Scene(root1);
                stage.setScene(adminScene);
                stage.show();
                App.getStage().close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return 0;

    }

}
