package lk.ijse.POSBackend.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.POSBackend.bo.BOFactory;
import lk.ijse.POSBackend.bo.custom.LoginBO;
import lk.ijse.POSBackend.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    static Logger logger =  LoggerFactory.getLogger(LoginController.class);
    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    Connection connection;

    @Override
    public void init() {
        logger.info("Initializing LoginController with call init method");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/userLogin");
            this.connection =  pool.getConnection();
        }catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Logging");
        // Persist Data
        try (var writer = resp.getWriter()){
            var name = req.getParameter("name");
            var password = req.getParameter("password");
            boolean isSaved = loginBO.login(name, password, connection);
            logger.info(String.valueOf(isSaved));
            if (isSaved){
                logger.info("Logged Successfully");
                writer.println("Logged Successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                logger.info("Cannot log in");
                writer.println("Cannot log in");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }
}
