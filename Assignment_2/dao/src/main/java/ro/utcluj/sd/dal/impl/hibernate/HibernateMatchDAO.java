package ro.utcluj.sd.dal.impl.hibernate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.dal.HibernateUtil;
import ro.utcluj.sd.dal.impl.MatchDao;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class HibernateMatchDAO implements MatchDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public int insert(Match m) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        m.setP1name(m.getPlayer1().getName());
        m.setP2name(m.getPlayer2().getName());
        currentSession.persist(m);
        currentSession.flush();

        transaction.commit();
        return m.getId();
    }

    public ArrayList<Match> allPlayers(int id) {
        return null;
    }

    public ObservableList<Match> selAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query q = currentSession.createQuery("from Match");

        List<Match> list = q.list();

        for(Match x : list){
            System.out.println(x.toString());
        }
        transaction.commit();
        return FXCollections.observableList(list);
    }

    public void closeConnection() {
        sessionFactory.close();
    }

    public Match find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Match cart = (Match) currentSession.get(Match.class, id);
        if(cart.getP1name()==null && cart.getP2name()==null){
            cart.setP1name(cart.getPlayer1().getName());
            cart.setP2name(cart.getPlayer2().getName());
        }
        transaction.commit();
        return cart;
    }

    public void delete(Match objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(Match objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public ArrayList<Match> findAllByTour(int t){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query q = currentSession.createQuery("from Match where match_tournamentId = :tour").setInteger("tour",t);

        List<Match> list = q.list();

        for(Match x : list){
            if(x.getP1name()==null||x.getP2name()==null){
                x.setP1name(x.getPlayer1().getName());
                x.setP2name(x.getPlayer2().getName());
            }
            System.out.println(x.toString());
        }
        transaction.commit();

        return new ArrayList<Match>(list);
    }

    public void deleteById(int id) {

    }
}
