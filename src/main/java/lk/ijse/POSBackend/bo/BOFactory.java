package lk.ijse.POSBackend.bo;

import lk.ijse.POSBackend.bo.custom.impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMERS,ITEMS,ORDERS
    }

    public SuperBO getBO(BOTypes boType) {
        switch (boType) {
            case CUSTOMERS:
                return new CustomerBOImpl();
            default: return null;
        }
    }
}
