package servlet;

import DTO.TourDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.TourDAO;
import db.dao.interfaces.UserDAO;
import entities.Tour;
import entities.User;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TourService;
import util.FormCheckUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/AddTourServlet")
public class AddTourServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddTourServlet Post method");

        int tourIsHot;
        if (req.getParameter("tourHot") == null) { tourIsHot = 0; }
        else { tourIsHot = 1; }

        if (FormCheckUtils.addTourFormCheck(req)){

            TourDTO newTour = new TourDTO();
            newTour.setName(req.getParameter("tourName"));
            newTour.setDescription(req.getParameter("tourDescription"));
            newTour.setPersonsNumber(Integer.parseInt(req.getParameter("PersonNumber")));
            newTour.setPrice(Integer.parseInt(req.getParameter("tourPrice")));
            newTour.setMaxDiscount(Integer.parseInt(req.getParameter("maxDiscount")));
            newTour.setHot(tourIsHot);
            newTour.setTourType(req.getParameter("tourType"));
            newTour.setHotelType(Integer.parseInt(req.getParameter("hotelType")));

//            TourDAO tourDAO = new TourDAOImpl();
            TourService tourService = new TourService();

            if(tourService.add(newTour)) {
//            System.out.println(newTour);
//                System.out.println("Add - ok");
//                    req.getRequestDispatcher("AddTourOk.jsp").forward(req, resp);
                resp.sendRedirect("AddTourOk.jsp");
//                System.out.println("After redirect");
            } else {
                req.setAttribute("errorAddTour", "Wrong Tour data");

                req.getRequestDispatcher("AddTour.jsp").forward(req, resp);
            }


        }
//        System.out.println("End of servlet");
    }


}

