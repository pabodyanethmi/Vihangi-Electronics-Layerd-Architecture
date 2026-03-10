package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.PaymentDAO;
import lk.ijse.vihangielectronics_ijse_76.dto.PaymentDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    public boolean save(Payment payment)throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus()
        );
    }

    public boolean update(Payment payment)throws SQLException, ClassNotFoundException {
        String sql = "UPDATE payment SET order_id=?, date=?, amount=?, payment_method=?, status=? WHERE payment_id=?";
        return CrudUtil.execute(
              sql,
                payment.getOrderId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getPaymentId(),
                payment.getPaymentId()
        );
    }

    public boolean delete(String id)throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE payment_id=?";
        return CrudUtil.execute(
                sql, id
        );

    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public Payment search(String paymentId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment WHERE payment_id = ?";
        ResultSet rs = CrudUtil.execute(sql, paymentId);

        if (rs.next()) {
            return new Payment(
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

    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment";
        ResultSet rs = CrudUtil.execute(sql);

        ArrayList<Payment> paymentList = new ArrayList<>();

        while (rs.next()) {
            paymentList.add(new Payment(
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
