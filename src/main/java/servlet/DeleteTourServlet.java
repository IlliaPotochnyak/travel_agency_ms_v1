package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TourITourService;

import java.io.IOException;

@WebServlet("/DeleteTourServlet")
public class DeleteTourServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("DeleteTourServlet");
////        System.out.println(req.getParameter("tourId") + " - " + req.getParameter("tourName"));
//        System.out.println("tourId" + " - " + req.getParameter("tourId"));

//        TourDAO tourDAO = new TourDAOImpl();
        TourITourService tourService = new TourITourService();
        tourService.delete(Integer.parseInt(req.getParameter("tourId")));

        resp.sendRedirect("index.jsp");


    }
}

