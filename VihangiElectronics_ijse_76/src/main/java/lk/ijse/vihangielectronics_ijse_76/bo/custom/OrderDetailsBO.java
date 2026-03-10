package lk.ijse.vihangielectronics_ijse_76.bo.custom;

import lk.ijse.vihangielectronics_ijse_76.bo.SuperBO;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    boolean saveOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailsDto> getAllOrderDetails() throws SQLException, ClassNotFoundException;
}
