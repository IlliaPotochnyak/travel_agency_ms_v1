package db.dao.DAOImpl.MySQLImpl;

import DTO.TourDTO;
import db.DataSource;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static constant.MySQLConstant.*;

public class TourDAOImpl implements TourDAO {
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public boolean addTour(Tour tour) throws SQLException {
        boolean result = false;
        System.out.println("addTour method");
//        String query = "INSERT INTO tour (name, description, persons_number, price, max_discount, hot, tour_type_id, hotel_type_id)" +
//                "VALUES (?, ?, ?, ?, ?, ?, (SELECT tour_type.id FROM tour_type WHERE tour_type.tour_type=?), " +
//                "(SELECT hotel_type.id FROM hotel_type WHERE hotel_type.star_rate=?))";
        String query = ADD_TOUR;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setString(1, tour.getName());
            pstmnt.setString(2, tour.getDescription());
            pstmnt.setInt(3, tour.getPersonsNumber());
            pstmnt.setInt(4, tour.getPrice());
            pstmnt.setInt(5, tour.getMaxDiscount());
            pstmnt.setInt(6, tour.getHot());
            pstmnt.setString(7, tour.getTourType());
            pstmnt.setInt(8, tour.getHotelType());
            pstmnt.executeUpdate();

            result = true;
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<Tour> getAllTours(int offset, int noOfRecords) throws DatabaseException {
        System.out.println("getAllTours");
        List<Tour> tourList = new ArrayList<>();
//        String query = "SELECT SQL_CALC_FOUND_ROWS tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.max_discount, tour.hot, \n" +
//                "tour_type.tour_type, hotel_type.star_rate \n" +
//                "FROM ((tour\n" +
//                "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
//                "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) ORDER BY hot DESC limit "
//                + offset + ", " + noOfRecords;
        String query = GET_ALL_TOURS + offset + ", " + noOfRecords;

        try (Connection con = DataSource.getConnection();
             Statement stmnt = con.createStatement()
             ){
//            System.out.println("con - ok");
            ResultSet rs = stmnt.executeQuery(query);
//            System.out.println(rs);
            while (rs.next()) {
                Tour tour = new Tour(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getInt("persons_number"),
                                    rs.getInt("price"),
                                    rs.getInt("max_discount"),
                                    rs.getInt("hot"),
                                    rs.getString("tour_type"),
                                    rs.getInt("star_rate")
                                    );
                tourList.add(tour);
            }
            rs.close();

            rs = stmnt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
//            tourList.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tourList;
    }

    @Override
    public List<Tour> getSortedTours(String tourType, String price, String personNumber, String hotelType, int offset, int noOfRecords) throws DatabaseException {
        //        System.out.println("get sorted tours");
        List<Tour> tourList = new ArrayList<>();

//        String query = "SELECT SQL_CALC_FOUND_ROWS tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.max_discount, tour.hot, \n" +
//                "tour_type.tour_type, hotel_type.star_rate \n" +
//                "FROM ((tour\n" +
//                "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
//                "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) WHERE tour_type=? AND price<=? " +
//                "AND persons_number=? " +
//                "AND star_rate>=? " +
//                "ORDER BY hot DESC limit "
//                + offset + ", " + noOfRecords;
        String query = GET_SORTED_TOURS + offset + ", " + noOfRecords;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){

            pstmnt.setString(1, tourType);
            pstmnt.setString(2, price);
            pstmnt.setString(3, personNumber);
            pstmnt.setString(4, hotelType);
//            pstmnt.setString(1, tourDTO.getTourType());
//            pstmnt.setInt(2, tourDTO.getPrice());
//            pstmnt.setInt(3, tourDTO.getPersonsNumber());
//            pstmnt.setInt(4, tourDTO.getHotelType());

            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("persons_number"),
                        rs.getInt("price"),
                        rs.getInt("max_discount"),
                        rs.getInt("hot"),
                        rs.getString("tour_type"),
                        rs.getInt("star_rate")
                );
                tourList.add(tour);
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

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return tourList;
    }

    @Override
//    public List<Tour> getSortedTours(String tourType, String price, String personNumber, String hotelType,
//                                     int offset, int noOfRecords) throws DatabaseException {
    public List<Tour> getSortedTours(TourDTO tourDTO, int offset, int noOfRecords) throws DatabaseException {
//        System.out.println("get sorted tours");
        List<Tour> tourList = new ArrayList<>();

//        String query = "SELECT SQL_CALC_FOUND_ROWS tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.max_discount, tour.hot, \n" +
//                "tour_type.tour_type, hotel_type.star_rate \n" +
//                "FROM ((tour\n" +
//                "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
//                "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) WHERE tour_type=? AND price<=? " +
//                "AND persons_number=? " +
//                "AND star_rate>=? " +
//                "ORDER BY hot DESC limit "
//                + offset + ", " + noOfRecords;
        String query = GET_SORTED_TOURS + offset + ", " + noOfRecords;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){

//            pstmnt.setString(1, tourType);
//            pstmnt.setString(2, price);
//            pstmnt.setString(3, personNumber);
//            pstmnt.setString(4, hotelType);
            pstmnt.setString(1, tourDTO.getTourType());
            pstmnt.setInt(2, tourDTO.getPrice());
            pstmnt.setInt(3, tourDTO.getPersonsNumber());
            pstmnt.setInt(4, tourDTO.getHotelType());

            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("persons_number"),
                        rs.getInt("price"),
                        rs.getInt("max_discount"),
                        rs.getInt("hot"),
                        rs.getString("tour_type"),
                        rs.getInt("star_rate")
                );
                tourList.add(tour);
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

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return tourList;
    }

