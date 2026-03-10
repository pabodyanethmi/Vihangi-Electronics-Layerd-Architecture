package lk.ijse.vihangielectronics_ijse_76.bo.custom;

import lk.ijse.vihangielectronics_ijse_76.bo.SuperBO;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDto;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    boolean placeOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;
    String getLastOrderId() throws SQLException, ClassNotFoundException;
}
