package db.dao.DAOImpl.MySQLImpl;


import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TourDAOImpl implements TourDAO {
    @Override
    public void addTour(Connection connection, Tour tour) throws SQLException {

    }

    @Override
    public List<Tour> getAllTours() throws DatabaseException {
        return null;
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
