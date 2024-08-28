package lk.ijse.POSBackend.bo.custom;

import lk.ijse.POSBackend.bo.SuperBO;
import lk.ijse.POSBackend.dto.UserDTO;

import java.sql.Connection;

public interface RegistrationBO extends SuperBO {
    public boolean saveUser(UserDTO userDTO, Connection connection);
}
