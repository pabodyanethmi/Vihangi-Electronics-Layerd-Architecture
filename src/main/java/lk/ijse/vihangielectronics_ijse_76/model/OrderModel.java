package lk.ijse.vihangielectronics_ijse_76.model;

import javafx.collections.ObservableList;
import lk.ijse.vihangielectronics_ijse_76.db.DBConnection;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDetailsDto;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDto;
import lk.ijse.vihangielectronics_ijse_76.dto.tm.CartTM;
import lk.ijse.vihangielectronics_ijse_76.util.CrudUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderModel {

    private final OrderDetailsModel orderDetailsModel = new OrderDetailsModel();
    private final ProductModel productModel = new ProductModel();
    private final PaymentModel paymentModel = new PaymentModel();

    public OrderModel() throws SQLException {
    }

    // ================= SAVE ORDER =================
    public boolean orderSave(OrderDto orderDto)
            throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Orders(order_id, order_date, time, total_amount, cus_id) " +
                "VALUES (?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                orderDto.getOrder_id(),
                orderDto.getOrder_date(),
                orderDto.getTime(),
                orderDto.getTotal_amount(),
                orderDto.getCus_id()
        );
    }

    // ================= PLACE ORDER =================
    public boolean placeOrder(OrderDto orderDto)
            throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderSave(orderDto);
            if (!isOrderSaved) {
                connection.rollback();
                return false;
            }

            for (OrderDetailsDto detail : orderDto.getCartList()) {

                // set order id
                detail.setOrder_id(orderDto.getOrder_id());

                boolean isDetailSaved =
                        orderDetailsModel.saveOrderDetails(detail);
                if (!isDetailSaved) {
                    connection.rollback();
                    return false;
                }

                boolean isStockUpdated =
                        productModel.stockUpdate(
                                detail.getItem_id(),
                                detail.getQuantity()
                        );
                if (!isStockUpdated) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    // ================= GET LAST ORDER ID =================
    public String getLastOrderId()
            throws SQLException, ClassNotFoundException {

        ResultSet resultSet =
                CrudUtil.execute("SELECT MAX(order_id) FROM Orders");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    Connection conn = DBConnection.getInstance().getConnection();
/*
    // Step 01
    InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/supermarket_hdse_76/reports/invoice.jrxml");

    // Step 02
    JasperReport jr = JasperCompileManager.compileReport(reportObject); // this method thorws JRException

    // Step 03

        *//*
        > There are can be more than one parameter (e.g. ORDER_ID)

        > List

        > Key-Value
          e.g. ORDER_ID:21, CUSTOMER_ID:25

        [ORDER_ID:21, CUSTOMER_ID:25, CUSTOMER_NAME:Kavindu]
        *//*

    Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderId);

    JasperPrint jp = JasperFillManager.fillReport(jr, params, conn); // fillReport(japerreport, params, connection_obj)

    // Step 04
        JasperViewer.viewReport(jp, false);

}*/

}
