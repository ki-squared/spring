package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/kakao?characterEncding=utf-8&serverTimezone=UTC", "root", "root");
    }
}
