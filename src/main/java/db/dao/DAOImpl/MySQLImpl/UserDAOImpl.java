package db.dao.DAOImpl.MySQLImpl;

import db.DataSource;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static constant.MySQLConstant.*;

public class UserDAOImpl implements UserDAO {

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public List<User> getAllUsers(int offset, int noOfRecords) throws DatabaseException {
//        System.out.println("getAllUsers");
        List<User> userList = new ArrayList<>();
//        String query = "SELECT * FROM receipt;";
//        String query = "SELECT SQL_CALC_FOUND_ROWS user.id, user.first_name, user.last_name, user.email, " +
//                "user.password, user.phone, user.active, role.role " +
//                "FROM user INNER JOIN role ON user.role_id=role.id " +
//                "ORDER BY user.id limit "
//                + offset + ", " + noOfRecords;
        String query = GET_ALL_USERS + offset + ", " + noOfRecords;
        try (Connection con = DataSource.getConnection();
             Statement stmnt = con.createStatement()
        ){
            ResultSet rs = stmnt.executeQuery(query);
//            System.out.println("ResultSet " + rs);
            while (rs.next()) {
                //int id, String firstName, String lastLame, String email, String phone, int active, String role
//                System.out.println("user_id = " + rs.getInt("id"));
//                        + "-" + rs.getInt("user_id")
//                        + "-" + rs.getInt("discount")
//                );
                User user = new User( rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("active"),
                        rs.getString("role")
                );
//                System.out.println("Receipt - " + receipt);
                userList.add(user);
            }
            rs.close();

            rs = stmnt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
            userList.forEach(System.out::println);

        } catch (SQLException e) {
//            System.out.println("ReceiptDao error");
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public User getUserByEmail(String email) throws DatabaseException {
//        String query = "SELECT user.id, user.first_name, user.last_name, user.email, user.password, user.phone, role.role \n" +
//                "FROM user INNER JOIN role ON user.role_id=role.id WHERE email=?";
        String query = GET_USER_BY_EMAIL;
        User user = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();
//            System.out.println(rs);
            if (rs.next()) {
                //String first_name, String last_name, String email, String password, String phone, String role
                user = new User( rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("role")
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) throws DatabaseException {
        boolean result = false;
//        String query = "INSERT INTO user (first_name, last_name, email, password, phone, active, role_id)" +
//                "VALUES (?, ?, ?, ?, ?, ?, (SELECT role.id FROM role WHERE role.role=?))";
        String query = ADD_USER;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setString(1, user.getFirstName());
            pstmnt.setString(2, user.getLastName());
            pstmnt.setString(3, user.getEmail());
            pstmnt.setString(4, user.getPassword());
            pstmnt.setString(5, user.getPhone());
            pstmnt.setInt(6, user.getActive());
            pstmnt.setString(7, user.getRole());
            pstmnt.executeUpdate();

            result = true;
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e);

        }
        return result;
    }

    @Override
    public boolean blockOrUnblockUserByIdAndParam(int id, int isBlock) throws DatabaseException {
//        System.out.println("blockOrUnblockUserByIdAndParam");

        boolean result = false;
//        String query = "UPDATE user SET active=? WHERE id=?;";
        String query = SET_USER_ACTIVE_FIELD;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setInt(1, isBlock);
            pstmnt.setInt(2, id);

            pstmnt.executeUpdate();

            result = true;
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e);

        }
        return result;
    }
}
