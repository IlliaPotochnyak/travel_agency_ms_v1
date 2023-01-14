package servlet;

import DTO.TourDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TourITourService;

import java.io.IOException;

@WebServlet("/TourPageServlet")
public class TourPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TourPageServlet servlet Get method");

//        TourDAOImpl tourDAO = new TourDAOImpl();
//        Tour tour = null;
        TourDTO tourDTO;
        TourITourService service = new TourITourService();

        tourDTO = service.getById(Integer.parseInt(req.getParameter("tourId")));
        System.out.println(tourDTO);
        req.setAttribute("tour", tourDTO);

//        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").forward(req, resp);
//        req.getRequestDispatcher("TourPage.jsp").include(req, resp);
        req.getRequestDispatcher("TourPage.jsp").forward(req, resp);

    }


}
