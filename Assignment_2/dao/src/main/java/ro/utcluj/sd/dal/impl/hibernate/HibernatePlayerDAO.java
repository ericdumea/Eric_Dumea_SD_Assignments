package ro.utcluj.sd.dal.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.dal.HibernateUtil;
import ro.utcluj.sd.dal.impl.PlayerDao;
import ro.utcluj.sd.model.Player;

import java.util.ArrayList;
import java.util.List;

public class HibernatePlayerDAO implements PlayerDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Player find(int id) {
        Session sf = sessionFactory.getCurrentSession();
        Transaction t = sf.beginTransaction();
        Player p = (Player) sf.get(Player.class, id);
        t.commit();
        return p;
    }

    public void delete(Player objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(Player objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public int insert(Player objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();

        return 0;
    }

    public void deleteById(int id) {

    }

    public void closeConnection() {
        sessionFactory.close();
    }

    public ArrayList<Player> selAll() {

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query q = currentSession.createQuery("from Player");

        List<Player> list = q.list();

        transaction.commit();
        for(Player x : list){
            System.out.println(x.toString());
        }

        return new ArrayList<Player>(list);

    }

    public Player findByEmail(String playerEmail) {
        Session sf = sessionFactory.getCurrentSession();
        Transaction t = sf.beginTransaction();

        Query q = sf.createQuery("from Player where playerEmail = :email").setString("email",playerEmail);

        Player p = (Player) q.uniqueResult();

        t.commit();
        return p;
    }
}
