package dao;

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
        try {
            Query query = session.createQuery("FROM User WHERE name = :name");
            query.setParameter("name", name);
            return (List<User>) query.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findBySurname(String surname) {
        try {
            Query query = session.createQuery("FROM User WHERE surname = :surname");
            query.setParameter("surname", surname);
            return (List<User>) query.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAgeBetwen(byte startAge, byte endAge) {
        try {
            Query query = session.createQuery("FROM User WHERE age > :startAge AND age < endAge");
            query.setParameter("startAge", startAge);
            query.setParameter("endAge", endAge);
            return (List<User>) query.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User find(Long id) {
        try {
            Query query = session.createQuery("FROM User WHERE id = :id");
            query.setParameter("id", id);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(User user) {
        try {
            session.save(user);
        } catch (Exception ignored) { }
        finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            User u = (User) session.get(User.class, user.getId());
            u.setName(user.getName());
            u.setSurname(user.getSurname());
            u.setAge(user.getAge());
            session.update(u);
            transaction.commit();
        } catch (Exception ignored) { }
        finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Query query = session.createQuery("DELETE User WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception ignored) { }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return (List<User>) session.createQuery("FROM User").list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    public UserHibernateDAO setSession(Session session) {
        this.session = session;
        return this;
    }
}
