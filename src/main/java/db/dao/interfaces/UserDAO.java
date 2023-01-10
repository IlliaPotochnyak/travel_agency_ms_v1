package db.dao.interfaces;

import entities.User;
import exceptions.DatabaseException;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers(int offset, int noOfRecords) throws DatabaseException;
    User getUserByEmail(String email) throws DatabaseException;
    boolean addUser(User user) throws DatabaseException;
    void blockOrUnblockUserByIdAndParam(int id, boolean isBlock) throws DatabaseException;
    int getNoOfRecords();
}
