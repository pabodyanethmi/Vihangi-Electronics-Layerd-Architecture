package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dto.ProductDto;
import lk.ijse.vihangielectronics_ijse_76.model.ProductModel;
import lk.ijse.vihangielectronics_ijse_76.util.CrudUtil;
import org.checkerframework.checker.units.qual.C;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl {
    public static boolean saveProduct(ProductDto product)throws SQLException ,ClassNotFoundException{
        String sql = "INSERT INTO product (productId,name,qty,pricePerUnit) VALUES (?,?,?,?)";
        return CrudUtil.execute(
                sql,
                product.getProductId(),
                product.getName(),
                product.getQuantity(),
                product.getPricePerUnit()

        );

    }

    public boolean updateProduct(ProductDto product)throws SQLException ,ClassNotFoundException{
        String sql = "UPDATE product SET name=?,qty=?,pricePerUnit=? WHERE productId=?";
        return  CrudUtil.execute(
                sql,
                product.getName(),
                product.getQuantity(),
                product.getPricePerUnit(),
                product.getProductId()
        );
    }

    public boolean deleteProduct(ProductDto product)throws SQLException ,ClassNotFoundException{
        String sql = "DELETE FROM product WHERE productId=?";
        return CrudUtil.execute(
                sql,
                product.getProductId()
            );
    }

    public List<ProductDto> getProducts() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM product ORDER BY productId DESC");

        List<ProductDto> productDtoList = new ArrayList<>();

        while (rs.next()) {
            ProductDto productDto = new ProductDto(
                    rs.getString("productId"),
                    rs.getString("supplierId"),
                    rs.getString("name"),
                    rs.getDouble("pricePerUnit"),
                    rs.getInt("qty")
            );
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public ProductDto searchProduct(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM product WHERE productId=?",
                        id
                );

        if (rs.next()) {
            String productId = rs.getString("productId");
            String supplierId = rs.getString("supplierId");
            String name = rs.getString("name");
            int qty = Integer.parseInt(rs.getString("qty"));
            double pricePerUnit = rs.getDouble("pricePerUnit");

            return new ProductDto(productId,supplierId, name, pricePerUnit,qty);
        }
        return null;
    }

    public String nextProductId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT productId FROM product ORDER BY productId DESC LIMIT 1"
        );

        if (rs.next()) {
            String lastId = rs.getString(1); // C005
            int number = Integer.parseInt(lastId.substring(1));
            number++;
            return String.format("P%03d", number);
        }
        return "P001";
    }

    public boolean stockUpdate(String itemId, int quantity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE product SET qty = qty - ? WHERE productId = ?";
        return CrudUtil.execute(sql,quantity, itemId);
    }
}
