package servlet;

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

import java.io.IOException;

@WebServlet("/LoginValidator")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO userDAO = new UserDAOImpl();
        User user = null;
        try {
            user = userDAO.getUserByEmail(req.getParameter("email"));
        } catch (DatabaseException e) {
            System.out.println("Get user fault");
            throw new RuntimeException(e);
        }
        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession session = req.getSession(true);
            session.setAttribute("UserId", user.getId());
            session.setAttribute("UserFirstName", user.getFirstName());
            session.setAttribute("UserLastName", user.getLastLame());
            session.setAttribute("UserEmail", user.getEmail());
            session.setAttribute("UserPhone", user.getPhone());
            session.setAttribute("UserRole", user.getRole());

            resp.sendRedirect("index.jsp");
        } else {

            req.setAttribute("errorLogin", "Wrong email or password");

            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }
}

