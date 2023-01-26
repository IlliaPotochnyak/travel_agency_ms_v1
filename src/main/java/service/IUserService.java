package service;


import DTO.UserDTO;
import db.dao.interfaces.UserDAO;
import entities.User;

import java.util.List;

public interface IUserService {
    boolean add(UserDTO userDTO);
//    User getByEmail(String email);

    User getByEmail(String email);

    List<UserDTO> getAll(int offset, int noOfRecords);
    boolean update(UserDTO userDTO);
    boolean delete(int id);
}
