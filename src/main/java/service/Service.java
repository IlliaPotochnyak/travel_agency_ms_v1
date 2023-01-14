package service;

import DTO.AbstractDTO;
import DTO.IDTO;
import DTO.TourDTO;

import java.util.List;

public interface Service {
    boolean add(TourDTO o);
    Object getById(int id);
    List<TourDTO> getAll(int offset, int noOfRecords);
    boolean Update();
    boolean delete(int id);
}
