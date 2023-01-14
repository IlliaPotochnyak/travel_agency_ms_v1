package service;

import DTO.TourDTO;

import java.util.List;

public interface Service {
    boolean add(TourDTO o);
    Object getById(int id);
    List<TourDTO> getAll(int offset, int noOfRecords);
    boolean update(TourDTO tourDTO);
    boolean delete(int id);
}
