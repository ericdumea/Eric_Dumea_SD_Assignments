package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ro.utcluj.sd.model.Tournament;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TourOps implements Initializable {
    public ComboBox cbId;
    public Button updBtn;
    public Button delBtn;
    public TextField nameTF;
    public TextField winTF;
    public TextField nameTFF;

    private String name;
    private String status;
    private int id, winnerid;

    public void cbAction(ActionEvent actionEvent) {
        String choice = (String) cbId.getValue();
        if(choice!=null) {

            AdminOps operations = new AdminOps();

            Tournament t = operations.getTourByName(choice);
            if(t!=null) {

                name = t.getName();
                id = t.getId();
                winnerid = t.getWinnerID();
                status = t.getStatus();

                nameTFF.setText(name);
                nameTF.setText(status);
                winTF.setText(Integer.toString(winnerid));
            }
        }
    }

    public void update(ActionEvent actionEvent) {
        AdminOps a = new AdminOps();

        if(nameTF.getText()!=null && nameTFF.getText()!=null && winTF.getText()!=null){
            a.updateTour(id, nameTFF.getText(), nameTF.getText(), winTF.getText());
            nameTF.setText("");
            nameTFF.setText("");
            winTF.setText("");

        }

    }

    public void delete(ActionEvent actionEvent) {
        AdminOps a = new AdminOps();
        if(nameTF.getText()!=null && nameTFF.getText()!=null && winTF.getText()!=null) {
            a.deleteTour(id, nameTFF.getText(), nameTF.getText(), winTF.getText());
        }

        cbId.getItems().clear();
        cbId.getItems().addAll(a.populateTourCb());
    }



    public void initialize(URL location, ResourceBundle resources) {
        AdminOps a = new AdminOps();

        ArrayList<String> options = new ArrayList<String>();

        options = a.populateTourCb();

        cbId.getItems().addAll(options);
    }
}
