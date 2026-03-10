package lk.ijse.vihangielectronics_ijse_76.bo.custom;

import lk.ijse.vihangielectronics_ijse_76.bo.SuperBO;
import lk.ijse.vihangielectronics_ijse_76.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public String getLastCustomerId() throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;
}