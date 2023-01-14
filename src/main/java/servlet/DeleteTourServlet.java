package servlet;

import DTO.TourDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.TourDAO;
import entities.Tour;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TourService;
import util.FormCheckUtils;

import java.io.IOException;

@WebServlet("/DeleteTourServlet")
public class DeleteTourServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("DeleteTourServlet");
////        System.out.println(req.getParameter("tourId") + " - " + req.getParameter("tourName"));
//        System.out.println("tourId" + " - " + req.getParameter("tourId"));

//        TourDAO tourDAO = new TourDAOImpl();
        TourService tourService = new TourService();
        tourService.delete(Integer.parseInt(req.getParameter("tourId")));

        resp.sendRedirect("index.jsp");


    }
}

