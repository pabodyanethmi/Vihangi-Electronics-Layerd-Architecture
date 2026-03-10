package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.OrderDetailsDAO;
import lk.ijse.vihangielectronics_ijse_76.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Order_Details (order_id, item_id, quantity, unit_price) VALUES (?,?,?,?)";

        return CrudUtil.execute(
                sql,
                dto.getOrder_id(),
                dto.getItem_id(),
                dto.getQuantity(),
                dto.getUnit_price()
        );
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Order_Details SET quantity=?, unit_price=? WHERE order_id=? AND item_id=?";

        return CrudUtil.execute(
                sql,
                dto.getQuantity(),
                dto.getUnit_price(),
                dto.getOrder_id(),
                dto.getItem_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM Order_Details WHERE order_id=?";
        return CrudUtil.execute(sql, id);
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {

        ResultSet rs = CrudUtil.execute("SELECT MAX(order_id) FROM Order_Details");

        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM Order_Details");
        ArrayList<OrderDetails> list = new ArrayList<>();

        while (rs.next()) {

            list.add(new OrderDetails(
                    rs.getString("order_id"),
                    rs.getString("item_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("unit_price")
            ));
        }

        return list;
    }

    @Override
    public OrderDetails search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM Order_Details WHERE order_id=?",
                id
        );

        if (rs.next()) {
            return new OrderDetails(
                    rs.getString("order_id"),
                    rs.getString("item_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("unit_price")
            );
        }

        return null;
    }
}
