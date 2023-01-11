package servlet;

import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.FormCheckUtils;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditTourServlet")
public class EditTourServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditTourServlet");
        System.out.println(req.getParameter("tourName"));
        System.out.println(req.getParameter("tourDescription"));
        System.out.println(req.getParameter("tourHot"));
        System.out.println(req.getParameter("tourType"));
        System.out.println(req.getParameter("hotelType"));
        System.out.println(req.getParameter("PersonNumber"));
        System.out.println(req.getParameter("tourPrice"));
        System.out.println(req.getParameter("tourId"));

        int tourIsHot;
        if (req.getParameter("tourHot") == null) { tourIsHot = 0; }
        else { tourIsHot = 1; }

        if(FormCheckUtils.addTourFormCheck(req)){
            //int id, String name, String description, int personsNumber,
            //                int price, int hot, String tourType, int hotelType
            Tour newTour = new Tour(Integer.parseInt(req.getParameter("tourId")),
                    req.getParameter("tourName"),
                    req.getParameter("tourDescription"),
                    Integer.parseInt(req.getParameter("PersonNumber")),
                    Integer.parseInt(req.getParameter("tourPrice")),
                    Integer.parseInt(req.getParameter("maxDiscount")),
                    tourIsHot,
                    req.getParameter("tourType"),
                    Integer.parseInt(req.getParameter("hotelType"))
            );
            TourDAO tourDAO = new TourDAOImpl();

            try {
                if (tourDAO.updateTour(newTour)) {
//                    HttpSession session = req.getSession(true);
//                    session.setAttribute("UserFirstName", newUser.getFirstName());
//                    session.setAttribute("UserLastName", newUser.getLastLame());
//                    session.setAttribute("UserRole", newUser.getRole());
//                    session.setAttribute("UserId", newUser.getId());
                    System.out.println("Update - ok");

//                    req.getRequestDispatcher("AddTourOk.jsp").forward(req, resp);
                    resp.sendRedirect("TourPageServlet?tourId=" + req.getParameter("tourId"));
                    System.out.println("After redirect");
                }
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }

        } else {

            req.setAttribute("errorUpdateTour", "Wrong Tour data");

//            req.getRequestDispatcher("AddTour.jsp").forward(req, resp);
            resp.sendRedirect("TourPageServlet?tourId=" + req.getParameter("tourId"));
        }
        System.out.println("End of servlet");
        }
    }

