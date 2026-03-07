package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.PaymentDAO;
import lk.ijse.vihangielectronics_ijse_76.dto.PaymentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
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

    public boolean updatePayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException {
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

    public boolean deletePayment(String id)throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE payment_id=?";
        return CrudUtil.execute(
                sql, id
        );

    }

    public PaymentDto searchPayment(String paymentId)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM payment WHERE payment_id = ?";
        ResultSet rs = CrudUtil.execute(sql, paymentId);

        if (rs.next()) {
            return new PaymentDto(
                    rs.getString("payment_id"),
                    rs.getString("order_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getInt("amount"),
                    rs.getString("payment_method"),
                    rs.getString("status")
            );
        }

        return null;
    }

    public ArrayList<PaymentDto> getAllPayments()
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM payment";
        ResultSet rs = CrudUtil.execute(sql);

        ArrayList<PaymentDto> paymentList = new ArrayList<>();

        while (rs.next()) {
            paymentList.add(new PaymentDto(
                    rs.getString("payment_id"),
                    rs.getString("order_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getInt("amount"),
                    rs.getString("payment_method"),
                    rs.getString("status")
            ));
        }

        return paymentList;
    }

    public boolean isPaymentExists(String paymentId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT payment_id FROM payment WHERE payment_id=?";
        return CrudUtil.execute(sql, paymentId);
    }
}
