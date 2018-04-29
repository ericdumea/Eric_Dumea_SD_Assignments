package ro.utcluj.sd.dal.factory;

import ro.utcluj.sd.dal.impl.*;
import ro.utcluj.sd.dal.impl.jdbc.GameDAO;
import ro.utcluj.sd.dal.impl.jdbc.MatchDAO;
import ro.utcluj.sd.dal.impl.jdbc.PlayerDAO;
import ro.utcluj.sd.dal.impl.jdbc.TournamentDAO;

public class JdbcDaoFactory extends DaoFactory {

    public PlayerDao getPlayerDao() {
        return new PlayerDAO();
    }

    public GameDao getGameDao() {
        return new GameDAO();
    }

    public TournamentDao getTourDao() {
        return new TournamentDAO();
    }

    public MatchDao getMatchDao() {return new MatchDAO(); };
}
