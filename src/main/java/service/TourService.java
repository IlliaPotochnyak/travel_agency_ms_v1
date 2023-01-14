package service;

import DTO.AbstractDTO;
import DTO.TourDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;

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
    public TourDTO getById(int id) {

        TourDAO tourDAO = new TourDAOImpl();
        TourDTO tourDTO = new TourDTO();

        try {
            Tour tour = tourDAO.getTourById(id);

            tourDTO.setId(tour.getId());
            tourDTO.setName(tour.getName());
            tourDTO.setDescription(tour.getDescription());
            tourDTO.setPersonsNumber(tour.getPersonsNumber());
            tourDTO.setPrice(tour.getPrice());
            tourDTO.setMaxDiscount(tour.getMaxDiscount());
            tourDTO.setHot(tour.getHot());
            tourDTO.setTourType(tour.getTourType());
            tourDTO.setHotelType(tour.getHotelType());

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }


        return tourDTO;
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
