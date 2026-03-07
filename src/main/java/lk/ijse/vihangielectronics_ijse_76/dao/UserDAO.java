package lk.ijse.vihangielectronics_ijse_76.dao;

import lk.ijse.vihangielectronics_ijse_76.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public boolean saveUser(UserDto userDTO) throws SQLException, ClassNotFoundException;
    public boolean updateUser(UserDto userDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteUser(UserDto userId) throws SQLException, ClassNotFoundException;
    public UserDto SearchUser(String userId) throws SQLException, ClassNotFoundException;
    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException;
}
