package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.ProductBO;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.ProductDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.ProductDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.ProductDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);
    @Override
    public boolean saveProduct(ProductDto save) throws SQLException, ClassNotFoundException {
        Product product = new Product(save.getProductId(), save.getSupplierId(),
                save.getName(), save.getPricePerUnit(), save.getQuantity());
        return productDAO.save(product);
    }

    @Override
    public boolean updateProduct(ProductDto update) throws SQLException, ClassNotFoundException {
        Product product = new Product(update.getProductId(), update.getSupplierId(),
                update.getName(), update.getPricePerUnit(), update.getQuantity());
        return productDAO.update(product);
    }

    @Override
    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productId);
    }

    @Override
    public ArrayList<ProductDto> getAllProducts() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = productDAO.getAll();
        ArrayList<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            ProductDto dto = new ProductDto(
                    product.getProductId(),
                    product.getSupplierId(),
                    product.getName(),
                    product.getPricePerUnit(),
                    product.getQuantity()
            );
            productDtos.add(dto);
        }
        return productDtos;
    }

    @Override
    public ProductDto searchProduct(String id) throws SQLException, ClassNotFoundException {
        Product product = productDAO.search(id);
        if (product != null) {
            return new ProductDto(
                    product.getProductId(),
                    product.getSupplierId(),
                    product.getName(),
                    product.getPricePerUnit(),
                    product.getQuantity()
            );
        }
        return null;
    }

    @Override
    public String nextProductId() throws SQLException, ClassNotFoundException {
        return productDAO.getLastId();
    }

    @Override
    public boolean stockUpdate(String productId, int quantity) throws SQLException, ClassNotFoundException {
        Product product = productDAO.search(productId);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            return productDAO.update(product);
        }
        return false;
    }
}
