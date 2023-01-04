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
        String query = "SELECT tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.hot, \n" +
                "tour_type.tour_type, hotel_type.star_rate \n" +
                "FROM ((tour\n" +
                "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
                "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) ORDER BY hot DESC;";
//        String query = "SELECT tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.hot, \n" +
//                "                tour_type.tour_type, tour.hotel_type_id\n" +
//                "                FROM tour\n" +
//                "                INNER JOIN tour_type ON tour.tour_type_id=tour_type.id ORDER BY hot DESC;";
        try (Connection con = DataSource.getConnection();
             Statement stmnt = con.createStatement();
             ResultSet rs = stmnt.executeQuery(query)){

            while (rs.next()) {
                Tour tour = new Tour(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getInt("persons_number"),
                                    rs.getInt("price"),
                                    rs.getInt("hot"),
                                    rs.getString("tour_type"),
                                    rs.getInt("star_rate")
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

        String query = "SELECT tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.hot, \n" +
                "tour_type.tour_type, hotel_type.star_rate \n" +
                "FROM ((tour\n" +
                "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
                "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) WHERE tour_type=? AND price<=? " +
                "AND persons_number=? " +
                "AND star_rate>=? " +
                "ORDER BY hot DESC;";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){

            pstmnt.setString(1, tourType);
            pstmnt.setString(2, price);
            pstmnt.setString(3, personNumber);
            pstmnt.setString(4, hotelType);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("persons_number"),
                        rs.getInt("price"),
                        rs.getInt("hot"),
                        rs.getString("tour_type"),
                        rs.getInt("star_rate")
                );
                tourList.add(tour);
            }
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
        Tour tour = null;
        String query = "SELECT tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.hot, \n" +
                "tour_type.tour_type, hotel_type.star_rate \n" +
                "FROM ((tour\n" +
                "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
                "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.star_rate) WHERE tour.id=?";
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){
            pstmnt.setLong(1, id);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next())
                tour = new Tour(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("persons_number"),
                        rs.getInt("price"),
                        rs.getInt("hot"),
                        rs.getString("tour_type"),
                        rs.getInt("hotel_type_id")
                );
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return tour;
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
