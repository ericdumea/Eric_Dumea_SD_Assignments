package ro.utcluj.sd.dal.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.dal.HibernateUtil;
import ro.utcluj.sd.dal.impl.GameDao;
import ro.utcluj.sd.model.Game;

public class HibernateGameDAO implements GameDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Game find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Game g = (Game) currentSession.get(Game.class,id);
        transaction.commit();
        return g;

    }

    public void delete(Game objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(Game objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public int insert(Game objectToCreate) {
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
}
