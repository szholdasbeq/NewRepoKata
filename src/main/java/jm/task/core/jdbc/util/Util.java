package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtestsanzhar";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "7922050Sanzhar";
    // реализуйте настройку соеденения с БД
    public static Connection getConnection(){
        Connection connection;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера!");
        }
        return null;
    }


}
