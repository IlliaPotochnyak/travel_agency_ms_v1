import DTO.TourDTO;
import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import entities.User;
import exceptions.DatabaseException;
import org.junit.jupiter.api.Test;
import service.TourService;
import service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TourServiceTests {

    @Test
    public void addTourTest() throws DatabaseException, SQLException {
//        User user = mock(User.class);
        TourDAOImpl tourDAO = mock(TourDAOImpl.class);
        when(tourDAO.addTour(any(Tour.class))).thenReturn(true);

        TourDTO newTourDTO = new TourDTO();

        TourService tourService = new TourService(tourDAO);
//        System.out.println(userService.add(userDTO));
        assertTrue(tourService.add(newTourDTO));

    }
    @Test
    public void getByIdTourPositiveTest() throws DatabaseException, SQLException {
        int id = 2;
        Tour tour = mock(Tour.class);
        when(tour.getId()).thenReturn(id);
        TourDAOImpl tourDAO = mock(TourDAOImpl.class);
        when(tourDAO.getTourById(id)).thenReturn(tour);

        TourDTO newTourDTO = new TourDTO();

        TourService tourService = new TourService(tourDAO);
        assertEquals(id, tourService.getById(id).getId());
    }
    @Test
    public void getByIdTourNegativeTest() throws DatabaseException, SQLException {
        int id = 2;
        Tour tour = mock(Tour.class);
        when(tour.getId()).thenReturn(id);
        TourDAOImpl tourDAO = mock(TourDAOImpl.class);
        when(tourDAO.getTourById(id)).thenReturn(null);

        TourDTO newTourDTO = new TourDTO();

        TourService tourService = new TourService(tourDAO);
        assertNull(tourService.getById(id));
    }

    @Test
    public void getAllTourTest() throws DatabaseException, SQLException {
        int offset = 0;
        int noOfRecords = 5;
        Tour tour = mock(Tour.class);
        List<Tour> list = new ArrayList<>();

        TourDAOImpl tourDAO = mock(TourDAOImpl.class);
        when(tourDAO.getAllTours(offset, noOfRecords)).thenReturn(list);
        TourService service = new TourService(tourDAO);

        assertEquals(list.size(), service.getAll(offset, noOfRecords).size());

        for (int i = 0; i < 5; i++) {
            list.add(tour);
            assertEquals(list.size(), service.getAll(offset, noOfRecords).size());
            assertTrue(service.getAll(offset, noOfRecords).size() <= noOfRecords);
        }

        list.add(tour);
        assertEquals(list.size(), service.getAll(offset, noOfRecords).size());
        assertFalse(service.getAll(offset, noOfRecords).size() <= noOfRecords);
    }

    @Test
    public void updateTourNegativeTest () throws DatabaseException {
        TourDTO tourDTO = mock(TourDTO.class);
        TourDAO tourDAO = mock(TourDAOImpl.class);
        when(tourDAO.updateTour(any(TourDTO.class))).thenThrow( new DatabaseException());

        TourService tourService = new TourService(tourDAO);
        assertFalse(tourService.update(tourDTO));
    }
    @Test
    public void deleteTourNegativeTest () throws DatabaseException {
        int id = 0;
        TourDAO tourDAO = mock(TourDAOImpl.class);
        when(tourDAO.deleteTourById(id)).thenThrow( new DatabaseException());

        TourService tourService = new TourService(tourDAO);
        assertFalse(tourService.delete(id));
    }

}
