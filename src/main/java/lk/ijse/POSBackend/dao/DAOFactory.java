package lk.ijse.POSBackend.dao;

import lk.ijse.POSBackend.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMERS, PRODUCTS, ORDERS
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case CUSTOMERS:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
