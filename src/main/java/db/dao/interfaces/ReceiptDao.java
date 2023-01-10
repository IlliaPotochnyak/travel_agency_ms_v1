package db.dao.interfaces;

import entities.Receipt;
import exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public interface ReceiptDao {
    boolean addReceipt(Receipt receipt) throws SQLException;

    List<Receipt> getAllUserReceiptsByUserId(int id, int offset, int noOfRecords) throws DatabaseException;

    List<Receipt> getAllReceipts(int offset, int noOfRecords) throws DatabaseException;

    boolean updateReceiptStatus(int id, String status) throws DatabaseException;

    void updateReceiptPrice(Long ReceiptId, float price) throws DatabaseException;

    List<Receipt> getTotalPriceOfPaidToursForClient() throws DatabaseException;

    int getNoOfRecords();
}
