package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dto.CustomerDto;
import lk.ijse.vihangielectronics_ijse_76.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl {
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer (customerId, name, contact, email, address) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                dto.getCustomerId(),
                dto.getName(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress()
        );
    }
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Customer SET name=?, contact=?, email=?, address=? WHERE customerId=?";
        return CrudUtil.execute(
                sql,
                dto.getName(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getCustomerId()
        );
    }
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Customer WHERE customerId=?";
        return CrudUtil.execute(sql, id);
    }
    public String getLastCustomerId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1";
        ResultSet rs = CrudUtil.execute(sql);

        if (rs.next()) {
            return rs.getString("customerId");
        }
        return null;
    }

    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer";
        ResultSet rs = CrudUtil.execute(sql);

        ArrayList<CustomerDto> customer = new ArrayList<>();
        while (rs.next()) {
            customer.add(new CustomerDto(
                    rs.getString("customerId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
            ));
        }
        return customer;
    }
    public CustomerDto searchCustomerById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer WHERE customerId=?";
        ResultSet rs = CrudUtil.execute(sql, id);

        if (rs.next()) {
            return new CustomerDto(
                    rs.getString("customerId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
            );
        }
        return null;
    }
}
