import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;
import org.junit.Test;
import service.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTests {


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
}
