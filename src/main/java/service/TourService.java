package service;

import DTO.AbstractDTO;
import DTO.TourDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourService implements Service{
    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

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
//        TourDTO tourDTO = new TourDTO();
        TourDTO tourDTO;

        try {
            Tour tour = tourDAO.getTourById(id);
            tourDTO = getTourDTOFromTour(tour);

//            tourDTO.setId(tour.getId());
//            tourDTO.setName(tour.getName());
//            tourDTO.setDescription(tour.getDescription());
//            tourDTO.setPersonsNumber(tour.getPersonsNumber());
//            tourDTO.setPrice(tour.getPrice());
//            tourDTO.setMaxDiscount(tour.getMaxDiscount());
//            tourDTO.setHot(tour.getHot());
//            tourDTO.setTourType(tour.getTourType());
//            tourDTO.setHotelType(tour.getHotelType());

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }


        return tourDTO;
    }

    @Override
    public List<TourDTO> getAll(int offset, int noOfRecords) {
        System.out.println("TourService getAll method");
        List<TourDTO> tourDTOList = new ArrayList<>();

        TourDAOImpl tourDAO = new TourDAOImpl();

        try {
            List<Tour> tourList = tourDAO.getAllTours(offset, noOfRecords);
            tourList.forEach(
                    tour -> {
                        TourDTO tourDTO = getTourDTOFromTour(tour);
                        tourDTOList.add(tourDTO);
                    }
            );

            this.noOfRecords = tourDAO.getNoOfRecords();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        return tourDTOList;
    }

    public List<TourDTO> getSorted(String tourType, String price, String personNumber, String hotelType,
                                   int offset, int noOfRecords) {
        System.out.println("TourService getSorted method");
        List<TourDTO> tourDTOList = new ArrayList<>();
        TourDTO paramTourDTO = new TourDTO();
        paramTourDTO.setTourType(tourType);
        paramTourDTO.setPrice(Integer.parseInt(price));
        paramTourDTO.setPersonsNumber(Integer.parseInt(personNumber));
        paramTourDTO.setHotelType(Integer.parseInt(hotelType));

        TourDAOImpl tourDAO = new TourDAOImpl();

        try {
            List<Tour> tourList = tourDAO.getSortedTours(paramTourDTO, offset, noOfRecords);
            tourList.forEach(
                    tour -> {
                        TourDTO tourDTO = getTourDTOFromTour(tour);
                        tourDTOList.add(tourDTO);
                    }
            );
            this.noOfRecords = tourDAO.getNoOfRecords();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        return tourDTOList;
    }

    @Override
    public boolean update(TourDTO tourDTO) {
        TourDAO tourDAO = new TourDAOImpl();

        try {
            return tourDAO.updateTour(tourDTO);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        TourDAO tourDAO = new TourDAOImpl();
        try {
            return tourDAO.deleteTourById(id);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    private TourDTO getTourDTOFromTour(Tour tour) {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setId(tour.getId());
        tourDTO.setName(tour.getName());
        tourDTO.setDescription(tour.getDescription());
        tourDTO.setPersonsNumber(tour.getPersonsNumber());
        tourDTO.setPrice(tour.getPrice());
        tourDTO.setMaxDiscount(tour.getMaxDiscount());
        tourDTO.setHot(tour.getHot());
        tourDTO.setTourType(tour.getTourType());
        tourDTO.setHotelType(tour.getHotelType());

        return tourDTO;

    }


}
