package ro.utcluj.sd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ro.utcluj.sd.dal.impl.DaoFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddTour implements Initializable {

    @FXML
    public Button btnAdd;
    @FXML public ComboBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;
    private ArrayList<String> choices;
    private ArrayList<String> options;
    private boolean choice = false;
    private int tourId = -1;
    private AdminOps a;


    public void setTourId(int tourId) {
        this.tourId = tourId;
    }


    public void refreshcb(ActionEvent actionEvent) {

        if(cb1.getValue()!=null){
            if(cb2.getValue()!=null){
                if(cb3.getValue()!=null){
                    if(cb4.getValue()!=null){
                        if(cb5.getValue()!=null){
                            if(cb6.getValue()!=null){
                                if(cb7.getValue()!= null){
                                    if(cb8.getValue()!=null){
                                        choice = true;
                                        options.remove(cb8.getValue());
                                        if(!choices.contains(cb8.getValue()))
                                            choices.add((String)cb8.getValue());
                                    } else{

                                    }
                                    options.remove(cb7.getValue());
                                    if(!choices.contains(cb7.getValue()))
                                        choices.add((String)cb7.getValue());
                                    cb8.getItems().addAll(options);
                                } else{

                                }
                                options.remove(cb6.getValue());
                                if(!choices.contains(cb6.getValue()))
                                    choices.add((String)cb6.getValue());
                                cb7.getItems().addAll(options);
                            } else{

                            }
                            options.remove(cb5.getValue());
                            if(!choices.contains(cb5.getValue()))
                                choices.add((String)cb5.getValue());
                            cb6.getItems().addAll(options);
                        } else{

                        }
                        options.remove(cb4.getValue());
                        if(!choices.contains(cb4.getValue()))
                            choices.add((String)cb4.getValue());
                        cb5.getItems().addAll(options);
                    } else{

                    }
                    options.remove(cb3.getValue());
                    if(!choices.contains(cb3.getValue()))
                        choices.add((String)cb3.getValue());
                    cb4.getItems().addAll(options);
                } else{

                }
                options.remove(cb2.getValue());
                if(!choices.contains(cb2.getValue()))
                    choices.add((String)cb2.getValue());
                cb3.getItems().addAll(options);
            } else{

            }
            options.remove(cb1.getValue());
            if(!choices.contains(cb1.getValue()))
                choices.add((String)cb1.getValue());
            cb2.getItems().addAll(options);
        } else{
            cb1.getItems().addAll(options);
        }

        System.out.println(choices.toString());

    }

    public void initialize(URL location, ResourceBundle resources) {
        a = new AdminOps(App.DAO_TYPE);


        choices = new ArrayList<String>();
        options = new ArrayList<String>();

        options = a.populateCbTour();

        cb1.getItems().addAll(options);

    }

    public void addMatches(ActionEvent actionEvent) {


        if(cb1.getValue()!=null && cb2.getValue()!=null && cb3.getValue()!=null && cb4.getValue()!=null && cb5.getValue()!=null &&
                cb6.getValue()!=null && cb7.getValue()!=null && cb8.getValue()!=null){
            a.addMatches(choices, tourId);
        }


    }
}
