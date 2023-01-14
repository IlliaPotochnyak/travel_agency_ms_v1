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
import jakarta.servlet.http.HttpSession;
import service.UserService;

import java.io.IOException;

@WebServlet("/LoginValidator")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        UserDAO userDAO = new UserDAOImpl();
//        User user = null;
        UserService userService = new UserService();
        UserDTO userDTO = userService.loginUser(req.getParameter("email"), req.getParameter("password"));

//        try {
//            user = userDAO.getUserByEmail(req.getParameter("email"));
//        } catch (DatabaseException e) {
//            System.out.println("Get user fault");
//            throw new RuntimeException(e);
//        }
        if (userDTO != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("UserId", userDTO.getId());
            session.setAttribute("UserFirstName", userDTO.getFirstName());
            session.setAttribute("UserLastName", userDTO.getLastName());
            session.setAttribute("UserEmail", userDTO.getEmail());
            session.setAttribute("UserPhone", userDTO.getPhone());
            session.setAttribute("UserRole", userDTO.getRole());
            session.setAttribute("UserActive", userDTO.getActive());

            resp.sendRedirect("index.jsp");
        } else {

            req.setAttribute("errorLogin", "Wrong email or password");

            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }
}

