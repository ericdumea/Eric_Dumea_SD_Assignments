package ro.utcluj.sd.dal.impl;

import javafx.collections.ObservableList;
import ro.utcluj.sd.model.Match;

import java.util.ArrayList;

public interface MatchDao extends Dao<Match> {

    int insert(Match m);

    ArrayList<Match> allPlayers(int id);
    ObservableList<Match> selAll();
    ArrayList<Match> findAllByTour(int t);

    void closeConnection();


}
