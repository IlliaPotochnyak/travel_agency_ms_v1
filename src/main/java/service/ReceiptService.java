package service;

import DTO.ReceiptDTO;
import DTO.TourDTO;
import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.interfaces.ReceiptDao;
import entities.Receipt;
import entities.Tour;
import entities.User;

import java.sql.SQLException;
import java.util.List;

public class ReceiptService implements IReceiptService{
    @Override
    public boolean add(ReceiptDTO receiptDTO) {
        System.out.println("add method");

        ReceiptDao receiptDao = new ReceiptDAOImpl();

        TourService tourService = new TourService();
        TourDTO tour = tourService.getById(receiptDTO.getTourId());

        Receipt receipt = new Receipt(receiptDTO.getTourId(),
                receiptDTO.getUserId(), 0,
                tour.getPrice(),
                "registered");

        try {
            return receiptDao.addReceipt(receipt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> getAllByUserId(int id) {
        return null;
    }

    @Override
    public List<UserDTO> getAll(int offset, int noOfRecords) {
        return null;
    }

    @Override
    public boolean updateReceiptStatus(ReceiptDTO receiptDTO) {
        return false;
    }

    @Override
    public boolean updateReceiptDiscount(ReceiptDTO receiptDTO) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
