package service;

import dao.UserDAO;
import factory.UserDaoFactory;
import model.User;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Service {
    private static Service instance;

    private UserDAO dao;

    private Service() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(".properties");
            properties.load(inputStream);
            this.dao = UserDaoFactory.getDAO(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public List<User> findAllUsers() {
        return dao.findAll();
    }

    public void addUser(User user) {
        dao.save(user);
    }

    public void deleteUser(Long id) {
        dao.delete(id);
    }

    public void updateUser(User user) {
        dao.update(user);
    }

    public User findUserById (Long id) {
        return dao.find(id);
    }
}