package lk.ijse.vihangielectronics_ijse_76.bo.custom;

import lk.ijse.vihangielectronics_ijse_76.bo.SuperBO;
import lk.ijse.vihangielectronics_ijse_76.dto.ProductDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    public  boolean saveProduct(ProductDto product)throws SQLException,ClassNotFoundException;
    public boolean updateProduct(ProductDto product)throws SQLException ,ClassNotFoundException;
    public boolean deleteProduct(String productId)throws SQLException ,ClassNotFoundException;
    public ArrayList<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException;
    public ProductDto searchProduct(String id) throws SQLException, ClassNotFoundException;
    public String nextProductId() throws SQLException, ClassNotFoundException;
    public boolean stockUpdate(String itemId, int quantity) throws SQLException, ClassNotFoundException;
}
