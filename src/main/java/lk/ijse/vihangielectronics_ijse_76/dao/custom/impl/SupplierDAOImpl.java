package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.SupplierDAO;
import lk.ijse.vihangielectronics_ijse_76.dto.SupplierDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Supplier (supplierId, name, contact, email, address) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getEmail(),
                supplier.getAddress()
        );
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Supplier SET name=?, contact=?, email=?, address=? WHERE supplierId=?";
        return CrudUtil.execute(
                sql,
                supplier.getName(),
                supplier.getContact(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getSupplierId()
        );
    }

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Supplier WHERE supplierId=?";
        return CrudUtil.execute(sql, supplierId);
    }

    public Supplier search(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Supplier WHERE supplierId=?";
        ResultSet rs = CrudUtil.execute(sql, supplierId);

        if (rs.next()) {
            return new Supplier(
                    rs.getString("supplierId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
            );
        }
        return null;
    }

    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Supplier";
        ResultSet rs = CrudUtil.execute(sql);

        ArrayList<Supplier> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Supplier(
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


