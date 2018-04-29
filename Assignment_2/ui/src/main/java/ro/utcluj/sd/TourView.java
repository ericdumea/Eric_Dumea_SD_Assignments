package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Tournament;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TourView implements Initializable {

    @FXML
    public ComboBox cbTour;
    @FXML
    public Label label11;
    @FXML
    public Label label12;
    @FXML
    public Label label13;
    @FXML
    public Label label14;
    @FXML
    public Label label15;
    @FXML
    public Label label16;
    @FXML
    public Label label17;
    @FXML
    public Label label18;
    @FXML
    public Label label21;
    @FXML
    public Label label22;
    @FXML
    public Label label23;
    @FXML
    public Label label24;
    @FXML
    public Label label31;
    @FXML
    public Label label32;
    @FXML
    public Button btn_search;
    @FXML
    public TextField tf_search;
    @FXML
    public ComboBox<String> cb_type;
    @FXML public ComboBox<String> cb_status;

    ArrayList<String> x, cb_type_choices,cb_status_choices;
    private TourService tourService;


    public void search() {
        if(cb_status.getValue() == null) {
            if (cb_type.getValue() == null) {
                x = tourService.populateCb();
                if (tf_search.getText().equals("")) {
                    new Alert(Alert.AlertType.WARNING, "Insert keyword to search for").showAndWait();
                    cbTour.getItems().clear();
                    cbTour.getItems().addAll(x);
                } else {
                    ArrayList<String> y = new ArrayList<String>();
                    for (String xx : x) {
                        if (xx.contains(tf_search.getText())) {
                            y.add(xx);
                        }
                    }

                    if (y.size() == 0) {
                        new Alert(Alert.AlertType.WARNING, "Could not find " + tf_search.getText()).showAndWait();
                        y = tourService.populateCb();
                    } else {
                        cbTour.getItems().clear();
                        cbTour.getItems().addAll(y);
                    }
                }
            } else {
                x = tourService.populateCbByType(cb_type.getValue());
                if (tf_search.getText().equals("")) {
                    // new Alert(Alert.AlertType.WARNING, "Insert keyword to search for").showAndWait();
                    cbTour.getItems().clear();
                    cbTour.getItems().addAll(x);
                } else {
                    ArrayList<String> y = new ArrayList<String>();
                    for (String xx : x) {
                        if (xx.contains(tf_search.getText())) {
                            y.add(xx);
                        }
                    }

                    if (y.size() == 0) {
                        new Alert(Alert.AlertType.WARNING, "Could not find " + tf_search.getText()).showAndWait();
                        y = tourService.populateCb();
                    } else {
                        cbTour.getItems().clear();
                        cbTour.getItems().addAll(y);
                    }
                }
            }
        } else {
            if (cb_type.getValue() == null) {
                x = tourService.populateCbByStatus(cb_status.getValue());
                if (tf_search.getText().equals("")) {
                    new Alert(Alert.AlertType.WARNING, "Insert keyword to search for").showAndWait();
                    cbTour.getItems().clear();
                    cbTour.getItems().addAll(x);
                } else {
                    ArrayList<String> y = new ArrayList<String>();
                    for (String xx : x) {
                        if (xx.contains(tf_search.getText())) {
                            y.add(xx);
                        }
                    }

                    if (y.size() == 0) {
                        new Alert(Alert.AlertType.WARNING, "Could not find " + tf_search.getText()).showAndWait();
                        y = tourService.populateCb();
                    } else {
                        cbTour.getItems().clear();
                        cbTour.getItems().addAll(y);
                    }
                }
            } else {
                x = tourService.populateCbByStatusType(cb_status.getValue(),cb_type.getValue());
                if (tf_search.getText().equals("")) {
                    // new Alert(Alert.AlertType.WARNING, "Insert keyword to search for").showAndWait();
                    cbTour.getItems().clear();
                    cbTour.getItems().addAll(x);
                } else {
                    ArrayList<String> y = new ArrayList<String>();
                    for (String xx : x) {
                        if (xx.contains(tf_search.getText())) {
                            y.add(xx);
                        }
                    }

                    if (y.size() == 0) {
                        new Alert(Alert.AlertType.WARNING, "Could not find " + tf_search.getText()).showAndWait();
                        y = tourService.populateCb();
                    } else {
                        cbTour.getItems().clear();
                        cbTour.getItems().addAll(y);
                    }
                }
            }
        }
    }

    public void chooseTour() {

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

        if (allMatches.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Tournament in progress or has not started yet").showAndWait();
        } else {
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
            if (!qfinals.isEmpty()) {
                label11.setText(qfinals.get(0));
                label12.setText(qfinals.get(1));
                label13.setText(qfinals.get(2));
                label14.setText(qfinals.get(3));
                label15.setText(qfinals.get(4));
                label16.setText(qfinals.get(5));
                label17.setText(qfinals.get(6));
                label18.setText(qfinals.get(7));
                if (!sfinals.isEmpty()) {
                    label21.setText(sfinals.get(0));
                    label22.setText(sfinals.get(1));
                    label23.setText(sfinals.get(2));
                    label24.setText(sfinals.get(3));
                }
            }
        }

    }


    public void initialize(URL location, ResourceBundle resources) {
        tourService = new TourService(App.DAO_TYPE);
        x = tourService.populateCb();

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

        cb_type_choices = new ArrayList<String>();
        cb_status_choices = new ArrayList<String>();


        cb_type_choices.add(Tournament.FREE);
        cb_type_choices.add(Tournament.PAID);
        cb_type.getItems().addAll(cb_type_choices);


        cb_status_choices.add(Tournament.ENDED);
        cb_status_choices.add(Tournament.ENROLL);
        cb_status_choices.add(Tournament.IN_PROGRESS);
        cb_status_choices.add(Tournament.NOT_STARTED);

        cb_status.getItems().addAll(cb_status_choices);

        cbTour.getItems().addAll(x);
    }
}
