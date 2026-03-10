package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.StockBO;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.StockDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.StockDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.ProductDto;
import lk.ijse.vihangielectronics_ijse_76.dto.StockDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Product;
import lk.ijse.vihangielectronics_ijse_76.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public boolean saveStock(StockDto save) throws SQLException, ClassNotFoundException {
        Stock stock = new Stock(save.getStockId(),save.getProductId(),save.getStockQuantity());
        return stockDAO.save(stock);
    }

    @Override
    public boolean updateStock(StockDto update) throws SQLException, ClassNotFoundException {
        Stock stock = new Stock(update.getStockId(),update.getProductId(),update.getStockQuantity());
        return stockDAO.update(stock);
    }

    @Override
    public boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(stockId);
    }

    @Override
    public StockDto searchStock(String stockId) throws SQLException, ClassNotFoundException {
        Stock stock = stockDAO.search(stockId);
        if (stock != null) {
            return new StockDto(
                    stock.getStockId(),
                    stock.getProductId(),
                    stock.getStockQuantity()
            );
        }
        return null;
    }

    @Override
    public ArrayList<StockDto> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> stocks = stockDAO.getAll();
        ArrayList<StockDto> stockDtos = new ArrayList<>();

        for (Stock stock : stocks) {
            StockDto dto = new StockDto(
                    stock.getStockId(),
                    stock.getProductId(),
                    stock.getStockQuantity()
            );
            stockDtos.add(dto);
        }
        return stockDtos;
    }

    @Override
    public String generateNextStockId() throws SQLException, ClassNotFoundException {
        return stockDAO.getLastId();
    }
}
