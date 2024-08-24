/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.bo.custom;

import lk.ijse.POSBackend.bo.SuperBO;
import lk.ijse.POSBackend.dto.ProductDTO;

import java.sql.Connection;

public interface ProductBO extends SuperBO {
    public boolean saveProduct(ProductDTO productDTO, Connection connection);
    public boolean deleteProduct(int id, Connection connection);
}
