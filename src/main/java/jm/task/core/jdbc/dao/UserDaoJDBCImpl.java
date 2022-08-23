package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.model.core.ID;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.lang.model.element.Name;
import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {
       try (Connection connection = new Util().getConnection(); Statement statement = connection.createStatement()){
           String sql = "CREATE TABLE IF NOT EXISTS USERS(ID BIGINT(40) NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(40), LASTNAME VARCHAR(40), AGE TINYINT(40))";
           statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        try(Connection connection = new Util().getConnection(); Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE IF EXISTS USERS";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try(Connection connection = new Util().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql))  {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS Where id = ?";
        try (Connection connection = new Util().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = new Util().getConnection(); Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM USERS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastname, age);

                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;



    }

    public void cleanUsersTable() {
        try (Connection connection = new Util().getConnection(); Statement statement = connection.createStatement()){
            String sql = "DELETE FROM USERS";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
