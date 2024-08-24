package lk.ijse.POSBackend.bo.custom.impl;

import lk.ijse.POSBackend.bo.custom.CustomerBO;
import lk.ijse.POSBackend.dao.DAOFactory;
import lk.ijse.POSBackend.dao.custom.CustomerDAO;
import lk.ijse.POSBackend.dto.CustomerDTO;
import lk.ijse.POSBackend.entity.Customer;

import java.sql.Connection;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERS);

    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerDAO.saveCustomer(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getPhone()), connection);
    }

    @Override
    public boolean deleteCustomer(int id, Connection connection) {
        return customerDAO.deleteCustomer(id, connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) {
        return customerDAO.updateCustomer(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getPhone()), connection);
    }
}
