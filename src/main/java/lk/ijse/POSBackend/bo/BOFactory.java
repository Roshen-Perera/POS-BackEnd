package lk.ijse.POSBackend.bo;

import lk.ijse.POSBackend.bo.custom.impl.CustomerBOImpl;
import lk.ijse.POSBackend.bo.custom.impl.ProductBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMERS,PRODUCTS,ORDERS
    }

    public SuperBO getBO(BOTypes boType) {
        switch (boType) {
            case CUSTOMERS:
                return new CustomerBOImpl();
            case PRODUCTS:
                return new ProductBOImpl();
            default: return null;
        }
    }
}
