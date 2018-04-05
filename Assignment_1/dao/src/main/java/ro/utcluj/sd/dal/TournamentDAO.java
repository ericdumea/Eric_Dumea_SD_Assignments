package ro.utcluj.sd.dal;

import ro.utcluj.sd.ConnectionFactory;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;
import ro.utcluj.sd.model.Tournament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TournamentDAO {

    private final static String findStatementStringId = "SELECT * FROM tournament.tournament INNER JOIN tournament.match" +
            " ON tournamentID = tournament.match.match_tournamentId where playerId = ?";
    private final static String findStatementStringName = "SELECT * FROM tournament.tournament INNER JOIN tournament.match" +
            " ON tournamentID = tournament.match.match_tournamentId where tournamentName = ?";
    private final static String findStatementStringName2 = "SELECT * FROM tournament.tournament WHERE tournamentName = ?";
    private final static String findStatementStringAll = "SELECT P1.playerName AS p1, P2.playerName AS p2,matchID, tournamentName," +
            " match_playerOneID, match_playerTwoID, match_tournamentId, p1Score,p2Score,tourPlace FROM tournament.match INNER JOIN " +
            "tournament.tournament ON match_tournamentId = tournamentID INNER JOIN ( player P1, player P2 ) ON P1.playerID " +
            "= match_playerOneID AND P2.playerID = match_playerTwoID WHERE tournament.tournamentID = ?";
    private final static String insertStatementString = "INSERT INTO tournament.tournament" +
            " (tournamentName, tournamentStatus ) VALUES (?,?)";
    private final static String delStatementString = "DELETE FROM tournament.tournament WHERE tournamentID = ?";


    private final static String findStatementSelectAll = "SELECT * FROM tournament.tournament";

    private final static String updateStatementString = "UPDATE tournament.tournament SET tournamentName = ?," +
            " tournamentStatus = ?, winnerID = ? WHERE tournamentID = ?";


    private ArrayList<Match> matches = new ArrayList<Match>();

    public static Tournament findByid(int tourid) {
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        Tournament t = new Tournament();

        String name = "";
        String status = "";
        int winnerID = 0;

        try {
            st = dbConnection.prepareStatement(findStatementStringId);
            st.setInt(1, tourid);
            rs = st.executeQuery();
            rs.next();

            name = rs.getString("tournamentName");
            status = rs.getString("tournamentStatus");
            winnerID = rs.getInt("winnerID");

            t.setId(tourid);
            t.setName(name);
            t.setStatus(status);
            t.setWinnerID(winnerID);


        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }

        return t;

    }

    public static Tournament findByName (String tName) {
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        Tournament t = new Tournament();

        int tourid=0;
        String status = "";
        int winnerID = 0;

        try {
            st = dbConnection.prepareStatement(findStatementStringName2);
            st.setString(1, tName);
            rs = st.executeQuery();
            //rs.next();

            if(!rs.next()){
                return null;
            } else {

                tourid = rs.getInt("tournamentID");
                status = rs.getString("tournamentStatus");
                winnerID = rs.getInt("winnerID");

                t.setId(tourid);
                t.setName(tName);
                t.setStatus(status);
                t.setWinnerID(winnerID);
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

        return t;

    }

    public static ArrayList<Tournament> findAllTournaments() {
        ArrayList<Tournament> toReturn = new ArrayList<Tournament>();
        Tournament t = null;
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {


            st = dbConnection.prepareStatement(findStatementSelectAll);
            rs = st.executeQuery();

            while(rs.next()){
                t = new Tournament();
                t.setId(rs.getInt("tournamentID"));
                t.setName(rs.getString("tournamentName"));
                t.setWinnerID(rs.getInt("winnerID"));
                t.setStatus(rs.getString("tournamentStatus"));


                toReturn.add(t);
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
        return toReturn;
    }

    public static ArrayList<Match> selAllById(int tourid) {
        ArrayList<Match> toReturn = new ArrayList<Match>();
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

            st = dbConnection.prepareStatement(findStatementStringAll);
            st.setInt(1, tourid);
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

    public static int insert(String name) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, name);
            insertStatement.setString(2, "Not started");
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

    public static void delete(Tournament t){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement delStatement = null;
        int id=t.getId();

        try {
            delStatement = dbConnection.prepareStatement(delStatementString, Statement.RETURN_GENERATED_KEYS);
            delStatement.setInt(1,id);
            delStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(delStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void update(Tournament t){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1, t.getName());
            updateStatement.setString(2, t.getStatus());
            updateStatement.setInt(3, t.getWinnerID());
            updateStatement.setInt(4, t.getId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
