package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StockDAO {
    public  boolean saveStock(StockDto stock) throws SQLException, ClassNotFoundException;
    public boolean updateStock(StockDto stock) throws SQLException, ClassNotFoundException;
    public boolean deleteStock(StockDto productId) throws SQLException, ClassNotFoundException;
    public StockDto searchStock(String productId) throws SQLException, ClassNotFoundException;
    public ArrayList<StockDto> getAllStocks() throws SQLException, ClassNotFoundException;
    public String generateNextStockId() throws SQLException, ClassNotFoundException;
    }
