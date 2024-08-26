/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dao.custom.impl;

import lk.ijse.POSBackend.dao.custom.ProductDAO;
import lk.ijse.POSBackend.entity.Product;
import lk.ijse.POSBackend.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO{

    public Product getProduct(String productId, Connection connection) throws SQLException {
        var product = new Product();
        try {
            var ps = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            ps.setString(1, productId);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getString("id"));
                product.setName(resultSet.getString("name"));
                product.setType(resultSet.getString("type"));
                product.setQty(resultSet.getInt("qty"));
                product.setPrice(resultSet.getDouble("price"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean saveProduct(Product product, Connection connection) {
        try {
            var ps = connection.prepareStatement("INSERT INTO product (id,name,type,qty,price) VALUES (?,?,?,?,?)");
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getType());
            ps.setInt(4, product.getQty());
            ps.setDouble(5, product.getPrice());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean deleteProduct(String id, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            ps.setString(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateProduct(Product product, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE product SET name = ?, type = ?, qty = ?, price = ? WHERE id = ?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getType());
            ps.setInt(3, product.getQty());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getId());
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
