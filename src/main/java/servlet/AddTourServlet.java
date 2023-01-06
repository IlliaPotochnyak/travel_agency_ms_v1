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

        if (addTourFormCheck(req)){
            //String name, String description, int personsNumber,
            //                int price, int hot, String tourType, int hotelType

            Tour newTour = new Tour(req.getParameter("tourName"),
                    req.getParameter("tourDescription"),
                    Integer.parseInt(req.getParameter("PersonNumber")),
                    Integer.parseInt(req.getParameter("tourPrice")),
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

                    req.getRequestDispatcher("AddTourOk.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        req.setAttribute("errorAddTour", "Wrong Tour data");

        req.getRequestDispatcher("AddTour.jsp").forward(req, resp);
    }

    private boolean addTourFormCheck(HttpServletRequest req) {

        String regexName = ".{2,64}";
        String regexDesc = ".{2,65500}";
        String regexPersonNumber = "^[0-9]{1,4}";
        String regexTourPrice = "^[1-9][0-9]{1,8}";
        String regexHotelType = "[1-5]{1}";
        String regexTourHot = "[0-1]{1}";
        String[] tourTypes = {"rest", "excursion", "shopping"};
        System.out.println(req.getParameter("tourType"));

        if ( !req.getParameter("tourName").matches(regexName) ) {
            System.out.println("Wrong tour name");
            return false;
        }
        if ( !req.getParameter("tourDescription").matches(regexDesc) ) {
            System.out.println("Wrong tour Desc");
            return false;
        }
        if ( !req.getParameter("PersonNumber").matches(regexPersonNumber) ) {
            System.out.println("Wrong person number");
            return false;
        }
        if ( !req.getParameter("tourPrice").matches(regexTourPrice) ) {
            System.out.println("Wrong tour price");
            return false;
        }
//        if ( !req.getParameter("tourHot").matches(regexTourPrice) ) {
//            System.out.println("Wrong tour Hot");
//            return false;
//        }
        if ( !Arrays.stream(tourTypes).anyMatch (req.getParameter("tourType")::equals)) {
            System.out.println("Wrong tour type");
            return false;
        }

        return true;
    }
}

