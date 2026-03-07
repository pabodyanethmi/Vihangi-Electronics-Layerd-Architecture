package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T dto) throws SQLException, ClassNotFoundException;
    public boolean update(T dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String getLastId() throws SQLException, ClassNotFoundException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException;
}