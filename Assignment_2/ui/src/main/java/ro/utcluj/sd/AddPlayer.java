package ro.utcluj.sd;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ro.utcluj.sd.dal.impl.DaoFactory;

public class AddPlayer {

    public TextField emailTF;
    public TextField passTF;
    public TextField confirmTF;
    public TextField nameTF;
    public DaoFactory.Type daoType;


    public void addUser(){

        String name, email, pass, confirmed;
        AdminOps validators = new AdminOps(daoType);

        email = emailTF.getText();
        name = nameTF.getText();
        pass = passTF.getText();
        confirmed = confirmTF.getText();


        if(validators.emailValidator(email) == false){
            new Alert(Alert.AlertType.ERROR, "Email not correct!").showAndWait();
            return;
        }

        if(pass.length()<6 && !(pass.length()==0) ){
            new Alert(Alert.AlertType.ERROR, "Password too short!").showAndWait();
            return;
        }

        if( ! pass.equals(confirmed)){
            new Alert(Alert.AlertType.ERROR, "Passwords don't match").showAndWait();
            passTF.setText("");
            confirmTF.setText("");
            return;
        }

        if(email.length()!=0 && name.length()!=0 && pass.length()!=0){

            //aici e ok
            validators.addUser(email,pass,name);
            new Alert(Alert.AlertType.CONFIRMATION, "User Inserted").showAndWait();
            return;

        } else {
            new Alert(Alert.AlertType.ERROR, "One or more fields left blank").showAndWait();
        }

        validators.close();
    }
}
