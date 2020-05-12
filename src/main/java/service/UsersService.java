package service;

import dao.JdbcDao.UserJdbcDAO;
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
        return UserJdbcDAO.getInstance().findAll();
    }

    public void addUser(User user) {
        UserJdbcDAO.getInstance().save(user);
    }

    public void deleteUser(Long id) {
        UserJdbcDAO.getInstance().delete(id);
    }

    public void updateUser(User user) {
        UserJdbcDAO.getInstance().update(user);
    }
}