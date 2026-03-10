package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.SupplierBO;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.SupplierDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.SupplierDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {

        Supplier supplier = new Supplier(
                dto.getSupplierId(),
                dto.getName(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress()
        );

        return supplierDAO.save(supplier);
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {

        Supplier supplier = new Supplier(
                dto.getSupplierId(),
                dto.getName(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress()
        );

        return supplierDAO.update(supplier);
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public SupplierDto searchSupplier(String supplierId) throws SQLException, ClassNotFoundException {

        Supplier supplier = supplierDAO.search(supplierId);

        if (supplier != null) {
            return new SupplierDto(
                    supplier.getSupplierId(),
                    supplier.getName(),
                    supplier.getContact(),
                    supplier.getEmail(),
                    supplier.getAddress()
            );
        }

        return null;
    }

    @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {

        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers) {

            SupplierDto dto = new SupplierDto(
                    supplier.getSupplierId(),
                    supplier.getName(),
                    supplier.getContact(),
                    supplier.getEmail(),
                    supplier.getAddress()
            );

            supplierDtos.add(dto);
        }

        return supplierDtos;
    }

    @Override
    public String getLastSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getLastId();
    }
}
