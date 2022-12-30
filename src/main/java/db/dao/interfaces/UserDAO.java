package db.dao.interfaces;

import entities.User;
import exceptions.DatabaseException;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws DatabaseException;
    User getUserByEmail(String email) throws DatabaseException;
    void addUser(User user) throws DatabaseException;
    void blockOrUnblockUserByIdAndParam(int id, boolean isBlock) throws DatabaseException;
}
