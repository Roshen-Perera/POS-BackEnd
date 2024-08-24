/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dao.custom.impl;

import lk.ijse.POSBackend.dao.custom.ProductDAO;
import lk.ijse.POSBackend.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO{
    public boolean saveProduct(Product product, Connection connection) {
        try {
            var ps = connection.prepareStatement("INSERT INTO product (id,name,type,qty,price) VALUES (?,?,?,?,?)");
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getType());
            ps.setInt(4, product.getQty());
            ps.setDouble(5, product.getPrice());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean deleteProduct(int id, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
