package service;

import DTO.UserDTO;
import com.mysql.cj.util.StringUtils;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import entities.Tour;
import entities.User;
import exceptions.DatabaseException;

import java.util.List;

public class UserService implements IUserService{
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public boolean add(UserDTO userDTO) {

        UserDAO userDAO = new UserDAOImpl();
        if (userDTO.getActive() == 0) {
            userDTO.setActive(1);
        }
        if (StringUtils.isNullOrEmpty(userDTO.getRole())) {
            userDTO.setRole("client");
        }

        User user =new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getPhone(),
                userDTO.getActive(),
                userDTO.getRole()
        );

        try {
            return userDAO.addUser(user);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public List<UserDTO> getAll(int offset, int noOfRecords) {
        return null;
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public boolean blockOrUnblockUser (int id, int isBlock){
        return false;
    }
}
