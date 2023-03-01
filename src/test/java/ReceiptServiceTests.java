import DTO.ReceiptDTO;
import DTO.TourDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.ReceiptDao;
import db.dao.interfaces.TourDAO;
import entities.Receipt;
import entities.Tour;
import exceptions.DatabaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ReceiptService;
import service.TourService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReceiptServiceTests {

    @Test
    public void addReceiptTest() throws SQLException {
        TourDAO tourDAO = mock(TourDAOImpl.class);
        TourDTO tour = mock(TourDTO.class);
        when(tour.getPrice()).thenReturn(10000);
        ReceiptDTO receiptDTO = mock(ReceiptDTO.class);
        when(receiptDTO.getTourId()).thenReturn(1);
        when(receiptDTO.getUserId()).thenReturn(11);
        TourService tourService = mock(TourService.class);
        when(tourService.getById(receiptDTO.getTourId())).thenReturn(tour);
        ReceiptDao receiptDao = mock(ReceiptDAOImpl.class);
        when(receiptDao.addReceipt(any(Receipt.class))).thenReturn(true);

        ReceiptService receiptService = new ReceiptService(receiptDao);
        Assertions.assertTrue(receiptService.add(receiptDTO));


    }
    @Test
    public void addReceiptWrongTourTest() {
        TourDAO tourDAO = mock(TourDAOImpl.class);
        ReceiptDTO receiptDTO = mock(ReceiptDTO.class);
        when(receiptDTO.getTourId()).thenReturn(1);
        when(receiptDTO.getUserId()).thenReturn(11);
        TourService tourService = mock(TourService.class);
        when(tourService.getById(receiptDTO.getTourId())).thenReturn(null);
        ReceiptDao receiptDao = mock(ReceiptDAOImpl.class);

        ReceiptService receiptService = new ReceiptService(receiptDao);
        Assertions.assertFalse(receiptService.add(receiptDTO));
    }

    @Test
    public void getAllreceiptsByUserIdTest () throws DatabaseException {
        int id = 1;
        int offset = 0;
        int noOfRecords = 5;
        Receipt receipt = mock(Receipt.class);
        List<Receipt> receiptList = new ArrayList<>();
        ReceiptDao receiptDao = mock(ReceiptDAOImpl.class);
        when(receiptDao.getAllUserReceiptsByUserId(id, offset, noOfRecords)).thenReturn(receiptList);

        ReceiptService receiptService = new ReceiptService(receiptDao);
        Assertions.assertEquals(receiptList.size(), receiptService.getAllByUserId(id, offset, noOfRecords).size());

        for (int i = 0; i < noOfRecords; i++) {
            receiptList.add(receipt);
            Assertions.assertEquals(receiptList.size(), receiptService.getAllByUserId(id, offset, noOfRecords).size());
            Assertions.assertTrue(receiptService.getAllByUserId(id, offset, noOfRecords).size() <= noOfRecords);
        }
        receiptList.add(receipt);
        Assertions.assertEquals(receiptList.size(), receiptService.getAllByUserId(id, offset, noOfRecords).size());
        Assertions.assertFalse(receiptService.getAllByUserId(id, offset, noOfRecords).size() <= noOfRecords);
    }

    @Test
    public void getAllreceiptsTest () throws DatabaseException {
        int offset = 0;
        int noOfRecords = 5;
        Receipt receipt = mock(Receipt.class);
        List<Receipt> receiptList = new ArrayList<>();
        ReceiptDao receiptDao = mock(ReceiptDAOImpl.class);
        when(receiptDao.getAllReceipts(offset, noOfRecords)).thenReturn(receiptList);

        ReceiptService receiptService = new ReceiptService(receiptDao);
        Assertions.assertEquals(receiptList.size(), receiptService.getAll(offset, noOfRecords).size());

        for (int i = 0; i < noOfRecords; i++) {
            receiptList.add(receipt);
            Assertions.assertEquals(receiptList.size(), receiptService.getAll(offset, noOfRecords).size());
            Assertions.assertTrue(receiptService.getAll(offset, noOfRecords).size() <= noOfRecords);
        }
        receiptList.add(receipt);
        Assertions.assertEquals(receiptList.size(), receiptService.getAll(offset, noOfRecords).size());
        Assertions.assertFalse(receiptService.getAll(offset, noOfRecords).size() <= noOfRecords);
    }

    @Test
    public void updateReceiptStatusNegativeTest() throws DatabaseException {
        ReceiptDao receiptDao = mock(ReceiptDAOImpl.class);
        int id = 1;
        String status = "registered";
        when(receiptDao.updateReceiptStatus(id, status)).thenThrow(new DatabaseException());
        ReceiptDTO receiptDTO = mock(ReceiptDTO.class);
        when(receiptDTO.getId()).thenReturn(id);
        when(receiptDTO.getOrderStatus()).thenReturn(status);
        ReceiptService receiptService = new ReceiptService(receiptDao);
        Assertions.assertFalse(receiptService.updateReceiptStatus(receiptDTO));
    }

    @Test
    public void updateReceiptDiscountDBExceptionTest() throws DatabaseException {
        ReceiptDTO receiptDTO = mock(ReceiptDTO.class);
        ReceiptDao receiptDAO = mock(ReceiptDAOImpl.class);
        TourDTO tourDTO = mock(TourDTO.class);

        when(receiptDTO.getId()).thenReturn(0);
        when(receiptDAO.getReceiptById(0)).thenThrow(new DatabaseException());

        ReceiptService receiptService = new ReceiptService(receiptDAO);
        //fail to get receipt from DB
        Assertions.assertFalse(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));

    }
    @Test
    public void updateReceiptDiscountOverDiscountTest() throws DatabaseException {
        ReceiptDTO receiptDTO = mock(ReceiptDTO.class);
        ReceiptDao receiptDAO = mock(ReceiptDAOImpl.class);
        TourDTO tourDTO = mock(TourDTO.class);
        Receipt receipt = mock(Receipt.class);
        when(receiptDAO.updateReceiptDiscount(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(true);

        when(receiptDTO.getId()).thenReturn(0);
        when(receiptDAO.getReceiptById(0)).thenReturn(receipt);
        when(receiptDTO.getOrderStatus()).thenReturn("registered");
        when(tourDTO.getMaxDiscount()).thenReturn(10);

        ReceiptService receiptService = new ReceiptService(receiptDAO);
        //discount equal to maxDiscount
        when(receiptDTO.getDiscount()).thenReturn(10);
        Assertions.assertTrue(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));
        //discount smaller then maxDiscount
        when(receiptDTO.getDiscount()).thenReturn(9);
        Assertions.assertTrue(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));
        //discount greater then maxDiscount
        when(receiptDTO.getDiscount()).thenReturn(11);
        Assertions.assertFalse(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));
        //тупфешму discount
        when(receiptDTO.getDiscount()).thenReturn(-1);
        Assertions.assertFalse(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));
    }
    @Test
    public void updateReceiptDiscountWrongOrderStatusTest() throws DatabaseException {
        ReceiptDTO receiptDTO = mock(ReceiptDTO.class);
        ReceiptDao receiptDAO = mock(ReceiptDAOImpl.class);
        TourDTO tourDTO = mock(TourDTO.class);
        Receipt receipt = mock(Receipt.class);
        when(receiptDAO.updateReceiptDiscount(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(true);
        when(receiptDTO.getId()).thenReturn(0);
        when(receiptDAO.getReceiptById(0)).thenReturn(receipt);
        when(receiptDTO.getDiscount()).thenReturn(10);
        when(tourDTO.getMaxDiscount()).thenReturn(10);

        ReceiptService receiptService = new ReceiptService(receiptDAO);
        when(receiptDTO.getOrderStatus()).thenReturn("registered");
        Assertions.assertTrue(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));

        when(receiptDTO.getOrderStatus()).thenReturn("paid");
        Assertions.assertFalse(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));
        when(receiptDTO.getOrderStatus()).thenReturn("canceled");
        Assertions.assertFalse(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));
    }
    @Test
    public void updateReceiptDiscountUpdateExceptionTest() throws DatabaseException {
        ReceiptDTO receiptDTO = mock(ReceiptDTO.class);
        ReceiptDao receiptDAO = mock(ReceiptDAOImpl.class);
        TourDTO tourDTO = mock(TourDTO.class);
        Receipt receipt = mock(Receipt.class);

        when(receiptDTO.getId()).thenReturn(0);
        when(receiptDAO.getReceiptById(0)).thenReturn(receipt);
        when(receiptDTO.getDiscount()).thenReturn(10);
        when(tourDTO.getMaxDiscount()).thenReturn(10);
        when(receiptDTO.getOrderStatus()).thenReturn("registered");

        ReceiptService receiptService = new ReceiptService(receiptDAO);
        when(receiptDAO.updateReceiptDiscount(any(Integer.class), any(Integer.class), any(Integer.class)))
                .thenThrow(new DatabaseException());
        Assertions.assertFalse(receiptService.updateReceiptDiscount(receiptDTO, tourDTO));
    }

}
