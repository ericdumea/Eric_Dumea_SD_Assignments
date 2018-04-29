package ro.utcluj.sd.dal.factory;

import ro.utcluj.sd.dal.impl.*;
import ro.utcluj.sd.dal.impl.hibernate.HibernateGameDAO;
import ro.utcluj.sd.dal.impl.hibernate.HibernateMatchDAO;
import ro.utcluj.sd.dal.impl.hibernate.HibernatePlayerDAO;
import ro.utcluj.sd.dal.impl.hibernate.HibernateTournamentDAO;

public class HibernateDaoFactory extends DaoFactory {


    public PlayerDao getPlayerDao() {
        return new HibernatePlayerDAO();
    }

    public GameDao getGameDao() {
        return new HibernateGameDAO();
    }

    public TournamentDao getTourDao() {
        return new HibernateTournamentDAO();
    }

    public MatchDao getMatchDao() {return new HibernateMatchDAO();};
}
