package lk.ijse.POSBackend.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.POSBackend.bo.BOFactory;
import lk.ijse.POSBackend.bo.custom.CustomerBO;
import lk.ijse.POSBackend.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet{
    static Logger logger =  LoggerFactory.getLogger(CustomerController.class);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMERS);
    // CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERS);

    Connection connection;

    @Override
    public void init() {
        logger.info("Initializing CustomerController with call init method");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/cusRegistration");
            this.connection =  pool.getConnection();
        }catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("id") != null)  {
            var customerId = req.getParameter("id");
            try (var writer = resp.getWriter()){
                var customer = customerBO.getCustomer(customerId, connection);
                System.out.println(customer);
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(customer,writer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try (var writer = resp.getWriter()) {
                List<CustomerDTO> customerDTOList = customerBO.getAllCustomers(connection);
                if (customerDTOList != null) {
                    resp.setContentType("application/json");
                    Jsonb jsonb = JsonbBuilder.create();
                    jsonb.toJson(customerDTOList, writer);
                } else {
                    writer.write("No customers found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("Adding Customers");
        // Persist Data
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            logger.info(customerDTO.toString());
            boolean isSaved = customerBO.saveCustomer(customerDTO, connection);
            if (isSaved){
                logger.info("Customer saved");
                writer.println("Customer saved");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                logger.info("Customer not saved");
                writer.println("Customer not saved");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        logger.info("Patching Customers");
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            if(customerBO.updateCustomer(customerDTO, connection)){
                logger.info("Customer updated");
                writer.println("Customer updated");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                logger.info("Customer not updated");
                writer.println("Customer not updated");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var stuId = req.getParameter("id");
        logger.info("Deleting Customer");
        try (var writer = resp.getWriter()){
            if(customerBO.deleteCustomer(stuId, connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                writer.println("Customer deleted");
                logger.info("Customer deleted");
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                writer.println("Customer not deleted");
                logger.info("customer not deleted");
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }
}
