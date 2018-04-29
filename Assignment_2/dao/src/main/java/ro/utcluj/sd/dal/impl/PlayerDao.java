package ro.utcluj.sd.dal.impl;

import ro.utcluj.sd.model.Player;

import java.util.ArrayList;

public interface PlayerDao extends Dao<Player> {

    void closeConnection();

    ArrayList<Player> selAll();

    Player findByEmail(String playerEmail);
}
