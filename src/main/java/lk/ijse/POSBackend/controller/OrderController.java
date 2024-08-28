package lk.ijse.POSBackend.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.POSBackend.bo.BOFactory;
import lk.ijse.POSBackend.bo.custom.OrderBO;
import lk.ijse.POSBackend.dto.OrderDTO;
import lk.ijse.POSBackend.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    static Logger logger =  LoggerFactory.getLogger(OrderController.class);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERS);

    Connection connection;

    @Override
    public void init() {
        logger.info("Initializing OrderController with call init method");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/ordRegistration");
            this.connection =  pool.getConnection();
        }catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("Adding Orders");
        // Persist Data
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var OrderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
            logger.info(OrderDTO.toString());
            boolean isSaved = orderBO.saveOrder(OrderDTO, connection);
            if (isSaved){
                logger.info("Order saved");
                writer.println("Order saved");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                logger.info("Order not saved");
                writer.println("Order not saved");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            List<OrderDTO> OrderDTOList = orderBO.getAllOrders(connection);
            logger.info(OrderDTOList.toString());
            if (OrderDTOList != null) {
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(OrderDTOList, writer);
            } else {
                writer.write("No Orders found");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = req.getParameter("id");
        logger.info("Deleting Order");
        try (var writer = resp.getWriter()){
            if(orderBO.deleteOrder(id, connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                writer.println("Order deleted");
                logger.info("Order deleted");
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                writer.println("Order not deleted");
                logger.info("customer not deleted");
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }
}
