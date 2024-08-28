package lk.ijse.POSBackend.bo;

import lk.ijse.POSBackend.bo.custom.LoginBO;
import lk.ijse.POSBackend.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        LOGIN,USERS, CUSTOMERS,PRODUCTS,ORDERS
    }

    public SuperBO getBO(BOTypes boType) {
        switch (boType) {
            case LOGIN:
                return new LoginBOImpl();
            case USERS:
                return new RegistrationBOImpl();
            case CUSTOMERS:
                return new CustomerBOImpl();
            case PRODUCTS:
                return new ProductBOImpl();
            case ORDERS:
                return new OrderBOImpl();
            default: return null;
        }
    }
}