    @Override
    public boolean deleteTourById(int id) throws DatabaseException {
        boolean result = false;
//        System.out.println("deleteTourById method - tour id = " + id);
//        String query = "DELETE FROM tour WHERE id=?;";
        String query = DELETE_TOUR_BY_ID;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setInt(1, id);

            pstmnt.execute();

            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Tour getTourById(int id) throws DatabaseException {
        Tour tour = null;
//        String query = "SELECT tour.id, tour.name, tour.description, tour.persons_number, tour.price, tour.max_discount, tour.hot, \n" +
//                "tour_type.tour_type, hotel_type.star_rate \n" +
//                "FROM ((tour\n" +
//                "INNER JOIN tour_type ON tour.tour_type_id=tour_type.id)\n" +
//                "INNER JOIN hotel_type ON tour.hotel_type_id=hotel_type.id) WHERE tour.id=?;";
        String query = GET_TOUR_BY_ID;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)){
            pstmnt.setInt(1, id);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next())
                tour = new Tour(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("persons_number"),
                    rs.getInt("price"),
                    rs.getInt("max_discount"),
                    rs.getInt("hot"),
                    rs.getString("tour_type"),
                    rs.getInt("star_rate")
                );
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return tour;
    }

    @Override
    public void updatePlaceQuantityRowById(Connection connection, int id, int placeQuantity) throws SQLException {

    }

    @Override
    public boolean updateTour(TourDTO tour) throws DatabaseException {
        boolean result = false;
        System.out.println("updateTour method");
        //id, name, description, persons_number, price, hot, tour_type_id, hotel_type_id
//        String query = "UPDATE tour SET " +
//                "name=?, description=?, persons_number=?, price=?, tour.max_discount=?, hot=?, " +
//                "tour_type_id=(SELECT tour_type.id FROM tour_type WHERE tour_type=?)," +
//                "hotel_type_id=(SELECT hotel_type.id FROM hotel_type WHERE star_rate=?) " +
//                "WHERE tour.id=?;";
        String query = UPDATE_TOUR;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pstmnt = con.prepareStatement(query)) {
            pstmnt.setString(1, tour.getName());
            pstmnt.setString(2, tour.getDescription().trim());
            pstmnt.setInt(3, tour.getPersonsNumber());
            pstmnt.setInt(4, tour.getPrice());
            pstmnt.setInt(5, tour.getMaxDiscount());
            pstmnt.setInt(6, tour.getHot());
            pstmnt.setString(7, tour.getTourType());
            pstmnt.setInt(8, tour.getHotelType());
            pstmnt.setInt(9, tour.getId());
            pstmnt.executeUpdate();

            result = true;
            System.out.println("update db ok!");
        } catch (SQLException e) {
            result = false;
            throw new RuntimeException(e);
        }


        return result;
    }

    @Override
    public void setHotTourById(int id) throws DatabaseException {

    }

    @Override
    public List<Tour> searchTours(int tourTypeId, float price, int peopleAmount, int stars) throws DatabaseException {
        return null;
    }
}
