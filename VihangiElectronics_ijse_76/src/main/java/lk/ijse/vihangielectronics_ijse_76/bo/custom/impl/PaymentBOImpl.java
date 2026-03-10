package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.PaymentBO;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.PaymentDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.PaymentDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean savePayment(PaymentDto save) throws SQLException, ClassNotFoundException {
        Payment payment = new Payment(save.getPaymentId(),save.getOrderId(),save.getDate(),save.getAmount(),save.getPaymentMethod(),save.getStatus());
        return paymentDAO.save(payment);
    }

    @Override
    public boolean updatePayment(PaymentDto update) throws SQLException, ClassNotFoundException {
        Payment payment = new Payment(update.getPaymentId(),update.getOrderId(),update.getDate(),update.getAmount(),update.getPaymentMethod(),update.getStatus());
        return paymentDAO.update(payment);
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDto searchPayment(String paymentId) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.search(paymentId);
        if (payment != null) {
            return new PaymentDto(
                    payment.getPaymentId(),
                    payment.getOrderId(),
                    payment.getDate(),
                    payment.getAmount(),
                    payment.getPaymentMethod(),
                    payment.getStatus()
            );
        }
        return null;
    }

    @Override
    public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentDto dto = new PaymentDto(
                    payment.getPaymentId(),
                    payment.getOrderId(),
                    payment.getDate(),
                    payment.getAmount(),
                    payment.getPaymentMethod(),
                    payment.getStatus()
            );
            paymentDtos.add(dto);
        }
        return paymentDtos;
    }

    @Override
    public boolean isPaymentExists(String paymentId) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.search(paymentId);
        return payment != null;
    }
}
