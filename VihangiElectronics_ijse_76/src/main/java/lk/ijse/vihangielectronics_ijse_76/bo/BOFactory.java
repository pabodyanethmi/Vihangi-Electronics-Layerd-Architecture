package lk.ijse.vihangielectronics_ijse_76.bo;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory instance;
    private BOFactory() {

    }
    public static BOFactory getInstance() {
        return instance ==null?new BOFactory():instance;
    }
    public enum BOType{
        CUSTOMER, PAYMENT, PRODUCT, STOCK, SUPPLIER, USER, ORDER, ORDERDETAILS
    }
    public SuperBO getBO(BOType type){
        switch(type){
            case CUSTOMER:
                return new CustomerBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDERDETAILS:
                return new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
