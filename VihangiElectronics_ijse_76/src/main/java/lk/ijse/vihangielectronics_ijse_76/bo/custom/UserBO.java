package lk.ijse.vihangielectronics_ijse_76.bo.custom;

import lk.ijse.vihangielectronics_ijse_76.bo.SuperBO;
import lk.ijse.vihangielectronics_ijse_76.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public boolean saveUser(UserDto userDTO) throws SQLException, ClassNotFoundException;
    public boolean updateUser(UserDto userDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException;
    public UserDto SearchUser(String userId) throws SQLException, ClassNotFoundException;
    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException;
}
