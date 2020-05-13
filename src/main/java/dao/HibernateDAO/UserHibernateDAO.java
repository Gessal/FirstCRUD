package dao.HibernateDAO;

import dao.interfaceDao.UserDAO;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private static UserHibernateDAO instance;

    private Session session;

    private UserHibernateDAO() { }

    public static UserHibernateDAO getInstance() {
        if (instance == null) {
            instance = new UserHibernateDAO();
        }
        return instance;
    }

    @Override
    public List<User> findByName(String name) {
        Query query = session.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        List<User> users = query.list();
        session.close();
        return users;
    }

    @Override
    public List<User> findBySurname(String surname) {
        Query query = session.createQuery("FROM User WHERE surname = :surname");
        query.setParameter("surname", surname);
        List<User> users = query.list();
        session.close();
        return users;
    }

    @Override
    public List<User> findAgeBetwen(byte startAge, byte endAge) {
        Query query = session.createQuery("FROM User WHERE age > :startAge AND age < endAge");
        query.setParameter("startAge", startAge);
        query.setParameter("endAge", endAge);
        List<User> users = query.list();
        session.close();
        return users;
    }

    @Override
    public User find(Long id) {
        Query query = session.createQuery("FROM User WHERE id = :id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void save(User user) {
        session.save(user);
        session.close();
    }

    @Override
    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        User u = (User) session.get(User.class, user.getId());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAge(user.getAge());
        session.update(u);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Query query = session.createQuery("DELETE User WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.close();
    }

    @Override
    public List<User> findAll() {
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    public UserHibernateDAO setSession(Session session) {
        this.session = session;
        return this;
    }
}
