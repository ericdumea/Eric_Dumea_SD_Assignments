package ro.utcluj.sd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ro.utcluj.sd.dal.impl.Dao;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.model.Tournament;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

public class TourOps implements Initializable {
    @FXML
    public ComboBox cbId;
    @FXML
    public Button updBtn;
    @FXML
    public Button delBtn;
    @FXML
    public TextField tf_fee;
    @FXML
    public TextField winTF;
    @FXML
    public TextField nameTFF;
    @FXML
    public ComboBox cb_type;
    @FXML
    public ComboBox cb_status;
    @FXML
    public TextField date_pick;
    public DaoFactory.Type daoType = App.DAO_TYPE;
    Tournament t;
    ArrayList<String> cb_type_choices = new ArrayList<String>();
    ArrayList<String> cb_status_choices = new ArrayList<String>();
    private String name;
    private String status;
    private int fee;
    private String type;
    private int id, winnerid;
    private AdminOps a;

    public void cbAction(ActionEvent actionEvent) {
        String choice = (String) cbId.getValue();
        if (choice != null) {


            t = a.getTourByName(choice);
            if (t != null) {

                name = t.getName();
                id = t.getId();
                winnerid = t.getWinnerID();
                status = t.getStatus();

                nameTFF.setText(name);
                cb_status.setValue(t.getStatus());
                cb_type.setValue(t.getType());
                tf_fee.setText(t.getFee().toString());
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                String mysqlDateString = formatter.format(t.getDate());
                date_pick.setText(mysqlDateString);
                winTF.setText(Integer.toString(winnerid));
            }
        }
    }

    public void update(ActionEvent actionEvent) {

        if (nameTFF.getText() != null) {
            t.setName(nameTFF.getText());
            t.setType((String) cb_type.getValue());
            if (t.getType().equals(Tournament.FREE))
                t.setFee(0);
            else
                t.setFee(Integer.parseInt(tf_fee.getText()));
            t.setStatus((String) cb_status.getValue());
            if(!winTF.getText().equals(""))
                t.setWinnerID(Integer.parseInt(winTF.getText()));
            else
                t.setWinnerID(0);
            t.setDate(Date.valueOf(date_pick.getText()));

            if (a.updateTour(t) == true) {
                new Alert(Alert.AlertType.CONFIRMATION, "Tournament updated!").showAndWait();
            }

        }

        cbId.getItems().clear();
        cbId.getItems().addAll(a.populateTourCb());
    }

    public void delete(ActionEvent actionEvent) {

        a.deleteTour(nameTFF.getText());

        cbId.getItems().clear();
        cbId.getItems().addAll(a.populateTourCb());
    }


    public void initialize(URL location, ResourceBundle resources) {
        a = new AdminOps(App.DAO_TYPE);

        ArrayList<String> options = new ArrayList<String>();

        options = a.populateTourCb();

        cb_type_choices.add(Tournament.FREE);
        cb_type_choices.add(Tournament.PAID);
        cb_type.getItems().addAll(cb_type_choices);

        cb_status_choices.add(Tournament.ENDED);
        cb_status_choices.add(Tournament.ENROLL);
        cb_status_choices.add(Tournament.IN_PROGRESS);
        cb_status_choices.add(Tournament.NOT_STARTED);

        cb_status.getItems().addAll(cb_status_choices);

        cbId.getItems().addAll(options);
    }
}
