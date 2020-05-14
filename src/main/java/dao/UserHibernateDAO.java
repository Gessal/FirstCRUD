package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private static UserHibernateDAO instance;

    private SessionFactory sessionFactory;
    private Session session;

    private UserHibernateDAO() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static UserHibernateDAO getInstance() {
        if (instance == null) {
            instance = new UserHibernateDAO();
        }
        return instance;
    }

    @Override
    public List<User> findByName(String name) {
        try {
            openSession();
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
            openSession();
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
            openSession();
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
    public User login(String name, String password) {
        try {
            openSession();
            Query query = session.createQuery("FROM User WHERE name = :name AND password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User find(Long id) {
        try {
            openSession();
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
            openSession();
            session.save(user);
        } catch (Exception ignored) { }
        finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        try {
            openSession();
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
            openSession();
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
            openSession();
            return (List<User>) session.createQuery("FROM User").list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    private void openSession() {
        this.session = sessionFactory.openSession();
    }
}
