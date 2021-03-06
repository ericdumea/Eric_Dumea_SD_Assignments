package ro.utcluj.sd.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "player")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerID")
    private int id;

    @Column(name = "playerName")
    private String name;

    @Column(name = "playerEmail")
    private String email;

    @Column(name = "playerPassword")
    private String password;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "account")
    private Integer account = 0;
    @OneToMany(mappedBy = "player1")
    private List<Match> matchesP1;
    @OneToMany(mappedBy = "player2")
    private List<Match> matchesP2;
    @ManyToMany(mappedBy = "enrolledPlayers", fetch = FetchType.EAGER)
    private List<Tournament> tournaments;

    public Player() {
    }

    public Player(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public Player(int id, String name, String email, String password, boolean admin) {
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.name = name;
        this.id = id;
    }

    public Player(String email, String pass, String name) {
        this.password = pass;
        this.email = email;
        this.name = name;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Match> getMatchesP1() {
        return matchesP1;
    }

    public void setMatchesP1(List<Match> matchesP1) {
        this.matchesP1 = matchesP1;
    }

    public List<Match> getMatchesP2() {
        return matchesP2;
    }

    public void setMatchesP2(List<Match> matchesP2) {
        this.matchesP2 = matchesP2;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id &&
                admin == player.admin &&
                Objects.equals(name, player.name) &&
                Objects.equals(email, player.email) &&
                Objects.equals(password, player.password) &&
                Objects.equals(account, player.account) &&
                Objects.equals(matchesP1, player.matchesP1) &&
                Objects.equals(matchesP2, player.matchesP2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, password, admin, account, matchesP1, matchesP2);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +

                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
