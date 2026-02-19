package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dto.PaymentDto;
import lk.ijse.vihangielectronics_ijse_76.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl {
    public boolean savePayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                paymentDto.getPaymentId(),
                paymentDto.getOrderId(),
                paymentDto.getDate(),
                paymentDto.getAmount(),
                paymentDto.getPaymentMethod(),
                paymentDto.getStatus()
        );
    }

    public boolean updatePaymentStatus(PaymentDto paymentDto)throws SQLException, ClassNotFoundException {
        String sql = "UPDATE payment SET order_id=?, date=?, amount=?, payment_method=?, status=? WHERE payment_id=?";
        return CrudUtil.execute(
              sql,
              paymentDto.getOrderId(),
              paymentDto.getDate(),
              paymentDto.getAmount(),
              paymentDto.getPaymentMethod(),
              paymentDto.getStatus(),
              paymentDto.getPaymentId(),
              paymentDto.getPaymentId()
        );
    }

    public boolean deletePayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE payment_id=?";
        return CrudUtil.execute(
                sql, paymentDto.getPaymentId()
        );

    }

    public PaymentDto searchPayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment WHERE payment_id=?";
        return CrudUtil.execute(
                sql,
                paymentDto.getPaymentId(),
                paymentDto.getOrderId(),
                paymentDto.getDate(),
                paymentDto.getAmount(),
                paymentDto.getPaymentMethod(),
                paymentDto.getStatus()
        );
    }

    public ArrayList<PaymentDto> getAllPayments(PaymentDto paymentDto)throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment";
        return CrudUtil.execute(
                sql,
                paymentDto.getPaymentId(),
                paymentDto.getOrderId(),
                paymentDto.getDate(),
                paymentDto.getAmount(),
                paymentDto.getPaymentMethod(),
                paymentDto.getStatus()
        );
    }

    public boolean isPaymentExists(String paymentId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT payment_id FROM payment WHERE payment_id=?";
        return CrudUtil.execute(sql, paymentId);
    }
}
