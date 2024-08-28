package lk.ijse.POSBackend.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.POSBackend.bo.BOFactory;
import lk.ijse.POSBackend.bo.custom.RegistrationBO;
import lk.ijse.POSBackend.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/register")
public class RegistrationController extends HttpServlet {
    static Logger logger =  LoggerFactory.getLogger(RegistrationController.class);
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERS);

    Connection connection;

    @Override
    public void init() {
        logger.info("Initializing RegistrationController with call init method");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/userRegistration");
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
        logger.info("Adding Users");
        // Persist Data
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var userDTO = jsonb.fromJson(req.getReader(), UserDTO.class);
            logger.info(userDTO.toString());
            boolean isSaved = registrationBO.saveUser(userDTO, connection);
            if (isSaved){
                logger.info("User saved");
                writer.println("User saved");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                logger.info("User not saved");
                writer.println("User not saved");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

}
