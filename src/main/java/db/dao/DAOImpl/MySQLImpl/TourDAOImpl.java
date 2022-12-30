package db.dao.DAOImpl.MySQLImpl;


import db.DataSource;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TourDAOImpl implements TourDAO {
    @Override
    public void addTour(Connection connection, Tour tour) throws SQLException {

    }

    @Override
    public List<Tour> getAllTours() throws DatabaseException {
        List<Tour> tourList = new ArrayList<>();
        String query = "SELECT * FROM travel_agency_db1.tour;";
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
