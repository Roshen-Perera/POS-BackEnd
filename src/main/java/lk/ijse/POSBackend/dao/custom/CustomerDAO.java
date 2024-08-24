package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;
import lk.ijse.POSBackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerDAO extends SuperDAO {
    public Customer getCustomer(String customerId, Connection connection) throws SQLException;
    public boolean saveCustomer(Customer customer, Connection connection);
    public boolean deleteCustomer(int customerId, Connection connection);
    public boolean updateCustomer(Customer customer, Connection connection);

}
