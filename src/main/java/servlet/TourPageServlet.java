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

@WebServlet("/TourPageServlet")
public class TourPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TourPageServlet servlet Get method");

        TourDAOImpl tourDAO = new TourDAOImpl();
        Tour tour = null;
        try {
            tour = tourDAO.getTourById(Integer.parseInt(req.getParameter("tourId")));
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(tour);
        req.setAttribute("tour", tour);

//        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").forward(req, resp);
//        req.getRequestDispatcher("TourPage.jsp").include(req, resp);
        req.getRequestDispatcher("TourPage.jsp").forward(req, resp);

    }


}
