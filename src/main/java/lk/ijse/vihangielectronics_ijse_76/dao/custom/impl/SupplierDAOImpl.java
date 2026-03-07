package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.SupplierDAO;
import lk.ijse.vihangielectronics_ijse_76.dto.SupplierDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean save(SupplierDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Supplier (supplierId, name, contact, email, address) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                dto.getSupplierId(),
                dto.getName(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress()
        );
    }

    public boolean update(SupplierDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Supplier SET name=?, contact=?, email=?, address=? WHERE supplierId=?";
        return CrudUtil.execute(
                sql,
                dto.getName(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getSupplierId()
        );
    }

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Supplier WHERE supplierId=?";
        return CrudUtil.execute(sql, supplierId);
    }

    public SupplierDto search(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Supplier WHERE supplierId=?";
        ResultSet rs = CrudUtil.execute(sql, supplierId);

        if (rs.next()) {
            return new SupplierDto(
                    rs.getString("supplierId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
            );
        }
        return null;
    }

    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Supplier";
        ResultSet rs = CrudUtil.execute(sql);

        ArrayList<SupplierDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new SupplierDto(
                    rs.getString("supplierId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
            ));
        }
        return list;

    }

    public String getLastId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT supplierId FROM Supplier ORDER BY supplierId DESC LIMIT 1";
        ResultSet rs = CrudUtil.execute(sql);

        if (rs.next()) {
            return rs.getString("supplierId");
        }
        return null;
    }
}


