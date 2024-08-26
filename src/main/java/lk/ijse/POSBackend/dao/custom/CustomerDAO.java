/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;
import lk.ijse.POSBackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends SuperDAO {
    public List<Customer> getAll(Connection connection) throws SQLException;
    public Customer getCustomer(String customerId, Connection connection) throws SQLException;
    public boolean saveCustomer(Customer customer, Connection connection);
    public boolean deleteCustomer(String customerId, Connection connection);
    public boolean updateCustomer(Customer customer, Connection connection);

}
