package ro.utcluj.sd;

import ro.utcluj.sd.dal.MatchDAO;
import ro.utcluj.sd.dal.PlayerDAO;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;

import java.util.ArrayList;

public class GameCore {

    private Player p;


    public void initialize(String mail) {

        p = PlayerDAO.findByEmail(mail);

    }

    public void selectMatch(Player p){
        ArrayList<Match> matches = MatchDAO.allPlayers(p.getId());
        Match m = null;

        for(Match x : matches){
            if(!isMatchOver(x)){
                m = x;
                break;
            }
        }
        if(m!=null){
            m.getGames();
            if(m.getPlayerOneID()==p.getId()){

            } else if(m.getPlayerTwoID()==p.getId()){

            }

        }

    }

    public boolean isMatchOver(Match m){

        int p1 = m.getP1Score();
        int p2 = m.getP2Score();
        int delta = Math.abs((p1-p2));
        if(delta==2 && ( (p1>=3 && p2>=5)||(p1>=5 &&p2>=3))){
            return true;
        }
        else
            return false;
    }

}
