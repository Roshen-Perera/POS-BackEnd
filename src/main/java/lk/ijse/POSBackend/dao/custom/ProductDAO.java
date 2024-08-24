/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dao.custom;

import lk.ijse.POSBackend.dao.SuperDAO;
import lk.ijse.POSBackend.entity.Product;

import java.sql.Connection;

public interface ProductDAO extends SuperDAO {
    public boolean saveProduct(Product product, Connection connection);

    public boolean deleteProduct(int id, Connection connection);
}
