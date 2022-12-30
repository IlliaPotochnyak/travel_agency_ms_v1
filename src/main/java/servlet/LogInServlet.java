package servlet;

import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import entities.Tour;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoginValidator")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.getWriter().append("<html>")
                .append("<body>")
                .append("You entered ")
                .append(req.getParameter("email"))
                .append(" email")
                .append("<body>")
                .append("</html>");
    }
}

