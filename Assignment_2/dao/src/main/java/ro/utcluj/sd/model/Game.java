package ro.utcluj.sd.model;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId")
    private int gameID;

    @Column(name = "score1")
    private int score1;

    @Column(name = "score2")
    private int score2;

    @ManyToOne
    @JoinColumn(name = "game_matchID")
    private Match match;

    @Transient
    private int matchID;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Game() {
    }

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

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", match=" + match +
                ", matchID=" + matchID +
                '}';
    }
}
