package service;

import DTO.ReceiptDTO;
import DTO.TourDTO;
import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.interfaces.ReceiptDao;
import entities.Receipt;
import entities.Tour;
import entities.User;
import exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptService implements IReceiptService{
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }
    @Override
    public boolean add(ReceiptDTO receiptDTO) {
        System.out.println("add method");

        ReceiptDao receiptDao = new ReceiptDAOImpl();

        TourService tourService = new TourService();
        TourDTO tour = tourService.getById(receiptDTO.getTourId());
        if (tour == null) return false;

        Receipt receipt = new Receipt(receiptDTO.getTourId(),
                receiptDTO.getUserId(), 0,
                tour.getPrice(),
                "registered");
        try {
            return receiptDao.addReceipt(receipt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ReceiptDTO> getAllByUserId(int id, int offset, int noOfRecords) {
        List<ReceiptDTO> receiptDTOList = new ArrayList<>();
        ReceiptDao receiptDao = new ReceiptDAOImpl();
        List<Receipt> receiptList;
        try {
            receiptList = receiptDao.getAllUserReceiptsByUserId(id, offset, noOfRecords);
            receiptList.forEach(receipt -> receiptDTOList.add( getReceiptDTOFromReceipt(receipt) ));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        this.noOfRecords = receiptDao.getNoOfRecords();
        return receiptDTOList;
    }

    @Override
    public List<ReceiptDTO> getAll(int offset, int noOfRecords) {
        List<ReceiptDTO> receiptDTOList = new ArrayList<>();
        ReceiptDao receiptDao = new ReceiptDAOImpl();
        List<Receipt> receiptList;
        try {
            receiptList = receiptDao.getAllReceipts(offset, noOfRecords);
            receiptList.forEach(receipt -> receiptDTOList.add( getReceiptDTOFromReceipt(receipt) ));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        this.noOfRecords = receiptDao.getNoOfRecords();
        return receiptDTOList;
    }

    @Override
    public boolean updateReceiptStatus(ReceiptDTO receiptDTO) {
        ReceiptDao receiptDao = new ReceiptDAOImpl();
        try {
            return receiptDao.updateReceiptStatus(receiptDTO.getId(), receiptDTO.getOrderStatus());
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReceiptDiscount(ReceiptDTO receiptDTO) {
        TourDTO tourDTO = new TourService().getById(receiptDTO.getTourId());
        int amount = tourDTO.getPrice() - (tourDTO.getPrice() * receiptDTO.getDiscount() / 100);
        ReceiptDao receiptDao = new ReceiptDAOImpl();
        try {
            receiptDTO.setOrderStatus( receiptDao.getReceiptById(receiptDTO.getId()).getOrderStatus() );
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        if (receiptDTO.getDiscount() >= 0 && receiptDTO.getDiscount() <= tourDTO.getMaxDiscount()
                && receiptDTO.getOrderStatus().equals("registered")) {

                try {
                    return receiptDao.updateReceiptDiscount(receiptDTO.getId(), receiptDTO.getDiscount(), amount);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    private ReceiptDTO getReceiptDTOFromReceipt(Receipt receipt) {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setId(receipt.getId());
        receiptDTO.setTourId(receipt.getTourId());
        receiptDTO.setTourName(receipt.getTourName());
        receiptDTO.setUserId(receipt.getUserId());
        receiptDTO.setDiscount(receipt.getDiscount());
        receiptDTO.setAmount(receipt.getAmount());
        receiptDTO.setOrderStatus(receipt.getOrderStatus());
        receiptDTO.setDatetime(receipt.getDatetime());
        receiptDTO.setUserFirstName(receipt.getUserFirstName());
        receiptDTO.setUserLastName(receipt.getUserLastName());

        receiptDTO.setPrice(getTourPrice(receiptDTO.getAmount(), receiptDTO.getDiscount()));

        return receiptDTO;
    }

    private int getTourPrice (int amount, int discount) {
        return amount / (100 - discount) * 100;
    }

}
