package lk.ijse.POSBackend.dao.custom.impl;

import lk.ijse.POSBackend.dao.custom.CustomerDAO;
import lk.ijse.POSBackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    public List<Customer> getAll(Connection connection) throws SQLException {
        var ps = connection.prepareStatement("SELECT * FROM customer");
        var resultSet = ps.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()){
            Customer customers = new Customer(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone")
            );
            customerList.add(customers);
        }
        return customerList;
    }

    @Override
    public Customer getCustomer(String customerId, Connection connection) throws SQLException {
        var customer = new Customer();
        try {
            var ps = connection.prepareStatement("SELECT * FROM customer WHERE id = ?");
            ps.setString(1, customerId);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhone(resultSet.getString("phone"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean saveCustomer(Customer customer, Connection connection) {

        try {
            var ps = connection.prepareStatement("INSERT INTO customer (id,name,address,phone) VALUES (?,?,?,?)");
            ps.setString(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getPhone());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) {
        try {
            var ps = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
            ps.setString(1, customerId);
            return ps.executeUpdate() != 0;
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    @Override
    public boolean updateCustomer(Customer customer, Connection connection) {
        try {
            var ps = connection.prepareStatement("UPDATE customer SET name = ?, address = ?, phone = ? WHERE id = ?");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getId());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
