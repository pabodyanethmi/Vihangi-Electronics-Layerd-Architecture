package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.OrderDAO;
import lk.ijse.vihangielectronics_ijse_76.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders(order_id, order_date, time, total_amount, cus_id) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                order.getOrder_id(),
                order.getOrder_date(),
                order.getTime(),
                order.getTotal_amount(),
                order.getCus_id()
        );
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =
                CrudUtil.execute("SELECT MAX(order_id) FROM Orders");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
