package ro.utcluj.sd.model;

import ro.utcluj.sd.model.Match;

import javax.persistence.*;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Transient
    public static final String PAID = "Paid";

    @Transient
    public static final String FREE = "Free";

    @Transient
    public static final String ENDED = "Ended";

    @Transient
    public static final String IN_PROGRESS = "In progress";

    @Transient
    public static final String NOT_STARTED = "Not started";

    @Transient
    public static final String ENROLL = "Enroll";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournamentID")
    private int id;

    @Column(name = "tournamentName")
    private String name;

    @Column(name = "tournamentStatus")
    private String status;

    @Column(name = "winnerID")
    private Integer winnerID = 0;

    @Column(name = "fee")
    private Integer fee = 0;
    @Column(name = "type")
    private String type;
    @Column(name = "start_date")
    private Date date;
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Match> matches;
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "enrollment",
            joinColumns = {@JoinColumn(name = "tournament_tournamentID")},
            inverseJoinColumns = {@JoinColumn(name = "player_playerID")}
    )

    private List<Player> enrolledPlayers;

    public Tournament(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;

        matches = new ArrayList<Match>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status) &&
                Objects.equals(winnerID, that.winnerID) &&
                Objects.equals(fee, that.fee) &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date) &&
                Objects.equals(matches, that.matches);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, status, winnerID, fee, type, date, matches);
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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Player> getEnrolledPlayers() {
        return enrolledPlayers;
    }

    public void setEnrolledPlayers(List<Player> players) {
        this.enrolledPlayers = players;
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
        if (winnerID == null)
            winnerID = 0;
        return winnerID;
    }

    public void setWinnerID(Integer winnerID) {
        if (winnerID == null) {
            this.winnerID = 0;
        } else
            this.winnerID = winnerID;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", winnerID=" + winnerID +
                ", matches=" + "" +
                '}';
    }
}
