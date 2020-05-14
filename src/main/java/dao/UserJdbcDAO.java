package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;
    private static UserJdbcDAO instance;

    private UserJdbcDAO() {
        connection = DBHelper.getInstance().getConnection();
    }

    public static UserJdbcDAO getInstance() {
        if (instance == null) {
            instance = new UserJdbcDAO();
        }
        return instance;
    }

    @Override
    public User find(Long id) {
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getByte(4));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        try (PreparedStatement st = connection.prepareStatement("INSERT user(name, surname, age) VALUES (?, ?, ?)")) {
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setByte(3, user.getAge());
            st.executeUpdate();
        } catch (Exception ignored) { }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE user SET name = ?, surname = ?, age = ? WHERE id = ?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setByte(3, user.getAge());
            st.setLong(4, user.getId());
            st.executeUpdate();
        } catch (Exception ignored) { }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (Exception ignored) { }
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM user")) {
                while (rs.next()) {
                    result.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getByte(4)));
                }
                return result;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> findByName(String name) {
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE name = ?")) {
            return getUsers(name, st);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> findBySurname(String surname) {
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE surname = ?")) {
            return getUsers(surname, st);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> findAgeBetwen(byte startAge, byte endAge) {
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE age > ? AND age < ?")) {
            st.setByte(1, startAge);
            st.setByte(2, endAge);
            try (ResultSet rs = st.executeQuery()) {
                List<User> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getByte(4)));
                }
                return result;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User login(String name, String password) {
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE name = ? AND password = ?")) {
            st.setString(1, name);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setRole(rs.getString(5));
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private List<User> getUsers(String param, PreparedStatement st) throws SQLException {
        st.setString(1, param);
        List<User> result = new ArrayList<>();
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                result.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
            return result;
        }
    }
}
