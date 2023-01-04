package db.dao.interfaces;

import entities.Tour;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TourDAO {
    void addTour(Connection connection, Tour tour) throws SQLException;

    List<Tour> getAllTours() throws DatabaseException;
    List<Tour> getSortedTours(String tourType, String price, String personNumber, String hotelType) throws DatabaseException;

    void deleteTourById(int id) throws DatabaseException;

    Tour getTourById(int id) throws DatabaseException;

    void updatePlaceQuantityRowById(Connection connection, int id, int placeQuantity) throws SQLException;

    void updateTour(Tour tour) throws DatabaseException;

    void setHotTourById(int id) throws DatabaseException;

    List<Tour> searchTours(int tourTypeId, float price, int peopleAmount, int stars) throws DatabaseException;
}
