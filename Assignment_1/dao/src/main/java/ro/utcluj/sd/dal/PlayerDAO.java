package ro.utcluj.sd.dal;

import ro.utcluj.sd.ConnectionFactory;
import ro.utcluj.sd.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDAO {

    private final static String findStatementStringName = "SELECT * FROM player where playerName = ?";
    private final static String findStatementStringAll = "SELECT * FROM player";
    private final static String findStatementStringId = "SELECT * FROM player where playerId = ?";
    private final static String findStatementStringEmail = "SELECT * FROM player where playerEmail = ?";
    private final static String findStatementStringAdminStatus = "SELECT * FROM player where playerEmail = ?";
    private final static String insertStatementString = "INSERT INTO player (playerEmail,playerName,playerPassword,admin) VALUES (?,?,?,0)";
    private final static String updateStatementString = "UPDATE tournament.player SET playerName = ?, playerPassword = ?, playerEmail = ? WHERE playerId = ?";
    private final static String delStatementString = "DELETE FROM player WHERE playerId = ?";


    public static void findByName(String clientid) {
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {
            st = dbConnection.prepareStatement(findStatementStringName);
            st.setString(1, clientid);
            rs = st.executeQuery();
            rs.next();

            int id = rs.getInt("playerID");
            //String address = rs.getString("playerUser");
            String email = rs.getString("playerEmail");

            System.out.println(id+" "+email+" ");
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }


    }

    public static String passByEmail(String userEmail) {
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        String password = "";

        try {
            st = dbConnection.prepareStatement(findStatementStringEmail);
            st.setString(1, userEmail);
            rs = st.executeQuery();
            rs.next();

            password = rs.getString("playerPassword");
            //String address = rs.getString("playerUser");
            //String email = rs.getString("playerEmail");

            //System.out.println(password+" "+email+" ");
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }

        return password;
    }

    public static int adminRights(String userEmail) {
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        int admin = 0;

        try {
            st = dbConnection.prepareStatement(findStatementStringAdminStatus);
            st.setString(1, userEmail);
            rs = st.executeQuery();
            rs.next();

            admin = rs.getInt("admin");
            //String address = rs.getString("playerUser");
            String email = rs.getString("playerEmail");

            //System.out.println(admin+" "+email+" ");
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }

        return admin;
    }

    public static int insert(Player p) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, p.getEmail());
            insertStatement.setString(3, p.getPassword());
            insertStatement.setString(2, p.getName());
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

    public static Player findByid(int playerid) {
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        Player p = null;
        String email ="";
        String name = "";
        String pass = "";
        int admin = 0;
        try {
            st = dbConnection.prepareStatement(findStatementStringId);
            st.setInt(1, playerid);
            rs = st.executeQuery();
            rs.next();

            name = rs.getString("playerName");
            email = rs.getString("playerEmail");
            admin = rs.getInt("admin");
            pass = rs.getString("playerPassword");



            //System.out.println(id+" "+email+" ");
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }

        boolean adminn;
        if(admin == 0){
            adminn = false;
        } else adminn = true;
        p = new Player(playerid, name, email, pass, adminn);

        return p;
    }

    public static Player findByEmail(String playerEmail) {
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        Player p = null;
        int id = 0;
        String name = "";
        String pass = "";
        int admin = 0;
        try {
            st = dbConnection.prepareStatement(findStatementStringEmail);
            st.setString(1, playerEmail);
            rs = st.executeQuery();
            rs.next();

            name = rs.getString("playerName");
            id = rs.getInt("playerId");
            admin = rs.getInt("admin");
            pass = rs.getString("playerPassword");
            //System.out.println(id+" "+email+" ");
        } catch (SQLException e) {
            e.printStackTrace();
            // LOGGER.log(Level.WARNING,"StudentDAO:findById " +
            // e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(dbConnection);
        }

        boolean adminn;
        if(admin == 0){
            adminn = false;
        } else adminn = true;
        p = new Player(id, name, playerEmail, pass, adminn);

        return p;
    }

    public static ArrayList<Player> selAll() {
        ArrayList<Player> toReturn = new ArrayList<Player>();
        Player p = null;
        PreparedStatement st = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {


            st = dbConnection.prepareStatement(findStatementStringAll);
            rs = st.executeQuery();
            int id = 0;
            String name = "";
            String pass = "";
            String email = "";
            int admin = 0;

            while(rs.next()){
                name = rs.getString("playerName");
                id = rs.getInt("playerId");
                admin = rs.getInt("admin");
                pass = rs.getString("playerPassword");
                email = rs.getString("playerEmail");
                boolean adminn;

                if(admin == 0){
                    adminn = false;
                } else adminn = true;

                toReturn.add(new Player(id, name, email, pass, adminn));
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

    public static void update(Player c){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1, c.getName());
            updateStatement.setString(2, c.getPassword());
            updateStatement.setString(3, c.getEmail());
            updateStatement.setInt(4, c.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void delete(Player c){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement delStatement = null;
        int id=c.getId();

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

}
