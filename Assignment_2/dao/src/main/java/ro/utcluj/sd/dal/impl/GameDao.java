package ro.utcluj.sd.dal.impl;

import ro.utcluj.sd.model.Game;

public interface GameDao extends Dao<Game> {

    void closeConnection();
}
