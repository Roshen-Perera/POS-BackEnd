package lk.ijse.POSBackend.bo.custom;

import lk.ijse.POSBackend.bo.SuperBO;
import lk.ijse.POSBackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;
    boolean saveCustomer(CustomerDTO customerDTO, Connection connection);
    boolean deleteCustomer(String id, Connection connection);
    boolean updateCustomer(CustomerDTO customerDTO, Connection connection);
    CustomerDTO getCustomer(String id, Connection connection) throws SQLException;
}

