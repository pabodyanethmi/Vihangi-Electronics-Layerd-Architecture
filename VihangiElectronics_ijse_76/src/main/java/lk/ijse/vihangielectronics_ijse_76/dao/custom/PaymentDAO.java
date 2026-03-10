package lk.ijse.vihangielectronics_ijse_76.dao.custom;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudDAO;
import lk.ijse.vihangielectronics_ijse_76.dto.PaymentDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    /*public boolean savePayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException ;
    public boolean updatePayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException ;
    public boolean deletePayment(String id)throws SQLException, ClassNotFoundException ;
    public PaymentDto searchPayment(String paymentId) throws SQLException, ClassNotFoundException ;
    public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException;*/
    public boolean isPaymentExists(String paymentId) throws SQLException, ClassNotFoundException ;
}