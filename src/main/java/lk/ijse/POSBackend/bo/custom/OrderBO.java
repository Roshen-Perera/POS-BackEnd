package lk.ijse.POSBackend.bo.custom;

import lk.ijse.POSBackend.bo.SuperBO;
import lk.ijse.POSBackend.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    public List<OrderDTO> getAllOrders(Connection connection) throws SQLException;
    public boolean saveOrder(OrderDTO order, Connection connection);
}
