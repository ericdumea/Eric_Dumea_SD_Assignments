package ro.utcluj.sd.model;

public class Game {
    private int gameID;

    public Game(int matchID) {
        this.matchID = matchID;
        this.score1 = 0;
        this.score2 = 0;
    }

    public Game(int gameID, int score1, int score2, int matchID) {
        this.gameID = gameID;
        this.score1 = score1;
        this.score2 = score2;
        this.matchID = matchID;
    }

    private int score1;
    private int score2;
    private int matchID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }
}
