package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO {
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;
    public SupplierDto searchSupplier(String supplierId) throws SQLException, ClassNotFoundException;
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
    public String getLastSupplierId() throws SQLException, ClassNotFoundException;
}
