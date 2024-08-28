package lk.ijse.POSBackend.dao.custom.impl;

import lk.ijse.POSBackend.dao.custom.RegistrationDAO;
import lk.ijse.POSBackend.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean saveUser(User user, Connection connection) {
        try {
            var ps = connection.prepareStatement("INSERT INTO user (name,email,password) VALUES (?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
