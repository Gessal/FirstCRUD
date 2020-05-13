package service;

import dao.UsersDAO;
import model.User;

import java.util.List;

public class UsersService {
    private static UsersService instance;

    private UsersService() { }

    public static UsersService getInstance() {
        if (instance == null) {
            instance = new UsersService();
        }
        return instance;
    }

    public List<User> findAllUsers() {
        return UsersDAO.getInstance().findAll();
    }

    public void addUser(User user) {
        UsersDAO.getInstance().save(user);
    }

    public void deleteUser(Long id) {
        UsersDAO.getInstance().delete(id);
    }

    public void updateUser(User user) {
        UsersDAO.getInstance().update(user);
    }

    public User findUserById (Long id) {
        return UsersDAO.getInstance().find(id);
    }
}