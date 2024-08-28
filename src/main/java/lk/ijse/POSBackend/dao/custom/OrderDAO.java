package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;
import lk.ijse.POSBackend.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends SuperDAO {
    public List<Order> getAll(Connection connection) throws SQLException;
    public boolean saveOrder(Order order, Connection connection);
    public boolean deleteOrder(String orderId, Connection connection);
}
