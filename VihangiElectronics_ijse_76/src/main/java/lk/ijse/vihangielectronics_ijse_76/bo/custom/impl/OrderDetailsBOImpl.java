package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.OrderDetailsBO;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.OrderDetailsDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.OrderDetailsDto;
import lk.ijse.vihangielectronics_ijse_76.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean saveOrderDetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {

        OrderDetails orderDetails = new OrderDetails(
                dto.getOrder_id(),
                dto.getItem_id(),
                dto.getQuantity(),
                dto.getUnit_price()
        );

        return orderDetailsDAO.save(orderDetails);
    }

    @Override
    public ArrayList<OrderDetailsDto> getAllOrderDetails() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetails> list = orderDetailsDAO.getAll();
        ArrayList<OrderDetailsDto> dtoList = new ArrayList<>();

        for (OrderDetails orderDetails : list) {

            dtoList.add(new OrderDetailsDto(
                    orderDetails.getOrder_id(),
                    orderDetails.getItem_id(),
                    orderDetails.getQuantity(),
                    orderDetails.getUnit_price()
            ));
        }

        return dtoList;
    }
}
