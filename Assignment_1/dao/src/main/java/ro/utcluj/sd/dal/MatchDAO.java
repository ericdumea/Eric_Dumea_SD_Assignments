package ro.utcluj.sd.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ro.utcluj.sd.ConnectionFactory;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;

import java.sql.*;
import java.util.ArrayList;

public class MatchDAO {

    public static final String findAllMatches = "SELECT P1.playerName AS p1, P2.playerName AS p2,matchID, tournamentName," +
            " match_playerOneID, match_playerTwoID, match_tournamentId, p1Score,p2Score,tourPlace FROM tournament.match INNER JOIN " +
            "tournament.tournament ON match_tournamentId = tournamentID INNER JOIN ( player P1, player P2 ) ON P1.playerID = match_playerOneID AND P2.playerID = match_playerTwoID";

    private final static String insertStatementString = "INSERT INTO tournament.match" +
            " (match_playerOneID, match_playerTwoID, match_tournamentId, p1Score,p2Score,tourPlace) VALUES (?,?,?,?,?,?)";

    public static final String findAllPlayerMatches = "SELECT * FROM tournament_match WHERE ( match_playerOneID = ? OR match_playerTwoID = ?";

    public static ObservableList<Match> selAll() {
        ObservableList<Match> toReturn =FXCollections.observableArrayList();
        Player p = null;
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {

            int id;
            int playerOneID;
            int playerTwoID;
            int tournamentId;
            int p1Score;
            int p2Score;
            int tourPlace;
            String p1Name;
            String p2Name;
            String tourName;
            st = dbConnection.prepareStatement(findAllMatches);
            rs = st.executeQuery();

            while(rs.next()){

                id= rs.getInt("matchID");
                playerOneID = rs.getInt("match_playerOneID");
                playerTwoID = rs.getInt("match_playerTwoID");
                tournamentId = rs.getInt("match_tournamentId");
                p1Score = rs.getInt("p1Score");
                p2Score = rs.getInt("p2Score");
                tourPlace = rs.getInt("tourPlace");
                p1Name = rs.getString("p1");
                p2Name = rs.getString("p2");
                tourName = rs.getString("tournamentName");

                toReturn.add(new Match(id,playerOneID,playerTwoID,tournamentId,p1Score,p2Score,tourPlace,p1Name,p2Name,tourName));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(toReturn.toString());
        return toReturn;
    }

    public static ArrayList<Match> allPlayers(int id) {
        ArrayList<Match> toReturn = new ArrayList<Match>();
        Player p = null;
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {
            int playerOneID;
            int playerTwoID;
            int tournamentId;
            int p1Score;
            int p2Score;
            int tourPlace;
            String p1Name;
            String p2Name;
            String tourName;
            st = dbConnection.prepareStatement(findAllPlayerMatches);
            st.setInt(1, id);
            st.setInt(2, id);
            rs = st.executeQuery();

            while(rs.next()){

                id= rs.getInt("matchID");
                playerOneID = rs.getInt("match_playerOneID");
                playerTwoID = rs.getInt("match_playerTwoID");
                tournamentId = rs.getInt("match_tournamentId");
                p1Score = rs.getInt("p1Score");
                p2Score = rs.getInt("p2Score");
                tourPlace = rs.getInt("tourPlace");
                p1Name = rs.getString("p1");
                p2Name = rs.getString("p2");
                tourName = rs.getString("tournamentName");

                toReturn.add(new Match(id,playerOneID,playerTwoID,tournamentId,p1Score,p2Score,tourPlace,p1Name,p2Name,tourName));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(toReturn.toString());
        return toReturn;
    }


    public static int insertMatch(Match m) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, m.getPlayerOneID());
            insertStatement.setInt(2, m.getPlayerTwoID());
            insertStatement.setInt(3, m.getTournamentId());
            insertStatement.setInt(4, m.getP1Score());
            insertStatement.setInt(5, m.getP2Score());
            insertStatement.setInt(6, m.getTourPlace());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }



}
