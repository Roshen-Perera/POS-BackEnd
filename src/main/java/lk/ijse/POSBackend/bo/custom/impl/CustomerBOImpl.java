package lk.ijse.POSBackend.bo.custom.impl;

import lk.ijse.POSBackend.bo.custom.CustomerBO;
import lk.ijse.POSBackend.dao.DAOFactory;
import lk.ijse.POSBackend.dao.custom.CustomerDAO;
import lk.ijse.POSBackend.dto.CustomerDTO;
import lk.ijse.POSBackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERS);

    public CustomerDTO getCustomer(String id, Connection connection) throws SQLException {
        Customer customer = customerDAO.getCustomer(id, connection);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(), customer.getPhone());
    }

    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerDAO.saveCustomer(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getPhone()), connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) {
        return customerDAO.deleteCustomer(id, connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerDAO.updateCustomer(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getPhone()), connection);
    }
}
