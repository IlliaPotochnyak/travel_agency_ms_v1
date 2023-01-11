package servlet;

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
import util.FormCheckUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/AddTourServlet")
public class AddTourServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddTourServlet Post method");

        System.out.println("AddTourServlet Post method");


        int tourIsHot;
        if (req.getParameter("tourHot") == null) { tourIsHot = 0; }
        else { tourIsHot = 1; }

        if (FormCheckUtils.addTourFormCheck(req)){
            //String name, String description, int personsNumber,
            //                int price, int hot, String tourType, int hotelType
            System.out.println("maxDisc = " + req.getParameter("maxDiscount"));

            Tour newTour = new Tour(req.getParameter("tourName"),
                    req.getParameter("tourDescription"),
                    Integer.parseInt(req.getParameter("PersonNumber")),
                    Integer.parseInt(req.getParameter("tourPrice")),
                    Integer.parseInt(req.getParameter("maxDiscount")),
                    tourIsHot,
                    req.getParameter("tourType"),
                    Integer.parseInt(req.getParameter("hotelType"))
                    );
            TourDAO tourDAO = new TourDAOImpl();
//            System.out.println(newTour);

            try {
                if (tourDAO.addTour(newTour)) {
//                    HttpSession session = req.getSession(true);
//                    session.setAttribute("UserFirstName", newUser.getFirstName());
//                    session.setAttribute("UserLastName", newUser.getLastLame());
//                    session.setAttribute("UserRole", newUser.getRole());
//                    session.setAttribute("UserId", newUser.getId());
                    System.out.println("Add - ok");

//                    req.getRequestDispatcher("AddTourOk.jsp").forward(req, resp);
                    resp.sendRedirect("AddTourOk.jsp");
                    System.out.println("After redirect");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {

        req.setAttribute("errorAddTour", "Wrong Tour data");

        req.getRequestDispatcher("AddTour.jsp").forward(req, resp);
        }
        System.out.println("End of servlet");
    }


}

