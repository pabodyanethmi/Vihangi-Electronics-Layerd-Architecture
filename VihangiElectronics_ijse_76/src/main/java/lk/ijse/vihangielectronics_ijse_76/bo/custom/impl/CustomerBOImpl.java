package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.CustomerBO;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.CustomerDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.CustomerDto;
import lk.ijse.vihangielectronics_ijse_76.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDto save) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(save.getCustomerId(),save.getName(),save.getContact(),save.getEmail(),save.getAddress());
        return customerDAO.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDto update) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(update.getName(),update.getContact(),update.getEmail(),update.getAddress(),update.getCustomerId());
        return customerDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String getLastCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getLastId();
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDto dto = new CustomerDto();
            dto.setCustomerId(customer.getCustomerId());
            dto.setName(customer.getName());
            dto.setContact(customer.getContact());
            dto.setEmail(customer.getEmail());
            dto.setAddress(customer.getAddress());
            customerDtos.add(dto);

        }
        return customerDtos;
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        if (customer != null) {
            return new CustomerDto(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getEmail(),
                    customer.getAddress()
            );
        }
        return null;
    }
}
