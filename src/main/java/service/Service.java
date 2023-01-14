package service;

import DTO.AbstractDTO;
import DTO.TourDTO;

public interface Service {
    boolean add(TourDTO o);
    Object getById(int id);
    Object getAll();
    boolean Update();
    boolean delete(int id);
}
