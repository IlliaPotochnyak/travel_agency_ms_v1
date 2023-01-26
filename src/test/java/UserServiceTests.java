import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;
import org.junit.Test;
import service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTests {


    @Test
    public void getByEmailPositiveTest() throws DatabaseException {
        String email = "mail@mail.com";
        User user = mock(User.class);
        when(user.getFirstName()).thenReturn("UserFirstName");
        when(user.getLastName()).thenReturn("UserLastName");
        when(user.getEmail()).thenReturn(email);

//        assertEquals(email, user.getEmail());

        UserDAOImpl userDAO = mock(UserDAOImpl.class);
        when(userDAO.getUserByEmail(email)).thenReturn(user);



//        assertEquals(user.getEmail(), userDAO.getUserByEmail(email).getEmail());
//
        UserService userService = new UserService(userDAO);


        System.out.println(userService.getByEmail(email).getEmail());
//
//        assertEquals(email, userService.getByEmail(email).getEmail());




    }
}
