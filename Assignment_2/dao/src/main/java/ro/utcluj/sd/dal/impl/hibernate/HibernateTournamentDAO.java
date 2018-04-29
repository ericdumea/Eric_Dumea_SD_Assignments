package ro.utcluj.sd.dal.impl.hibernate;

import javafx.collections.FXCollections;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.dal.HibernateUtil;
import ro.utcluj.sd.dal.impl.TournamentDao;
import ro.utcluj.sd.model.Match;
import ro.utcluj.sd.model.Player;
import ro.utcluj.sd.model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class HibernateTournamentDAO implements TournamentDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Tournament find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Tournament t = (Tournament) currentSession.get(Tournament.class,id);
        transaction.commit();
        return t;
    }

    public void delete(Tournament objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(Tournament objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public int insert(Tournament objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        currentSession.flush();
        transaction.commit();
        return objectToCreate.getId();
    }

    public void deleteById(int id) {

    }

    public void closeConnection() {
        sessionFactory.close();
    }

    public Tournament findByName(String tName) {
        Session sf = sessionFactory.getCurrentSession();
        Transaction t = sf.beginTransaction();

        Query q = sf.createQuery("from Tournament where tournamentName = :tname").setString("tname",tName);

        Tournament tour = (Tournament) q.uniqueResult();

        t.commit();

        return tour;
    }

    public ArrayList<Tournament> findAllTournaments() {

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query q = currentSession.createQuery("from Tournament");

        List<Tournament> list = q.list();

        for(Tournament x : list){
            System.out.println(x.toString());
        }

        transaction.commit();
        return new ArrayList<Tournament>(list);

    }

    public ArrayList<Match> selAllById(int tourid) {
        return null;
    }

    public int insert(String name) {

        Tournament t = new Tournament();
        t.setName(name);
        t.setStatus(Tournament.NOT_STARTED);


        return this.insert(t);
    }

    public int insert(String name,String type) {

        Tournament t = new Tournament();
        t.setName(name);
        t.setStatus(Tournament.NOT_STARTED);
        t.setType(type);

        return this.insert(t);
    }
}
