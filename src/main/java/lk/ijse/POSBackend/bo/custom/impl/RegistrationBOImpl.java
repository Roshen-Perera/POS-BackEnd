package lk.ijse.POSBackend.bo.custom.impl;

import lk.ijse.POSBackend.bo.custom.RegistrationBO;
import lk.ijse.POSBackend.dao.DAOFactory;
import lk.ijse.POSBackend.dao.custom.RegistrationDAO;
import lk.ijse.POSBackend.dto.UserDTO;
import lk.ijse.POSBackend.entity.User;

import java.sql.Connection;

public class RegistrationBOImpl implements RegistrationBO {
    RegistrationDAO userDAO = (RegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    public boolean saveUser(UserDTO userDTO, Connection connection) {
        return userDAO.saveUser(new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword()), connection);
    }
}
