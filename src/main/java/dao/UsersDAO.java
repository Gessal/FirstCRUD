package dao;

import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements CrudDAO<User> {
    private Connection connection;
    private static UsersDAO instance;

    private UsersDAO() {
        connection = DBUtil.getConnection();
    }

    public static UsersDAO getInstance() {
        if (instance == null) {
            instance = new UsersDAO();
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
        } catch (SQLException e) {
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
        } catch (SQLException ignored) { }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE user SET name = ?, surname = ?, age = ? WHERE id = ?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setByte(3, user.getAge());
            st.setLong(4, user.getId());
            st.executeUpdate();
        } catch (SQLException ignored) { }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException ignored) { }
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
        } catch (SQLException e) {
            return null;
        }
    }
}
