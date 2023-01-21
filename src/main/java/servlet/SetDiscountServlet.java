package servlet;

import DTO.ReceiptDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.ReceiptDao;
import db.dao.interfaces.TourDAO;
import entities.Receipt;
import entities.Tour;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ReceiptService;

import java.io.IOException;
import java.util.List;

@WebServlet("/SetDiscountServlet")
public class SetDiscountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        System.out.println("SetDiscountServlet doGet");
////        System.out.println("receiptId = " + req.getParameter("receiptId"));
////        System.out.println("discount = " + req.getParameter("discount"));
//
//        int receiptId = Integer.parseInt(req.getParameter("receiptId"));
//        int tourId = Integer.parseInt(req.getParameter("tourId"));
//        int discount = Integer.parseInt(req.getParameter("discount"));
//
//        ReceiptDTO receiptDTO = new ReceiptDTO();
//        receiptDTO.setId(receiptId);
//        receiptDTO.setTourId(tourId);
//        receiptDTO.setDiscount(discount);
//
//        ReceiptService receiptService = new ReceiptService();
//        receiptService.updateReceiptDiscount(receiptDTO);
//
////        try {
////            Tour tour = new TourDAOImpl().getTourById(tourId);
////            int amount = tour.getPrice() - (tour.getPrice() * discount / 100);
//////            System.out.println("calc amount  = " + amount);
////
////            if (discount >= 0 && discount <= tour.getMaxDiscount()) {
////                ReceiptDao receiptDao = new ReceiptDAOImpl();
////                receiptDao.updateReceiptDiscount(receiptId, discount, amount);
////            }
////
////        } catch (DatabaseException e) {
////            throw new RuntimeException(e);
////        }
//        resp.sendRedirect("Cabinet.jsp");

    }
}
