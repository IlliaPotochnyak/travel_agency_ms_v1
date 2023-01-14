package service;


import DTO.UserDTO;

import java.util.List;

public interface IUserService {
    boolean add(UserDTO userDTO);
    Object getById(int id);
    List<UserDTO> getAll(int offset, int noOfRecords);
    boolean update(UserDTO userDTO);
    boolean delete(int id);
}
