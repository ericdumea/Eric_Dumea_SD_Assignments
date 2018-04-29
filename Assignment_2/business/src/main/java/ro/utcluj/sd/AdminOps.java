package ro.utcluj.sd;

import javafx.scene.control.Alert;
import ro.utcluj.sd.dal.impl.*;
import ro.utcluj.sd.dal.impl.jdbc.MatchDAO;
import ro.utcluj.sd.dal.impl.jdbc.PlayerDAO;
import ro.utcluj.sd.dal.impl.jdbc.TournamentDAO;
import ro.utcluj.sd.model.Game;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;
import ro.utcluj.sd.model.Tournament;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminOps {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public DaoFactory.Type daoType;
    private Pattern pattern;
    private Matcher matcher;
    private ArrayList<Player> players;
    private PlayerDao playerDAO;
    private GameDao gameDAO;
    private MatchDao matchDAO;
    private TournamentDao tournamentDAO;

    AdminOps() {
        players = playerDAO.selAll();
    }

    AdminOps(DaoFactory.Type d) {
        daoType = d;


        playerDAO = DaoFactory.getInstance(daoType).getPlayerDao();
        gameDAO = DaoFactory.getInstance(daoType).getGameDao();
        matchDAO = DaoFactory.getInstance(daoType).getMatchDao();
        tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        players = playerDAO.selAll();

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addUser(String email, String pass, String name) {


        String encrypted = new String(Base64.getEncoder().encode(pass.getBytes()));

        System.out.println(pass + "   " + encrypted);

        Player p = new Player(email, encrypted, name);
        playerDAO.insert(p);
        players.clear();
        //players = playerDAO.selAll();

    }

    public void updateUser(int id, String email, String pass, String name, int balance) {


        String encrypted = new String(Base64.getEncoder().encode(pass.getBytes()));

        System.out.println(pass + "   " + encrypted);

        Player p = new Player(id, name, email, encrypted, false);

        p.setAccount(balance);

        playerDAO.update(p);
        players.clear();
        players = playerDAO.selAll();


    }

    public boolean updateTour(Tournament t) {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        if (t.getDate().before(sqlDate)) {
            new Alert(Alert.AlertType.ERROR, "Starting time before current time").showAndWait();
            return false;
        } else
            tournamentDAO.update(t);

        return true;
    }

    public void deleteTour(String name) {
        Tournament t = tournamentDAO.findByName(name);
        tournamentDAO.delete(t);
    }

    public void deleteUser(int id, String email, String pass, String name) {


        Player p = new Player(id, name, email, pass, false);

        playerDAO.delete(p);
        players.clear();
        players = playerDAO.selAll();


    }

    public Player getPlayerOnId(int id) {
        players = new ArrayList<Player>();

        players = playerDAO.selAll();


        for (Player p : players) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;

    }

    public ArrayList<String> populateCb() {
        players = playerDAO.selAll();
        ArrayList<String> ids = new ArrayList<String>();

        for (Player p : players) {
            Integer aux = p.getId();
            String auxs = aux.toString();
            ids.add(auxs);
        }

        return ids;
    }

    public boolean emailValidator(String email) {

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();


    }

    public int newTour(String uiName, String type) {

        Tournament t = tournamentDAO.findByName(uiName);

        if (t != null) {
            return -1;
        }

        int inserted = tournamentDAO.insert(uiName, type);

        if (inserted == -1) {
            return -1;
        }

        // t = new Tournament(inserted, uiName, "Not Started");

        return inserted;
    }

    public ArrayList<String> populateCbTour() {
        players = playerDAO.selAll();
        ArrayList<String> ids = new ArrayList<String>();

        for (Player p : players) {
            ids.add(p.getName());
        }

        return ids;
    }

    public ArrayList<String> populateTourCb() {
        ArrayList<Tournament> t;
        t = tournamentDAO.findAllTournaments();

        ArrayList<String> ids = new ArrayList<String>();

        for (Tournament tt : t) {
            ids.add(tt.getName());
        }

        return ids;
    }

    public void createMatches(ArrayList<Player> player, Tournament t) {
        Match m1;

        int insertedMatchId = 0;
        if (this.daoType == DaoFactory.Type.HIBERNATE) {

            for (int i = 0; i < 4; i++) {
                // System.out.println("Am bagat meciuri boss " + options.toString());
                //System.out.println("Pe astia ii am " + player.toString());
                m1 = new Match(player.get(0), player.get(1), t, 0, 0, 4);
                insertedMatchId = matchDAO.insert(m1);

                for (int j = 0; j < 5; j++) {
                    Game g = new Game(insertedMatchId);
                    gameDAO.insert(g);
                }
                player.remove(0);
                player.remove(0);
            }
        }

        if (this.daoType == DaoFactory.Type.JDBC) {
            for (int i = 0; i < 4; i++) {
                // System.out.println("Am bagat meciuri boss " + options.toString());
                //System.out.println("Pe astia ii am " + player.toString());
                m1 = new Match(player.get(0).getId(), player.get(1).getId(), t.getId(), 0, 0, 4);
                insertedMatchId = matchDAO.insert(m1);

                for (int j = 0; j < 5; j++) {
                    Game g = new Game(insertedMatchId);
                    gameDAO.insert(g);
                }
                player.remove(0);
                player.remove(0);
            }
        }

    }

    public boolean modifyCash(int cash, Player p) {
        if (p.getAccount() + cash < 0) {
            new Alert(Alert.AlertType.ERROR, "Not enough cash to withdraw the sum").showAndWait();
            return false;
        }

        p.setAccount(p.getAccount() + cash);
        playerDAO.update(p);
        return true;
    }

    public void addMatches(ArrayList<String> options, int id) {

        Match m1;
        int insertedMatchId;
        players = playerDAO.selAll();
        ArrayList<Player> player = new ArrayList<Player>();

        for (String s : options) {
            for (Player p : players) {
                if (p.getName().equals(s)) {
                    player.add(p);
                }
            }
        }

        if (this.daoType == DaoFactory.Type.HIBERNATE) {

            for (int i = 0; i < 4; i++) {
                // System.out.println("Am bagat meciuri boss " + options.toString());
                //System.out.println("Pe astia ii am " + player.toString());
                m1 = new Match(player.get(0), player.get(1), tournamentDAO.find(id), 0, 0, 4);
                insertedMatchId = matchDAO.insert(m1);

                for (int j = 0; j < 5; j++) {
                    Game g = new Game(insertedMatchId);
                    gameDAO.insert(g);
                }
                player.remove(0);
                player.remove(0);
            }
        }

        if (this.daoType == DaoFactory.Type.JDBC) {
            for (int i = 0; i < 4; i++) {
                // System.out.println("Am bagat meciuri boss " + options.toString());
                //System.out.println("Pe astia ii am " + player.toString());
                m1 = new Match(player.get(0).getId(), player.get(1).getId(), id, 0, 0, 4);
                insertedMatchId = matchDAO.insert(m1);

                for (int j = 0; j < 5; j++) {
                    Game g = new Game(insertedMatchId);
                    gameDAO.insert(g);
                }
                player.remove(0);
                player.remove(0);
            }
        }

    }

    public Tournament getTourByName(String choice) {


        Tournament t = tournamentDAO.findByName(choice);

        return t;

    }

    public void close() {
        tournamentDAO.closeConnection();
        playerDAO.closeConnection();
        matchDAO.closeConnection();
        gameDAO.closeConnection();
    }
}
