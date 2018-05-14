package ro.utcluj.sd.model;

import ro.utcluj.sd.dal.impl.jdbc.GameDAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tournament.match")
public class Match {
    public Match() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "match_playerOneID")
    private Player player1;

    @Transient
    //@Column(name = "match_playerOneID")
    private int playerOneID;


    @ManyToOne
    @JoinColumn(name = "match_playerTwoID")
    private Player player2;

    @Transient
    //@Column(name = "match_playerTwoID")
    private int playerTwoID;

    @ManyToOne
    @JoinColumn(name = "match_tournamentId")
    private Tournament tournament;

    @Transient
    private int tournamentId;

    @Column(name = "p1Score")
    private int p1Score;

    @Column(name = "p2Score")
    private int p2Score;

    @Column(name = "tourPlace")
    private int tourPlace;

    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private List<Game> games;

    public Match(Player player1, Player player2, Tournament tournament, int p1Score, int p2Score, int tourPlace) {
        this.player1 = player1;
        this.player2 = player2;
        this.tournament = tournament;
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.tourPlace = tourPlace;
    }

    public Match(int playerOneID, int playerTwoID, int tournamentId, int p1Score, int p2Score, int tourPlace) {
        this.playerOneID = playerOneID;
        this.playerTwoID = playerTwoID;
        this.tournamentId = tournamentId;
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.tourPlace = tourPlace;
        this.games = new ArrayList<Game>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id &&
                playerOneID == match.playerOneID &&
                playerTwoID == match.playerTwoID &&
                tournamentId == match.tournamentId &&
                p1Score == match.p1Score &&
                p2Score == match.p2Score &&
                tourPlace == match.tourPlace &&
                Objects.equals(player1, match.player1) &&
                Objects.equals(player2, match.player2) &&
                Objects.equals(tournament, match.tournament) &&
                Objects.equals(games, match.games) &&
                Objects.equals(p1name, match.p1name) &&
                Objects.equals(p2name, match.p2name) &&
                Objects.equals(tourname, match.tourname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, player1, playerOneID, player2, playerTwoID, tournament, tournamentId, p1Score, p2Score, tourPlace, games, p1name, p2name, tourname);
    }

    @Transient
    private String p1name;
    @Transient
    private String p2name;
    @Transient
    private String tourname;

    //private ArrayList<Game> games = null;



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

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player1=" + player1 +
                ", playerOneID=" + playerOneID +
                ", player2=" + player2 +
                ", playerTwoID=" + playerTwoID +
                ", tournament=" + tournament +
                ", tournamentId=" + tournamentId +
                ", p1Score=" + p1Score +
                ", p2Score=" + p2Score +
                ", tourPlace=" + tourPlace +
                ", games=" + //games +
                ", p1name='" + p1name + '\'' +
                ", p2name='" + p2name + '\'' +
                ", tourname='" + tourname + '\'' +
                '}';
    }
}
