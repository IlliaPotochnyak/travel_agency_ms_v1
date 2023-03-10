package servlet;

import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserActiveServlet")
public class UserActiveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("UserActiveServlet doGet");
//        System.out.println("user id = " + req.getParameter("userId"));
//        System.out.println("user active = " + req.getParameter("userActive"));

        int userId = Integer.parseInt(req.getParameter("userId"));
        int userActive = Integer.parseInt(req.getParameter("userActive"));

        if (userId != 1) {
//            UserDAO userDAO = new UserDAOImpl();
            UserService userService = new UserService();
            userService.blockOrUnblockUser(userId, userActive);
        }

        resp.sendRedirect("Cabinet.jsp#userList");

    }
}
