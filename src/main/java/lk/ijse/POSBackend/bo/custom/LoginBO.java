package lk.ijse.POSBackend.bo.custom;

import lk.ijse.POSBackend.bo.SuperBO;

import java.sql.Connection;
import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public boolean login(String username, String password, Connection connection) throws SQLException;
}
