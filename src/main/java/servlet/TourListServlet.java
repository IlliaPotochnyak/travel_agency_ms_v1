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

@WebServlet("/TourList")
public class TourListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TourList servlet Get method");
        List<Tour> listOfTours;
//        System.out.println("Query - " + req.getQueryString());
        TourDAOImpl tourDAO = new TourDAOImpl();
        try {
            if (req.getQueryString() == null) {
                listOfTours = tourDAO.getAllTours();
            } else {
                listOfTours = tourDAO.getSortedTours(req.getParameter("tour_type"),
                        req.getParameter("price"),
                        req.getParameter("people_amount"),
                        req.getParameter("hotel_stars")
                        );
            }
        } catch (DatabaseException e) {
            System.out.println("Exception");
            throw new RuntimeException(e);
        }
        System.out.println("Success");
        req.setAttribute("tourList", listOfTours);

//        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").forward(req, resp);
        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").include(req, resp);

    }
}
