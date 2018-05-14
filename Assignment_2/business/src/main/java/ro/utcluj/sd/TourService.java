package ro.utcluj.sd;

import javafx.scene.control.Alert;
import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.dal.impl.MatchDao;
import ro.utcluj.sd.dal.impl.PlayerDao;
import ro.utcluj.sd.dal.impl.TournamentDao;
import ro.utcluj.sd.dal.impl.jdbc.TournamentDAO;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class TourService {
    public DaoFactory.Type daoType;

    public TourService(DaoFactory.Type daoType) {
        this.daoType = daoType;
    }

    public boolean enroll(String userMail, String tName) {

        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        PlayerDao playerDAO = DaoFactory.getInstance(daoType).getPlayerDao();


        Player p = playerDAO.findByEmail(userMail);
        Tournament t = tournamentDAO.findByName(tName);

        if (t.getFee() > p.getAccount()) {
            new Alert(Alert.AlertType.ERROR, "Fee is greater than your account sum!").showAndWait();
            return false;
        }
        if (t.getEnrolledPlayers().contains(p)) {
            new Alert(Alert.AlertType.ERROR, "You are already registered in this tournament").showAndWait();
            return false;
        }
        if (t.getEnrolledPlayers().size() > 8) {
            new Alert(Alert.AlertType.ERROR, "This tournament is already full").showAndWait();
            return false;
        }
        if (!t.getType().equals(Tournament.PAID)) {
            new Alert(Alert.AlertType.ERROR, "This tournament requires enrollment by admins").showAndWait();
            return false;
        }

        List<Player> auxPlayers = t.getEnrolledPlayers();
        p.setAccount(p.getAccount()-t.getFee());
        playerDAO.update(p);
        auxPlayers.add(p);
        tournamentDAO.update(t);

        if(t.getEnrolledPlayers().size()==8){
            t.setStatus(Tournament.ENROLL);
            AdminOps a = new AdminOps(daoType);
            a.createMatches(new ArrayList<Player>(t.getEnrolledPlayers()),t);

            return true;

        }
        //tournamentDAO.closeConnection();
        return true;
    }

    public ArrayList<Match> getTour(String uiInput) {

        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        MatchDao matchDao = DaoFactory.getInstance(daoType).getMatchDao();
        Tournament t = tournamentDAO.findByName(uiInput);


        ArrayList<Match> matches = matchDao.findAllByTour(t.getId());

        return matches;

    }

    public Tournament getTournamentByName(String name){
        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        return tournamentDAO.findByName(name);
    }

    public ArrayList<String> populateCb(String mail) {
        ArrayList<String> toReturn = new ArrayList<String>();
        ArrayList<Tournament> tournaments;
        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        tournaments = tournamentDAO.findAllTournaments();

        for (Tournament t : tournaments) {
            List<Match> match = t.getMatches();
            for (Match m : match) {
                if (m.getPlayer1().getEmail().equals(mail) || m.getPlayer2().getEmail().equals(mail)) {
                    toReturn.add(t.getName());
                }
            }

        }

        return toReturn;
    }

    public String fillEnrollLabel(String name) {

        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        Tournament t = tournamentDAO.findByName(name);

        if (!t.getType().equals(Tournament.PAID)) {
            return "You can't enroll in this tournament. Please select another one.";
        }

        return (t.getName() + " " + t.getDate().toString() + "   Fee:" + t.getFee());

    }

    public ArrayList<String> populateCb() {
        ArrayList<String> toReturn = new ArrayList<String>();
        ArrayList<Tournament> tournaments;
        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        tournaments = tournamentDAO.findAllTournaments();

        for (Tournament t : tournaments) {
            toReturn.add(t.getName());
        }

        return toReturn;
    }

    public ArrayList<String> populateCbByType(String type) {
        ArrayList<String> toReturn = new ArrayList<String>();
        ArrayList<Tournament> tournaments;
        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        tournaments = tournamentDAO.findAllTournaments();

        for (Tournament t : tournaments) {
            if(t.getType().equals(type))
                toReturn.add(t.getName());
        }

        return toReturn;
    }

    public ArrayList<String> populateCbByStatus(String type) {
        ArrayList<String> toReturn = new ArrayList<String>();
        ArrayList<Tournament> tournaments;
        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        tournaments = tournamentDAO.findAllTournaments();

        for (Tournament t : tournaments) {
            if(t.getStatus().equals(type))
                toReturn.add(t.getName());
        }

        return toReturn;
    }

    public ArrayList<String> populateCbByStatusType(String status, String type) {
        ArrayList<String> toReturn = new ArrayList<String>();
        ArrayList<Tournament> tournaments;
        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        tournaments = tournamentDAO.findAllTournaments();

        for (Tournament t : tournaments) {
            if(t.getStatus().equals(status) && t.getType().equals(type))
                toReturn.add(t.getName());
        }

        return toReturn;
    }


    public ArrayList<String> populateEnrollCb() {
        ArrayList<String> toReturn = new ArrayList<String>();
        ArrayList<Tournament> tournaments;
        TournamentDao tournamentDAO = DaoFactory.getInstance(daoType).getTourDao();
        tournaments = tournamentDAO.findAllTournaments();

        for (Tournament t : tournaments) {
            if (t.getStatus().equals(Tournament.NOT_STARTED) || t.getStatus().equals(Tournament.ENROLL))
                toReturn.add(t.getName());
        }

        return toReturn;
    }

}
