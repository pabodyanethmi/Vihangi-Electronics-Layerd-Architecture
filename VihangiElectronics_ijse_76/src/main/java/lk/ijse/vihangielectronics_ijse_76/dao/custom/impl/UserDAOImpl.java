package lk.ijse.vihangielectronics_ijse_76.dao.custom.impl;

import lk.ijse.vihangielectronics_ijse_76.dao.CrudUtil;
import lk.ijse.vihangielectronics_ijse_76.dao.custom.UserDAO;
import lk.ijse.vihangielectronics_ijse_76.dto.UserDto;
import lk.ijse.vihangielectronics_ijse_76.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user ( userName, password, jobRoll, status,email) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                //userDTO.getUser_Id(),
                user.getUserName(),
                user.getPassword(),
                user.getStatus(),
                user.getEmail(),
                user.getJobRoll()
        );
    }

    public boolean update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET userName=?, password=?, jobRoll=?, status=? WHERE email=?";
        return CrudUtil.execute(
                sql,
                user.getUserName(),
                user.getPassword(),
                user.getJobRoll(),
                user.getStatus(),
                user.getEmail()
        );
    }

    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM User WHERE user_id=?";
        return CrudUtil.execute(sql, userId);
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public User search(String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM User WHERE user_id=?";
        ResultSet rs = CrudUtil.execute(sql, userId);

        if (rs.next()) {
            return new User(
                    rs.getString("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("job_role"),
                    rs.getString("status")
            );
        }
        return null;
    }

    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM User");
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            userList.add(new User(
                    rs.getString("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("job_role"),
                    rs.getString("status")
            ));
        }
        return userList;
    }
}
