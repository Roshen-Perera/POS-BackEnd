/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.POSBackend.bo.BOFactory;
import lk.ijse.POSBackend.bo.custom.ProductBO;
import lk.ijse.POSBackend.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/product")
public class ProductController extends HttpServlet {
    static Logger logger =  LoggerFactory.getLogger(ProductController.class);
    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCTS);
    // ProductDAO customerDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERS);

    Connection connection;

    @Override
    public void init() {
        logger.info("Initializing ProductController with call init method");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/proRegistration");
            this.connection =  pool.getConnection();
        }catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("Adding Products");
        // Persist Data
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var productDTO = jsonb.fromJson(req.getReader(), ProductDTO.class);
            logger.info(productDTO.toString());
            boolean isSaved = productBO.saveProduct(productDTO, connection);
            if (isSaved){
                logger.info("Product saved");
                writer.println("Product saved");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                logger.info("Product not saved");
                writer.println("Product not saved");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        logger.info("Deleting Product");
        try (var writer = resp.getWriter()){
            if(productBO.deleteProduct(id, connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                logger.info("Product deleted");
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                logger.info("Product not deleted");
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("Updating Products");
        // Persist Data
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var productDTO = jsonb.fromJson(req.getReader(), ProductDTO.class);
            logger.info(productDTO.toString());
            boolean isSaved = productBO.updateProduct(productDTO, connection);
            if (isSaved){
                logger.info("Product updated");
                writer.println("Product updated");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                logger.info("Product not updated");
                writer.println("Product not updated");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        logger.info("Getting Product");
        try (var writer = resp.getWriter()){

        }
    }
}
