package lk.ijse.POSBackend.dao.custom.impl;

import lk.ijse.POSBackend.dao.custom.OrderDAO;
import lk.ijse.POSBackend.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public List<Order> getAll(Connection connection) throws SQLException {
        var ps = connection.prepareStatement("SELECT * FROM orders");
        var resultSet = ps.executeQuery();
        List<Order> customerList = new ArrayList<>();
        while (resultSet.next()){
            Order order = new Order(
                    resultSet.getString("orderId"),
                    resultSet.getString("customerId"),
                    resultSet.getString("customerName"),
                    resultSet.getString("productId"),
                    resultSet.getString("productName"),
                    resultSet.getString("productType"),
                    resultSet.getInt("productQTYNeeded"),
                    resultSet.getDouble("productPrice"),
                    resultSet.getDouble("productTotal")
            );
            customerList.add(order);
        }
        return customerList;
    }

    @Override
    public boolean saveOrder(Order order, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (orderId, customerId, customerName, productId, productName, productType, productQTYNeeded, productPrice, productTotal) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getCustomerId());
            ps.setString(3, order.getCustomerName());
            ps.setString(4, order.getProductId());
            ps.setString(5, order.getProductName());
            ps.setString(6, order.getProductType());
            ps.setInt(7, order.getProductQTYNeeded());
            ps.setDouble(8, order.getProductPrice());
            ps.setDouble(9, order.getProductTotal());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteOrder(String orderId, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE orderId = ?");
            ps.setString(1, orderId);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
