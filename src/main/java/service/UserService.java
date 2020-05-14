package service;

import dao.UserDAO;
import factory.UserDaoFactory;
import model.User;
import util.PropertyReader;

import java.util.List;

public class UserService {
    private static UserService instance;

    private UserDAO dao;

    private UserService() {
        try {
            this.dao = UserDaoFactory.getDAO(PropertyReader.getProperties(".properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
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

    public User login(String name, String password) {
        return dao.login(name, password);
    }
}