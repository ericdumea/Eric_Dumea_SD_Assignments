package ro.utcluj.sd;

import ro.utcluj.sd.dal.TournamentDAO;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;

public class TourService {

    public static ArrayList<Match> getTour(String uiInput){

        Tournament t = TournamentDAO.findByName(uiInput);

        ArrayList<Match> matches = TournamentDAO.selAllById(t.getId());

        return matches;

    }

    public static ArrayList<String> populateCb(){
        ArrayList<String> toReturn = new ArrayList<String>();
        ArrayList<Tournament> tournaments;
        tournaments = TournamentDAO.findAllTournaments();

        for(Tournament t : tournaments){
            toReturn.add(t.getName());
        }

        return toReturn;
    }
}
