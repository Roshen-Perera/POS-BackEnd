package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;
import lk.ijse.POSBackend.entity.User;

import java.sql.Connection;

public interface RegistrationDAO extends SuperDAO {
    public boolean saveUser(User user, Connection connection);
}
