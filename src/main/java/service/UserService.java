package service;

import DTO.UserDTO;
import com.mysql.cj.util.StringUtils;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;
import util.PWHash;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    private int noOfRecords;
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public boolean add(UserDTO userDTO) {

//        UserDAO userDAO = new UserDAOImpl();
        if (userDTO.getActive() == 0) {
            userDTO.setActive(1);
        }
        if (StringUtils.isNullOrEmpty(userDTO.getRole())) {
            userDTO.setRole("client");
        }
        User user;
        try {
            user = new User(
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getEmail(),
                    PWHash.hashPassword(userDTO.getPassword()),
                    userDTO.getPhone(),
                    userDTO.getActive(),
                    userDTO.getRole()
            );
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

        try {
            return userDAO.addUser(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getByEmail(String email) {
//        System.out.println("getByEmail");
//         UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.getUserByEmail(email);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDTO> getAll(int offset, int noOfRecords) {
        List<UserDTO> userDTOList = new ArrayList<>();

//        UserDAOImpl userDAO = new UserDAOImpl();

        try {
            List<User> userList = userDAO.getAllUsers(offset, noOfRecords);
            userList.forEach(
                    user -> {
                        UserDTO userDTO = getUserDTOFromUser(user);
                        userDTOList.add(userDTO);
                    }
            );

            this.noOfRecords = userDAO.getNoOfRecords();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return userDTOList;

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
//        System.out.println("loginUser method");

        User user = getByEmail(email);
        UserDTO userDTO = new UserDTO();

        if (user != null) {
            if (user.getPassword().equals(PWHash.hashPassword(password))) {
                userDTO.setId(user.getId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                userDTO.setEmail(user.getEmail());
                userDTO.setPhone(user.getPhone());
                userDTO.setRole(user.getRole());
                userDTO.setActive(user.getActive());

                return userDTO;
            }
        }
        return null;
    }

    public boolean blockOrUnblockUser (int id, int isBlock){
//        UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.blockOrUnblockUserByIdAndParam(id, isBlock);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private UserDTO getUserDTOFromUser(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setActive(user.getActive());
        userDTO.setRole(user.getRole());

        return userDTO;
    }
}
