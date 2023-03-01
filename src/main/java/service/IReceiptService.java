package service;


import DTO.ReceiptDTO;
import DTO.UserDTO;
import entities.User;

import java.util.List;

public interface IReceiptService {
    boolean add(ReceiptDTO receiptDTO);
    List<ReceiptDTO> getAllByUserId(int id, int offset, int noOfRecords);
    List<ReceiptDTO> getAll(int offset, int noOfRecords);
    boolean updateReceiptStatus(ReceiptDTO receiptDTO);
    boolean updateReceiptDiscount(ReceiptDTO receiptDTO);
//    boolean delete(int id);
}
