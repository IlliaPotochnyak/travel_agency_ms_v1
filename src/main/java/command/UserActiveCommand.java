package command;

import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import service.UserService;

import java.util.List;

public class UserActiveCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("UserActiveCommand");
        String pagePath = "/index.jsp";

        int userId = Integer.parseInt(req.getParameter("userId"));
        int userActive = Integer.parseInt(req.getParameter("userActive"));

        if (userId != 1) {

            UserDAO userDAO = new UserDAOImpl();
            UserService userService = new UserService(userDAO);
            userService.blockOrUnblockUser(userId, userActive);
        }

        pagePath = "/controller?command=user_list";


        return pagePath;
    }
}
