package ro.utcluj.sd.dal;

import ro.utcluj.sd.ConnectionFactory;
import ro.utcluj.sd.model.Game;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;

import java.sql.*;
import java.util.ArrayList;

public class GameDAO {

    private final static String insertStatementString = "INSERT INTO tournament.game" +
            " (score1, score2, game_matchID) VALUES (?,?,?)";
    private final static String findAllMatch = "SELECT * FROM tournament.game WHERE game_matchID = ?";

    public static int insert(Game g) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, g.getScore1());
            insertStatement.setInt(2, g.getScore2());
            insertStatement.setInt(3, g.getMatchID());
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

    public static ArrayList<Game> getGames(int id) {
        ArrayList<Game> toReturn = new ArrayList<Game>();
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {
            int gameid,score1,score2;
            st = dbConnection.prepareStatement(findAllMatch);
            st.setInt(1, id);
            rs = st.executeQuery();

            while(rs.next()){

                gameid= rs.getInt("gameId");
                score1 = rs.getInt("score1");
                score2 = rs.getInt("score2");


                toReturn.add(new Game(gameid,score1,score2,id));

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

}
