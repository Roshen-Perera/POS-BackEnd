/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;
import lk.ijse.POSBackend.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends SuperDAO {
    public List<Product> getAll(Connection connection) throws SQLException;

    public Product getProduct(String productId, Connection connection) throws SQLException;

    public boolean saveProduct(Product product, Connection connection);

    public boolean deleteProduct(String id, Connection connection);

    public boolean updateProduct(Product product, Connection connection);

    public boolean updateQty(Product product, Connection connection) throws SQLException;
}