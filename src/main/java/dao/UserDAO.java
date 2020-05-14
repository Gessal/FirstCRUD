package dao;

import model.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    List<User> findAgeBetwen(byte startAge, byte endAge);
    User login(String name, String password);
}
