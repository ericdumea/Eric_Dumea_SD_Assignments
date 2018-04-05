package ro.utcluj.sd;

import ro.utcluj.sd.dal.GameDAO;
import ro.utcluj.sd.dal.MatchDAO;
import ro.utcluj.sd.dal.PlayerDAO;
import ro.utcluj.sd.dal.TournamentDAO;
import ro.utcluj.sd.model.Game;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminOps {

    private Pattern pattern;
    private Matcher matcher;
    private ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    AdminOps(){
        players = PlayerDAO.selAll();
    }

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public void addUser(String email, String pass, String name){


        String encrypted = new String(Base64.getEncoder().encode(pass.getBytes()));

        System.out.println(pass+ "   " + encrypted);

        Player p = new Player(email, encrypted, name);

        PlayerDAO.insert(p);
        players.clear();
        players = PlayerDAO.selAll();

    }

    public void updateUser(int id,String email, String pass, String name){

        String encrypted = new String(Base64.getEncoder().encode(pass.getBytes()));

        System.out.println(pass+ "   " + encrypted);

        Player p = new Player(id, name, email, encrypted,false);

        PlayerDAO.update(p);
        players.clear();
        players = PlayerDAO.selAll();

    }

    public void updateTour(int id, String name, String status, String winnerID){
        Tournament t = new Tournament(id, name, status, Integer.parseInt(winnerID));
        TournamentDAO.update(t);
    }

    public void deleteTour(int id, String name, String status, String winnerID){
        Tournament t = new Tournament(id, name, status, Integer.parseInt(winnerID));
        TournamentDAO.delete(t);
    }

    public void deleteUser(int id,String email, String pass, String name){


        Player p = new Player(id, name, email, pass,false);

        PlayerDAO.delete(p);
        players.clear();
        players = PlayerDAO.selAll();


    }

    public Player getPlayerOnId(int id){
        players = new ArrayList<Player>();

        players = PlayerDAO.selAll();


        for(Player p : players){
            if(p.getId() == id){
                return p;
            }
        }

        return null;

    }

    public ArrayList<String> populateCb(){
        players = PlayerDAO.selAll();
        ArrayList<String> ids = new ArrayList<String>();

        for(Player p : players){
            Integer aux = p.getId();
            String auxs = aux.toString();
            ids.add(auxs);
        }

        return ids;
    }

    public boolean emailValidator(String email){

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();


    }

    public static int newTour(String uiName){

        Tournament t = TournamentDAO.findByName(uiName);

        if(t!=null){
            return -1;
        }

        int inserted = TournamentDAO.insert(uiName);

        if(inserted == -1){
            return -1;        }

        t = new Tournament(inserted, uiName, "Not Started");

        return inserted;
    }

    public ArrayList<String> populateCbTour(){
        players = PlayerDAO.selAll();
        ArrayList<String> ids = new ArrayList<String>();

        for(Player p : players){
            ids.add(p.getName());
        }

        return ids;
    }

    public ArrayList<String> populateTourCb(){
        ArrayList<Tournament> t = new ArrayList<Tournament>();
        t = TournamentDAO.findAllTournaments();

        ArrayList<String> ids = new ArrayList<String>();

        for(Tournament tt : t){
            ids.add(tt.getName());
        }

        return ids;
    }

    public void addMatches(ArrayList<String> options, int id) {

        Match m1;
        int insertedMatchId;
        players = PlayerDAO.selAll();
        ArrayList<Player> player = new ArrayList<Player>();

        for(String s :options){
            for(Player p : players){
                if(p.getName().equals(s)){
                    player.add(p);
                }
            }
        }

        //System.out.println(player.toString());

        for(int i=0; i<4;i++){
           // System.out.println("Am bagat meciuri boss " + options.toString());
            //System.out.println("Pe astia ii am " + player.toString());
            m1 = new Match(player.get(0).getId(),player.get(1).getId(),id,0,0,4);
            insertedMatchId= MatchDAO.insertMatch(m1);
            for(int j=0;j<5;j++){
                Game g = new Game(insertedMatchId);
                GameDAO.insert(g);
            }
            player.remove(0);
            player.remove(0);
        }

    }

    public Tournament getTourByName(String choice) {


        Tournament t = TournamentDAO.findByName(choice);

        return t;

    }
}
