package db.dao.DAOImpl.MySQLImpl;

import db.DataSource;
import db.dao.interfaces.ReceiptDao;
import entities.Receipt;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDao {

    @Override
    public boolean addReceipt(Receipt receipt) throws SQLException {
        String query = "INSERT INTO receipt (tour_id, user_id, discount, amount, order_status_id, datetime)" +
                "VALUES (?, ?, ?, ?, ?, now())";
        boolean result;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setInt(1, receipt.getTourId());
            pstmnt.setInt(2, receipt.getUserId());
            pstmnt.setInt(3, receipt.getDiscount());
            pstmnt.setInt(4, receipt.getAmount());
            pstmnt.setInt(5, receipt.getOrderStatusId());

            pstmnt.executeUpdate();

            result = true;
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Receipt> getAllUserReceiptsByUserId(int id) throws DatabaseException {
        List<Receipt> receiptList = new ArrayList<>();
        String query = "SELECT * FROM receipt WHERE user_id=?;";

        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){
            pstmnt.setInt(1, id);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
                //id, tour_id, user_id, discount, amount, order_status_id, datetime
                Receipt receipt = new Receipt(rs.getInt("id"),
                        rs.getInt("tour_id"),
                        rs.getInt("user_id"),
                        rs.getInt("discount"),
                        rs.getInt("amount"),
                        rs.getInt("order_status_id"),
                        rs.getString("datetime")
                );
                receiptList.add(receipt);
            }
            receiptList.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receiptList;
    }

    @Override
    public List<Receipt> getAllReceipts() throws DatabaseException {
        System.out.println("getAllReceipts");
        List<Receipt> receiptList = new ArrayList<>();
        String query = "SELECT * FROM receipt;";
        try (Connection con = DataSource.getConnection();
             Statement stmnt = con.createStatement();
             ResultSet rs = stmnt.executeQuery(query)){
            while (rs.next()) {
                //id, tour_id, user_id, discount, amount, order_status_id, datetime
                Receipt receipt = new Receipt(rs.getInt("id"),
                        rs.getInt("tour_id"),
                        rs.getInt("user_id"),
                        rs.getInt("discount"),
                        rs.getInt("amount"),
                        rs.getInt("order_status_id"),
                        rs.getString("datetime")
                );
                receiptList.add(receipt);
            }
            receiptList.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receiptList;
    }

    @Override
    public void updateReceiptStatus(Long ReceiptId, Long statusId) throws DatabaseException {

    }

    @Override
    public void updateReceiptPrice(Long ReceiptId, float price) throws DatabaseException {

    }

    @Override
    public List<Receipt> getTotalPriceOfPaidToursForClient() throws DatabaseException {
        return null;
    }
}
