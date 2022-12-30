package db.dao.DAOImpl.MySQLImpl;


import db.DataSource;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDAOImpl implements TourDAO {
    @Override
    public void addTour(Connection connection, Tour tour) throws SQLException {

    }

    @Override
    public List<Tour> getAllTours() throws DatabaseException {
        List<Tour> tourList = new ArrayList<>();
        String query = "SELECT * FROM tour ORDER BY hot DESC;";
        try (Connection con = DataSource.getConnection();
             Statement stmnt = con.createStatement();
             ResultSet rs = stmnt.executeQuery(query)){
            while (rs.next()) {
                Tour tour = new Tour(rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getInt("persons_number"),
                                    rs.getInt("price"),
                                    rs.getInt("hot"),
                                    rs.getInt("tour_type_id"),
                                    rs.getInt("hotel_type_id")
                                    );
                tourList.add(tour);
            }
//            tourList.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tourList;
    }
    @Override
    public List<Tour> getSortedTours(String tourType, String price, String personNumber, String hotelType) throws DatabaseException {
        List<Tour> tourList = new ArrayList<>();
        String query = "SELECT * FROM tour WHERE tour_type_id=? " +
                "AND price<=? AND persons_number=? AND hotel_type_id>=? " +
                "ORDER BY hot DESC;";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){
            pstmnt.setString(1, tourType);
            pstmnt.setString(2, price);
            pstmnt.setString(3, personNumber);
            pstmnt.setString(4, hotelType);
            System.out.println(pstmnt);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour(rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("persons_number"),
                        rs.getInt("price"),
                        rs.getInt("hot"),
                        rs.getInt("tour_type_id"),
                        rs.getInt("hotel_type_id")
                );
                tourList.add(tour);
            }
//            tourList.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tourList;
    }

    @Override
    public void deleteTourById(Long id) throws DatabaseException {

    }

    @Override
    public Tour getTourById(Long id) throws DatabaseException {
        return null;
    }

    @Override
    public void updatePlaceQuantityRowById(Connection connection, long id, int placeQuantity) throws SQLException {

    }

    @Override
    public void updateTour(Tour tour) throws DatabaseException {

    }

    @Override
    public void setHotTourById(long id) throws DatabaseException {

    }

    @Override
    public List<Tour> searchTours(int tourTypeId, float price, int peopleAmount, int stars) throws DatabaseException {
        return null;
    }
}
