/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.bo.custom;

import lk.ijse.POSBackend.bo.SuperBO;
import lk.ijse.POSBackend.dto.ProductDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProductBO extends SuperBO {
    public ProductDTO getProduct(String id, Connection connection) throws SQLException;
    public boolean saveProduct(ProductDTO productDTO, Connection connection);
    public boolean deleteProduct(String id, Connection connection);
    public boolean updateProduct(ProductDTO productDTO, Connection connection);
}
