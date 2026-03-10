package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.OrderBO;
import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.OrderDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.OrderDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.db.DBConnection;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean placeOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            boolean isOrderSaved = orderDAO.save(
                    new Order(
                            orderDto.getOrder_id(),
                            orderDto.getOrder_date(),
                            orderDto.getTime(),
                            orderDto.getTotal_amount(),
                            orderDto.getCus_id()
                    )
            );

            if (!isOrderSaved) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (Exception e) {

            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {

        ResultSet rs = CrudUtil.execute("SELECT MAX(order_id) FROM Orders");

        if (rs.next()) {
            return rs.getString(1);
        }

        return null;
    }
}
