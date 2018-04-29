package ro.utcluj.sd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.dal.impl.MatchDao;
import ro.utcluj.sd.dal.impl.jdbc.MatchDAO;
import ro.utcluj.sd.model.Match;

public class MatchService {

    public ObservableList<Match> matches = FXCollections.observableArrayList();



    public static TableView<Match> getMatches(){

        TableView<Match> toReturn = new TableView<Match>();
        MatchDao matchDAO = DaoFactory.getInstance(DaoFactory.Type.JDBC).getMatchDao();

        TableColumn<Match, Integer> idColumn = new TableColumn<Match, Integer>("MatchID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));

        TableColumn<Match, String> p1Column = new TableColumn<Match, String>("Player One");
        p1Column.setMinWidth(200);
        p1Column.setCellValueFactory(new PropertyValueFactory<Match, String>("p1Name"));

//        TableColumn<Match, Integer> p2Column = new TableColumn<Match, Integer>("Player Two");
//        p2Column.setMinWidth(200);
//        p2Column.setCellValueFactory(new PropertyValueFactory<Match, Integer>("p2Name"));
//
//        TableColumn<Match, Integer> tourColumn = new TableColumn<Match, Integer>("Tournament");
//        tourColumn.setMinWidth(200);
//        tourColumn.setCellValueFactory(new PropertyValueFactory<Match, Integer>("tourName"));


        toReturn.setItems(matchDAO.selAll());
        toReturn.getColumns().addAll(idColumn, p1Column);
        System.out.println(toReturn.toString());

        return toReturn;


    }
}
