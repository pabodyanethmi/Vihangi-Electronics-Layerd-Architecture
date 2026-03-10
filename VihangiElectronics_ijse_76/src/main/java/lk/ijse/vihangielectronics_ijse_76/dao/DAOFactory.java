package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, PAYMENT, PRODUCT, STOCK, SUPPLIER, USER, ORDER, ORDERDETAILS
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
