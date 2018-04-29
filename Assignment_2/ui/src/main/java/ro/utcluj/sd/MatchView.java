package ro.utcluj.sd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.dal.impl.MatchDao;
import ro.utcluj.sd.dal.impl.jdbc.MatchDAO;
import ro.utcluj.sd.model.Match;

import java.net.URL;
import java.util.ResourceBundle;

public class MatchView implements Initializable{

    @FXML public TableView<Match> table;
    @FXML public TableColumn<Match, Integer> idColumn;
    @FXML public TableColumn<Match, String> p1Column;
    @FXML public TableColumn<Match, String> p2Column;
    @FXML public TableColumn<Match, String> tourColumn;
    @FXML public TableColumn<Match, Integer> p1Score;
    @FXML public TableColumn<Match, Integer> p2Score;

    private MatchDao matchDAO;
    public DaoFactory.Type daoType = App.DAO_TYPE;


    public void buildTable(){

        matchDAO = DaoFactory.getInstance(daoType).getMatchDao();

        //idColumn = new TableColumn<Match, Integer>("MatchID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));

        //p1Column = new TableColumn<Match, String>("Player One");
        p1Column.setCellValueFactory(new PropertyValueFactory<Match, String>("p1name"));

        //p2Column = new TableColumn<Match, String>("Player Two");
        p2Column.setCellValueFactory(new PropertyValueFactory<Match, String>("p2name"));
        p1Score.setCellValueFactory(new PropertyValueFactory<Match, Integer>("p1Score"));
        p2Score.setCellValueFactory(new PropertyValueFactory<Match, Integer>("p2Score"));

       // tourColumn = new TableColumn<Match, String>("Tournament");
        tourColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("tourname"));

        table.getItems().setAll(matchDAO.selAll());
//        table.setItems(MatchDAO.selAll());
//        table.getColumns().addAll(idColumn, p1Column);
        System.out.println(table.toString()+ "hello");
        table.refresh();
    }


    public void initialize(URL location, ResourceBundle resources) {
//        idColumn = new TableColumn<Match, Integer>("MatchID");
//        idColumn.setMinWidth(50);
//        idColumn.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));
//
//        p1Column = new TableColumn<Match, String>("Player One");
//        p1Column.setMinWidth(200);
//        p1Column.setCellValueFactory(new PropertyValueFactory<Match, String>("p1name"));
//
//        p2Column = new TableColumn<Match, String>("Player Two");
//        p2Column.setMinWidth(200);
//        p2Column.setCellValueFactory(new PropertyValueFactory<Match, String>("p2name"));
//
//        tourColumn = new TableColumn<Match, String>("Tournament");
//        tourColumn.setMinWidth(200);
//        tourColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("tourname"));
//
//        table.getItems().setAll(MatchDAO.selAll());

        matchDAO = DaoFactory.getInstance(daoType).getMatchDao();

        idColumn.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));

        //p1Column = new TableColumn<Match, String>("Player One");
        p1Column.setCellValueFactory(new PropertyValueFactory<Match, String>("p1name"));

        //p2Column = new TableColumn<Match, String>("Player Two");
        p2Column.setCellValueFactory(new PropertyValueFactory<Match, String>("p2name"));
        p1Score.setCellValueFactory(new PropertyValueFactory<Match, Integer>("p1Score"));
        p2Score.setCellValueFactory(new PropertyValueFactory<Match, Integer>("p2Score"));

        // tourColumn = new TableColumn<Match, String>("Tournament");
        tourColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("tourname"));

        table.getItems().setAll(matchDAO.selAll());
//        table.setItems(MatchDAO.selAll());
//        table.getColumns().addAll(idColumn, p1Column);
        System.out.println(table.toString()+ " hello Initial");
    }
}
