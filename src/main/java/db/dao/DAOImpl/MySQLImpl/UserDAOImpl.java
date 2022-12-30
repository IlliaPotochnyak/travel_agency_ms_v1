package db.dao.DAOImpl.MySQLImpl;

import db.DataSource;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAllUsers() throws DatabaseException {
        return null;
    }

    @Override
    public User getUserByEmail(String email) throws DatabaseException {
        String query = "SELECT * FROM user WHERE email=?";
        User user = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();


            if (rs.next()) {
                //String first_name, String last_name, String email, String password, String phone, String role
                user = new User( rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getInt("role_id")
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void addUser(User user) throws DatabaseException {

    }

    @Override
    public void blockOrUnblockUserByIdAndParam(int id, boolean isBlock) throws DatabaseException {

    }
}
