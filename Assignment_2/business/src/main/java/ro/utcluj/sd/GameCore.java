package ro.utcluj.sd;

import ro.utcluj.sd.dal.impl.*;
import ro.utcluj.sd.model.Game;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;

public class GameCore {

    private Player p;
    private PlayerDao playerDAO;
    private MatchDao matchDAO ;
    private TournamentDao tourDAO ;
    private GameDao gameDAO;
    private Tournament t;
    private Match m;
    private int player;

    GameCore(String mail, DaoFactory.Type daoType, Tournament t) throws Exception {
        playerDAO = DaoFactory.getInstance(daoType).getPlayerDao();
        matchDAO = DaoFactory.getInstance(daoType).getMatchDao();
        tourDAO = DaoFactory.getInstance(daoType).getTourDao();
        gameDAO = DaoFactory.getInstance(daoType).getGameDao();

        p = playerDAO.findByEmail(mail);
        this.t = t;

        Tournament tt = null;

        System.out.println(p.getTournaments().toString());

        for(Tournament tx : p.getTournaments()){
            if(tx.getName().equals(t.getName())){
                tt = tx;
            }
        }

//        if(!p.getTournaments().contains(t)){
////            System.out.println("Not ok, nu exista player in turneu");
////            throw new Exception();
////        }

        if(tt == null){
            System.out.println("Not ok, nu exista player in turneu");
            throw new Exception();
        }

        Match m = null;

        for(Match mm : tt.getMatches()){
            if((mm.getPlayer1().equals(p) || mm.getPlayer2().equals(p) && (!isMatchOver(mm)))){
                m = mm;
                break;
            }
        }

        if(m == null){
            return;
        }

        if(m.getPlayer2().equals(p)){
            player = 2;
        } else player =1 ;


    }


    public void updateScore(){

        Game g = null;
        for(Game x : m.getGames()){
            if(!isGameOver(x)){
                g = x;
                break;
            }
        }
        if(g == null){
            System.out.println("Game null");
            return;
        }

        if(player == 1){
            g.setScore1(g.getScore1() + 1);
        } else
            g.setScore2(g.getScore2() + 1);


    }



    public void selectMatch(Player p){
        ArrayList<Match> matches = matchDAO.allPlayers(p.getId());
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

    public boolean isGameOver(Game g ){

        int p1 = g.getScore1();
        int p2 = g.getScore2();
        if(p1==11 && p2<9)
            return true;
        if(p2 == 11 && p1<9)
            return true;
        int delta = Math.abs((p1-p2));
        if(delta==2 && ( (p1>=11 && p2>=9)||(p1>=9 &&p2>=11))){
            return true;
        }
        else
            return false;
    }

}
