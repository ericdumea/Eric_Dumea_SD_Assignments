package ro.utcluj.sd.model;

import ro.utcluj.sd.model.Match;

import java.util.ArrayList;

public class Tournament {
    private int id;
    private String name;
    private String status;
    private int winnerID;

    private ArrayList<Match> matches;

    public Tournament(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
        matches = new ArrayList<Match>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(int winnerID) {
        this.winnerID = winnerID;
    }

    public Tournament() {
    }

    public Tournament(int id, String name, String status, int winnerID) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.winnerID = winnerID;
        matches = new ArrayList<Match>();
    }
}
