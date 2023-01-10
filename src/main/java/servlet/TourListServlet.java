package servlet;

import com.mysql.cj.util.StringUtils;
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
        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
//        System.out.println("Query - " + req.getQueryString());
        TourDAOImpl tourDAO = new TourDAOImpl();
        try {
            if (StringUtils.isNullOrEmpty(req.getParameter("tour_type"))  &&
                    StringUtils.isNullOrEmpty(req.getParameter("price")) &&
                    StringUtils.isNullOrEmpty(req.getParameter("people_amount")) &&
                    StringUtils.isNullOrEmpty(req.getParameter("hotel_stars") ))
            {
                System.out.println("");
                listOfTours = tourDAO.getAllTours((page-1)*recordsPerPage, recordsPerPage);

            } else {
                listOfTours = tourDAO.getSortedTours(req.getParameter("tour_type"),
                        req.getParameter("price"),
                        req.getParameter("people_amount"),
                        req.getParameter("hotel_stars"),
                        (page-1)*recordsPerPage, recordsPerPage
                        );
            }
            int noOfRecords = tourDAO.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//            req.setAttribute("employeeList", listOfTours);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
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
