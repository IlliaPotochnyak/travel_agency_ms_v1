package command;

import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import service.UserService;

public class LangChangeCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("LangChangeCommand");

        String page = "/";
        if (req.getParameter("path").isEmpty()) {

            page = req.getParameter("URI").substring(req.getParameter("URI").lastIndexOf("/"));
        } else {
            page += "controller?" + req.getParameter("path");
        }
        return page;
    }
}
