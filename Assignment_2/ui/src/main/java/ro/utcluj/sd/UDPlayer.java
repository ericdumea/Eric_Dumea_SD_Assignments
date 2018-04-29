package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.model.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UDPlayer implements Initializable{

    @FXML public ComboBox cbId;
    @FXML public Button btnUpd;
    @FXML public TextField emailTF;
    @FXML public TextField nameTF;
    @FXML public PasswordField passTF;
    @FXML public PasswordField confirmTF;
    @FXML public Button btnDel;
    @FXML public TextField tf_balance;
    private String name;
    private int id;
    private String email;
    private String pass;
    private String confirm;
    public DaoFactory.Type daoType = App.DAO_TYPE;

    public void updUser(ActionEvent actionEvent) {

        AdminOps validators = new AdminOps(daoType);

        if(nameTF.getText() != name || pass!=passTF.getText() || emailTF.getText()!=email ){

            if(validators.emailValidator(email) == false){
                new Alert(Alert.AlertType.ERROR, "Email not correct!").showAndWait();
                return;
            }

            if(pass.length()<6 && !(pass.length()==0) ){
                new Alert(Alert.AlertType.ERROR, "Password too short!").showAndWait();
                return;
            }

            if( ! passTF.getText().equals(confirmTF.getText())){
                new Alert(Alert.AlertType.ERROR, "Passwords don't match").showAndWait();
                passTF.setText("");
                confirmTF.setText("");
                return;
            }

            if(tf_balance.getText() == null){
                tf_balance.setText("0");
            }

            if( Integer.parseInt(tf_balance.getText()) < 0){

            }



            validators.updateUser(id,emailTF.getText(),passTF.getText(),nameTF.getText(), Integer.parseInt(tf_balance.getText()));

            emailTF.setText("");
            passTF.setText("");
            confirmTF.setText("");

        }

    }

    public void delUser(ActionEvent actionEvent) {

        AdminOps validators = new AdminOps(daoType);

        validators.deleteUser(id,emailTF.getText(),passTF.getText(),nameTF.getText());

        cbId.getItems().clear();
        cbId.getItems().addAll(validators.populateCb());

    }

    public void fillTF(ActionEvent actionEvent) {
        String choice = (String) cbId.getValue();
        if(choice!=null) {
            int idUi = Integer.parseInt(choice);

            AdminOps operations = new AdminOps(daoType);

            Player p = operations.getPlayerOnId(idUi);

            email = p.getEmail();
            name = p.getName();
            pass = p.getPassword();
            id = p.getId();
            confirm = p.getPassword();
            if(p.getAccount()!=null)
                tf_balance.setText(p.getAccount().toString());
            else
                tf_balance.setText("0");
            emailTF.setText(email);
            nameTF.setText(name);
        }



    }

    public void initialize(URL location, ResourceBundle resources) {

        AdminOps a = new AdminOps(daoType);

        ArrayList<String> options = new ArrayList<String>();

        options = a.populateCb();

        cbId.getItems().addAll(options);

    }
}
