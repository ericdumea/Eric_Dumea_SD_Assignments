package ro.utcluj.sd.dal.impl;

import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;

public interface TournamentDao extends Dao<Tournament> {

    void closeConnection();
    Tournament findByName (String tName);
    ArrayList<Tournament> findAllTournaments();
    ArrayList<Match> selAllById(int tourid);
    int insert(String name);
    int insert(String name, String type);

}
