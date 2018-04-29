package ro.utcluj.sd.dal.impl;

import ro.utcluj.sd.dal.factory.HibernateDaoFactory;
import ro.utcluj.sd.dal.factory.JdbcDaoFactory;

public abstract class DaoFactory {

    public enum Type {
        HIBERNATE,
        JDBC;
    }


    protected DaoFactory(){

    }

    public static DaoFactory getInstance(Type factoryType) {
        switch (factoryType) {
            case HIBERNATE:
                return new HibernateDaoFactory();
            case JDBC:
                return new JdbcDaoFactory();
            default:
                throw new IllegalArgumentException("Invalid factory");
        }
    }

    public abstract PlayerDao getPlayerDao();
    public abstract GameDao getGameDao();
    public abstract TournamentDao getTourDao();
    public abstract MatchDao getMatchDao();


}
