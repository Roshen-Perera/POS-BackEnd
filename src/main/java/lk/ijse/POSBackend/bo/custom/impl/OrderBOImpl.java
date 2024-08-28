package lk.ijse.POSBackend.bo.custom.impl;

import lk.ijse.POSBackend.bo.custom.OrderBO;
import lk.ijse.POSBackend.dao.DAOFactory;
import lk.ijse.POSBackend.dao.custom.OrderDAO;
import lk.ijse.POSBackend.dao.custom.ProductDAO;
import lk.ijse.POSBackend.dto.OrderDTO;
import lk.ijse.POSBackend.entity.Order;
import lk.ijse.POSBackend.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCTS);

    public List<OrderDTO> getAllOrders(Connection connection) throws SQLException {
        List<OrderDTO> productDTOS = new ArrayList<>();
        List<Order> orders = orderDAO.getAll(connection);
        for (Order order : orders) {
            productDTOS.add(new OrderDTO(order.getOrderId(), order.getCustomerId(), order.getCustomerName(), order.getProductId(), order.getProductName(), order.getProductType(), order.getProductQTYNeeded(), order.getProductPrice(), order.getProductTotal()));
        }
        return productDTOS;
    }

    public boolean saveOrder(OrderDTO order, Connection connection){
        try {
            connection.setAutoCommit(false);
            boolean isOrderSaved = orderDAO.saveOrder(new Order(order.getOrderId(), order.getCustomerId(), order.getCustomerName(), order.getProductId(), order.getProductName(), order.getProductType(), order.getProductQTYNeeded(), order.getProductPrice(), order.getProductTotal()), connection);
            if(isOrderSaved){
                boolean isUpdated = productDAO.updateQty(new Product(order.getProductId(),order.getProductName(), order.getProductType(), order.getProductQTYNeeded(), order.getProductPrice()),connection);
                if(!isUpdated) {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteOrder(String orderId, Connection connection){
        return orderDAO.deleteOrder(orderId, connection);
    }
}
