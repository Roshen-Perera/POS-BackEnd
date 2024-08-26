/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;
import lk.ijse.POSBackend.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProductDAO extends SuperDAO {

    public Product getProduct(String productId, Connection connection) throws SQLException;

    public boolean saveProduct(Product product, Connection connection);

    public boolean deleteProduct(String id, Connection connection);

    public boolean updateProduct(Product product, Connection connection);
}
