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
    public User getByEmail(String email) {
        System.out.println("getByEmail");
         UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.getUserByEmail(email);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
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

    public UserDTO loginUser(String email, String password) {
        System.out.println("loginUser method");

        User user = getByEmail(email);
        UserDTO userDTO= new UserDTO();

        if (user != null) {
            if (user.getPassword().equals(password)) {
                userDTO.setId(user.getId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                userDTO.setEmail(user.getEmail());
                userDTO.setPhone(user.getPhone());
                userDTO.setRole(user.getRole());

                return userDTO;
            }
        }


        return null;
    }

    public boolean blockOrUnblockUser (int id, int isBlock){
        return false;
    }
}
