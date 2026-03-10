package lk.ijse.vihangielectronics_ijse_76.bo.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.bo.custom.UserBO;
import lk.ijse.vihangielectronics_ijse_76.dao.DAOFactory;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.UserDAO;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.impl.UserDAOImpl;
import lk.ijse.vihangielectronics_ijse_76.dto.UserDto;
import lk.ijse.vihangielectronics_ijse_76.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDto userDTO) throws SQLException, ClassNotFoundException {

        User user = new User(
                userDTO.getUserName(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getJobRoll(),
                userDTO.getStatus()
        );

        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDto userDTO) throws SQLException, ClassNotFoundException {

        User user = new User(
                userDTO.getUserName(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getJobRoll(),
                userDTO.getStatus()
        );

        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return userDAO.delete(userId);
    }

    @Override
    public UserDto SearchUser(String userId) throws SQLException, ClassNotFoundException {

        User user = userDAO.search(userId);

        if (user != null) {
            return new UserDto(
                    user.getUserName(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getJobRoll(),
                    user.getStatus()
            );
        }

        return null;
    }

    @Override
    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException {

        List<User> users = userDAO.getAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {

            UserDto dto = new UserDto(
                    user.getUserName(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getJobRoll(),
                    user.getStatus()
            );

            userDtos.add(dto);
        }

        return userDtos;
    }
}
