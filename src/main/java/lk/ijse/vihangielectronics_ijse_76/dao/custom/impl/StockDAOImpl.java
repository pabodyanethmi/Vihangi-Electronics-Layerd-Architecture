package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.StockDAO;
import lk.ijse.vihangielectronics_ijse_76.dto.StockDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    public boolean save(StockDto stock) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Stock (productId, stockQuantity) VALUES (?,?)";
        return CrudUtil.execute(
                sql,
                stock.getProductId(),
                stock.getStockQuantity()
        );
    }

    public boolean update(StockDto stock) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Stock SET stockQuantity=? WHERE productId=?";
        return CrudUtil.execute(
                sql,
                stock.getStockQuantity(),
                stock.getProductId()
        );
    }

    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Stock WHERE productId=?";
        return CrudUtil.execute(sql, productId);
    }

    public StockDto search(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Stock WHERE productId=?";
        ResultSet rs = CrudUtil.execute(sql, productId);

        if (rs.next()) {
            return new StockDto(
                    rs.getInt("stockId"),
                    rs.getString("productId"),
                    rs.getInt("stockQuantity")
            );
        }
        return null;
    }
    public ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Stock ORDER BY stockId DESC";
        ResultSet rs = CrudUtil.execute(sql);

        ArrayList<StockDto> stockList = new ArrayList<>();

        while (rs.next()) {
            StockDto dto = new StockDto(
                    rs.getInt("stockId"),
                    rs.getString("productId"),
                    rs.getInt("stockQuantity")
            );
            stockList.add(dto);
        }
        return stockList;
    }
   public String getLastId() throws SQLException, ClassNotFoundException {
       ResultSet rs = CrudUtil.execute("SELECT stockId FROM Stock ORDER BY stockId DESC LIMIT 1");

       if (rs.next()) {
           int lastId = rs.getInt(1);
           return String.valueOf(lastId + 1);
       }
       return "1";
   }
}
