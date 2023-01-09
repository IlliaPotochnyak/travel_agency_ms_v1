package db.dao.interfaces;

import entities.Tour;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TourDAO {
    boolean addTour(Tour tour) throws SQLException;



    List<Tour> getAllTours(int offset, int noOfRecords) throws DatabaseException;

    List<Tour> getSortedTours(String tourType, String price, String personNumber, String hotelType,
                              int offset, int noOfRecords) throws DatabaseException;

    void deleteTourById(int id) throws DatabaseException;

    Tour getTourById(int id) throws DatabaseException;

    void updatePlaceQuantityRowById(Connection connection, int id, int placeQuantity) throws SQLException;

    boolean updateTour(Tour tour) throws DatabaseException;

    void setHotTourById(int id) throws DatabaseException;

    List<Tour> searchTours(int tourTypeId, float price, int peopleAmount, int stars) throws DatabaseException;
}
