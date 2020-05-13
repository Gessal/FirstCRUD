package service;

import dao.HibernateDAO.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class UsersService {
    private static UsersService instance;

    private SessionFactory sessionFactory;
    private UserHibernateDAO dao;

    private UsersService(SessionFactory sessionFactory) {
        this.dao = UserHibernateDAO.getInstance();
        this.sessionFactory = sessionFactory;
    }

    public static UsersService getInstance() {
        if (instance == null) {
            instance = new UsersService(DBHelper.getSessionFactory());
        }
        return instance;
    }

    public List<User> findAllUsers() {
        return daoSession().findAll();
    }

    public void addUser(User user) {
        daoSession().save(user);
    }

    public void deleteUser(Long id) {
        daoSession().delete(id);
    }

    public void updateUser(User user) {
        daoSession().update(user);
    }

    public User findUserById (Long id) {
        return daoSession().getInstance().find(id);
    }

    private UserHibernateDAO daoSession() {
        return dao.setSession(sessionFactory.openSession());
    }
}