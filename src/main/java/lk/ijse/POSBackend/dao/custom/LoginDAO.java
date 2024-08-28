package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;

import java.sql.Connection;

public interface LoginDAO extends SuperDAO {
    public boolean checkLogin(String username, String password, Connection connection);
}
