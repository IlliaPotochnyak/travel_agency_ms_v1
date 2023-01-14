package service;


import DTO.ReceiptDTO;
import DTO.UserDTO;
import entities.User;

import java.util.List;

public interface IReceiptService {
    boolean add(ReceiptDTO receiptDTO);
    List<UserDTO> getAllByUserId(int id);
    List<UserDTO> getAll(int offset, int noOfRecords);
    boolean updateReceiptStatus(ReceiptDTO receiptDTO);
    boolean updateReceiptDiscount(ReceiptDTO receiptDTO);
    boolean delete(int id);
}
