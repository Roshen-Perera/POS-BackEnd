package lk.ijse.POSBackend.bo.custom.impl;

import lk.ijse.POSBackend.bo.custom.LoginBO;
import lk.ijse.POSBackend.dao.DAOFactory;
import lk.ijse.POSBackend.dao.custom.LoginDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    public boolean login(String username, String password, Connection connection) throws SQLException {
        return loginDAO.checkLogin(username, password, connection);
    }
}
