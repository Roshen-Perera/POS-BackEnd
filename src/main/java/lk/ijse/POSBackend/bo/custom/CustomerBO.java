package lk.ijse.POSBackend.bo.custom;

import lk.ijse.POSBackend.bo.SuperBO;
import lk.ijse.POSBackend.dto.CustomerDTO;

import java.sql.Connection;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);

    boolean deleteCustomer(String id, Connection connection);

    boolean updateCustomer(CustomerDTO customerDTO, Connection connection);
}

