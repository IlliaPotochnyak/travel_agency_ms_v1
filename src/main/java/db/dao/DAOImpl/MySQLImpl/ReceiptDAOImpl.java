package db.dao.DAOImpl.MySQLImpl;

import db.DataSource;
import db.dao.interfaces.ReceiptDao;
import entities.Receipt;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDao {

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public boolean addReceipt(Receipt receipt) throws SQLException {
        String query = "INSERT INTO receipt (tour_id, user_id, discount, amount, order_status_id, datetime)" +
                "VALUES (?, ?, ?, ?," +
                " (SELECT receipt_status.id FROM receipt_status WHERE receipt_status.receipt_status=?), now())";
        boolean result;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setInt(1, receipt.getTourId());
            pstmnt.setInt(2, receipt.getUserId());
            pstmnt.setInt(3, receipt.getDiscount());
            pstmnt.setInt(4, receipt.getAmount());
            pstmnt.setString(5, receipt.getOrderStatus());

            pstmnt.executeUpdate();

            result = true;
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Receipt> getAllUserReceiptsByUserId(int id, int offset, int noOfRecords) throws DatabaseException {
        List<Receipt> receiptList = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS receipt.id, receipt.tour_id, tour.name, receipt.user_id, user.first_name, user.last_name, receipt.discount, receipt.amount, receipt_status.receipt_status, receipt.datetime \n" +
                "FROM receipt INNER JOIN tour ON receipt.tour_id=tour.id\n" +
                "    INNER JOIN receipt_status ON receipt.order_status_id=receipt_status.id\n" +
                "    INNER JOIN user ON receipt.user_id=user.id " +
                "WHERE user.id=? ORDER BY order_status_id limit "
                + offset + ", " + noOfRecords;

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
                        rs.getString("receipt_status"),
                        rs.getString("datetime"),
                        rs.getString("name"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                receiptList.add(receipt);
            }
            rs.close();
            try (Statement stmnt = con.createStatement();){

                rs = stmnt.executeQuery("SELECT FOUND_ROWS()");
                if(rs.next())
                    this.noOfRecords = rs.getInt(1);
//            tourList.forEach(System.out::println);
            }catch (SQLException e) {

                throw new RuntimeException(e);
            }
//            receiptList.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receiptList;
    }

    @Override
    public List<Receipt> getAllReceipts(int offset, int noOfRecords) throws DatabaseException {
        System.out.println("getAllReceipts");
        List<Receipt> receiptList = new ArrayList<>();
//        String query = "SELECT * FROM receipt;";
        String query = "SELECT SQL_CALC_FOUND_ROWS receipt.id, receipt.tour_id, tour.name, receipt.user_id, user.first_name, user.last_name, receipt.discount, receipt.amount, receipt_status.receipt_status, receipt.datetime \n" +
                "FROM receipt INNER JOIN tour ON receipt.tour_id=tour.id\n" +
                "    INNER JOIN receipt_status ON receipt.order_status_id=receipt_status.id\n" +
                "    INNER JOIN user ON receipt.user_id=user.id  ORDER BY order_status_id limit "
                + offset + ", " + noOfRecords;
        try (Connection con = DataSource.getConnection();
             Statement stmnt = con.createStatement();
             ){
            ResultSet rs = stmnt.executeQuery(query);
//            System.out.println("ResultSet " + rs);
            while (rs.next()) {
                //id, tour_id, user_id, discount, amount, order_status, datetime
//                System.out.println(rs.getInt("id")
//                        + "-" + rs.getInt("user_id")
//                        + "-" + rs.getInt("discount")
//                );
                Receipt receipt = new Receipt(rs.getInt("id"),
                        rs.getInt("tour_id"),
                        rs.getInt("user_id"),
                        rs.getInt("discount"),
                        rs.getInt("amount"),
                        rs.getString("receipt_status"),
                        rs.getString("datetime"),
                        rs.getString("name"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
//                System.out.println("Receipt - " + receipt);
                receiptList.add(receipt);
            }
            rs.close();

            rs = stmnt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
//            receiptList.forEach(System.out::println);

        } catch (SQLException e) {
//            System.out.println("ReceiptDao error");
            throw new RuntimeException(e);
        }
        return receiptList;
    }

    @Override
    public boolean updateReceiptStatus(int id, String status) throws DatabaseException {
        boolean result;

        String query ="UPDATE receipt SET order_status_id=" +
                "(SELECT receipt_status.id from receipt_status WHERE receipt_status=?) " +
                "WHERE receipt.id=?;";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setString(1, status);
            pstmnt.setInt(2, id);
            System.out.println(pstmnt);
            pstmnt.executeUpdate();

            result = true;
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateReceiptPrice(Long ReceiptId, float price) throws DatabaseException {

    }

    @Override
    public List<Receipt> getTotalPriceOfPaidToursForClient() throws DatabaseException {
        return null;
    }
}
