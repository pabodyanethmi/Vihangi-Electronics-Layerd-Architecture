package lk.ijse.vihangielectronics_ijse_76.bo.custom;

import lk.ijse.vihangielectronics_ijse_76.bo.SuperBO;
import lk.ijse.vihangielectronics_ijse_76.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public boolean savePayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException ;
    public boolean updatePayment(PaymentDto paymentDto)throws SQLException, ClassNotFoundException ;
    public boolean deletePayment(String id)throws SQLException, ClassNotFoundException ;
    public PaymentDto searchPayment(String paymentId) throws SQLException, ClassNotFoundException ;
    public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException;
    public boolean isPaymentExists(String paymentId) throws SQLException, ClassNotFoundException ;
}
