import DTO.TourDTO;
import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import entities.Tour;
import entities.User;
import exceptions.DatabaseException;
import org.junit.Test;
import service.TourService;
import service.UserService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TourServiceTests {

    @Test
    public void AddTourTest() throws DatabaseException, SQLException {
//        User user = mock(User.class);
        TourDAOImpl tourDAO = mock(TourDAOImpl.class);
        when(tourDAO.addTour(any(Tour.class))).thenReturn(true);

        TourDTO newTourDTO = new TourDTO();

        TourService tourService = new TourService(tourDAO);
//        System.out.println(userService.add(userDTO));
        assertTrue(tourService.add(newTourDTO));

    }
}
