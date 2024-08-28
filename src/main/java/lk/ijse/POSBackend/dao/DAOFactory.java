/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dao;

import lk.ijse.POSBackend.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        REGISTRATION, LOGIN, CUSTOMERS, PRODUCTS, ORDERS
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case REGISTRATION:
                return new RegistrationDAOImpl();
                case LOGIN:
                    return new LoginDAOImpl();
            case CUSTOMERS:
                return new CustomerDAOImpl();
            case PRODUCTS:
                return new ProductDAOImpl();
            case ORDERS:
                return new OrderDAOImpl();
            default:
                return null;
        }
    }
}
