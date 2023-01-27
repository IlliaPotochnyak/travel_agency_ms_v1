import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;
import org.junit.Test;
import service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    User mockedUser = mock(User.class);

    List<User> userList = Arrays.asList(
            new User(1, "User1", "User1LastName", "user1@mail.com", "+11-111-111-1111", 1, "client"),
            new User(2, "User2", "User2LastName", "user2@mail.com", "+11-111-111-1111", 1, "client"),
            new User(3, "User3", "User3LastName", "user3@mail.com", "+11-111-111-1111", 1, "client"),
            new User(4, "User4", "User4LastName", "user4@mail.com", "+11-111-111-1111", 1, "client"),
            new User(5, "User5", "User5LastName", "user5@mail.com", "+11-111-111-1111", 1, "client"),
            new User(6, "User6", "User6LastName", "user6@mail.com", "+11-111-111-1111", 1, "client"),
            new User(7, "User7", "User7LastName", "user6@mail.com", "+11-111-111-1111", 1, "client")
            );


    @Test
    public void getByEmailPositiveTest() throws DatabaseException {
        String email = "mail@mail.com";
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(email);

        UserDAOImpl userDAO = mock(UserDAOImpl.class);
        when(userDAO.getUserByEmail(email)).thenReturn(user);
        UserService userService = new UserService(userDAO);

        assertEquals(email, userService.getByEmail(email).getEmail());
    }
    @Test
    public void getByEmailNegativeTest() throws DatabaseException {
        String email = "mail@mail.com";
        UserDAOImpl userDAO = mock(UserDAOImpl.class);
        when(userDAO.getUserByEmail(email)).thenReturn(null);
        UserService userService = new UserService(userDAO);
        assertNull(userService.getByEmail(email));
    }

    @Test
    public void getAllFirstPartTest() throws DatabaseException {
        int offset = 0;
        int noOfRecords = 5;

        UserDAOImpl userDAO = mock(UserDAOImpl.class);
        when(userDAO.getAllUsers(offset, noOfRecords)).thenReturn(userList.subList(offset, noOfRecords));

        UserService userService = new UserService(userDAO);
        List<UserDTO> userDTOList = userService.getAll(offset, noOfRecords);

        assertEquals(noOfRecords, userDTOList.size());
        for (int i = 0; i < noOfRecords; i++) {
            assertEquals(userDTOList.get(i).getId(), userList.get(i).getId());
            assertEquals(userDTOList.get(i).getFirstName(), userList.get(i).getFirstName());
        }
    }
    @Test
    public void getAllLastPartTest() throws DatabaseException {
        int offset = 5;
        int noOfRecords = userList.size() - 5;

        UserDAOImpl userDAO = mock(UserDAOImpl.class);
        when(userDAO.getAllUsers(offset, noOfRecords)).thenReturn(userList.subList(offset, userList.size()));

        UserService userService = new UserService(userDAO);
        List<UserDTO> userDTOList = userService.getAll(offset, noOfRecords);

        assertEquals(noOfRecords, userDTOList.size());
        for (int i = 0; i < noOfRecords; i++) {
            assertEquals(userDTOList.get(i).getId(), userList.get(i + offset).getId());
            assertEquals(userDTOList.get(i).getFirstName(), userList.get(i + offset).getFirstName());
        }
    }
}
