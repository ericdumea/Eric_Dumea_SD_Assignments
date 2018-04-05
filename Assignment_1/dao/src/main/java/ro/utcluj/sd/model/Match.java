package ro.utcluj.sd.model;

import ro.utcluj.sd.dal.GameDAO;

import java.util.ArrayList;

public class Match {
    private int id;
    private int playerOneID;
    private int playerTwoID;
    private int tournamentId;
    private int p1Score;

    public Match(int playerOneID, int playerTwoID, int tournamentId, int p1Score, int p2Score, int tourPlace) {
        this.playerOneID = playerOneID;
        this.playerTwoID = playerTwoID;
        this.tournamentId = tournamentId;
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.tourPlace = tourPlace;
    }

    private int p2Score;
    private int tourPlace;
    private String p1name;
    private String p2name;
    private String tourname;

    private ArrayList<Game> games = null;

    public Match(int id, int playerOneID, int playerTwoID, int tournamentId, int p1Score, int p2Score, int tourPlace, String p1Name, String p2Name, String tourName) {
        this.id = id;
        this.playerOneID = playerOneID;
        this.playerTwoID = playerTwoID;
        this.tournamentId = tournamentId;
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.tourPlace = tourPlace;
        this.p1name = p1Name;
        this.p2name = p2Name;
        this.tourname = tourName;
        games = GameDAO.getGames(id);
        if(games == null){
            games = new ArrayList<Game>();
        }
    }



    public String getP1name() {
        return p1name;
    }

    public void setP1name(String p1Name) {
        this.p1name = p1Name;
    }

    public String getP2name() {
        return p2name;
    }

    public void setP2name(String p2Name) {
        this.p2name = p2Name;
    }

    public String getTourname() {
        return tourname;
    }

    public void setTourname(String tourName) {
        this.tourname = tourName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerOneID() {
        return playerOneID;
    }

    public void setPlayerOneID(int playerOneID) {
        this.playerOneID = playerOneID;
    }

    public int getPlayerTwoID() {
        return playerTwoID;
    }

    public void setPlayerTwoID(int playerTwoID) {
        this.playerTwoID = playerTwoID;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getP1Score() {
        return p1Score;
    }

    public void setP1Score(int p1Score) {
        this.p1Score = p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public void setP2Score(int p2Score) {
        this.p2Score = p2Score;
    }

    public int getTourPlace() {
        return tourPlace;
    }

    public void setTourPlace(int tourPlace) {
        this.tourPlace = tourPlace;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
}
