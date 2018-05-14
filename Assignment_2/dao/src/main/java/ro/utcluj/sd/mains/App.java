package ro.utcluj.sd.mains;

import ro.utcluj.sd.dal.impl.DaoFactory;
import ro.utcluj.sd.dal.impl.MatchDao;
import ro.utcluj.sd.dal.impl.PlayerDao;
import ro.utcluj.sd.dal.impl.TournamentDao;
import ro.utcluj.sd.dal.impl.hibernate.HibernateTournamentDAO;
import ro.utcluj.sd.model.Player;
import ro.utcluj.sd.model.Tournament;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! " );

        PlayerDao p = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getPlayerDao();

       // System.out.println(p.find(3).toString());

        //System.out.println("Asta e pantis: " + p.findByEmail("pantisvlad@bucuresti.ro"));

        //System.out.println(p.selAll().toString());

        //MatchDao m = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();

        //System.out.println(m.find(3).toString());

        TournamentDao t = new HibernateTournamentDAO();

       // System.out.println(t.find(2).toString());
        Tournament tt = t.findByName("First Tournament");
        Player pp = p.findByEmail("noisia@noisia.com");
        System.out.println(tt.getEnrolledPlayers().toString());
        List<Player> x = tt.getEnrolledPlayers();
        x.add(pp);
        tt.setEnrolledPlayers(x);
        t.update(tt);
        tt = t.findByName("First Tournament");
        System.out.println(tt.toString());

        t.closeConnection();

       // m.selAll();


       // m.closeConnection();
        //p.closeConnection();

        //TournamentDao t = new HibernateTournamentDAO();

        //System.out.println(t.find(2).toString());
        //t.closeConnection();

    }
}
