/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.bo.custom.impl;

import lk.ijse.POSBackend.bo.custom.ProductBO;
import lk.ijse.POSBackend.dao.DAOFactory;
import lk.ijse.POSBackend.dao.custom.ProductDAO;
import lk.ijse.POSBackend.dto.ProductDTO;
import lk.ijse.POSBackend.entity.Product;

import java.sql.Connection;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCTS);
    public boolean saveProduct(ProductDTO productDTO, Connection connection) {
        return productDAO.saveProduct(new Product(productDTO.getId(), productDTO.getName(), productDTO.getType(), productDTO.getQty(), productDTO.getPrice()), connection);
    }
    public boolean deleteProduct(String id, Connection connection) {
        return productDAO.deleteProduct(id, connection);
    }

    public boolean updateProduct(ProductDTO productDTO, Connection connection) {
        return productDAO.updateProduct(new Product(productDTO.getId(), productDTO.getName(), productDTO.getType(), productDTO.getQty(), productDTO.getPrice()), connection);
    }
}
