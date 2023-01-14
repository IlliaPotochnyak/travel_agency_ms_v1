package service;

import DTO.AbstractDTO;
import DTO.TourDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.TourDAO;
import entities.Tour;

import java.sql.SQLException;

public class TourService implements Service{
    @Override
    public boolean add(TourDTO newTourDTO) {

        Tour newTour= new Tour(
                newTourDTO.getName(),
                newTourDTO.getDescription(),
                newTourDTO.getPersonsNumber(),
                newTourDTO.getPrice(),
                newTourDTO.getMaxDiscount(),
                newTourDTO.getHot(),
                newTourDTO.getTourType(),
                newTourDTO.getHotelType() );

        TourDAO tourDAO = new TourDAOImpl();
        try {
            return tourDAO.addTour(newTour);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return false;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public Object getAll() {
        return null;
    }

    @Override
    public boolean Update() {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
