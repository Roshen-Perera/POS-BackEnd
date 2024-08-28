package lk.ijse.POSBackend.dao.custom.impl;

import lk.ijse.POSBackend.dao.custom.LoginDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    public boolean checkLogin(String username, String password, Connection connection) {
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
