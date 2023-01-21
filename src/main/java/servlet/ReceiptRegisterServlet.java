package servlet;

import DTO.ReceiptDTO;
import DTO.TourDTO;
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
import service.TourService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ReceiptRegister")
public class ReceiptRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        System.out.println("ReceiptRegister doPost method");
////        System.out.println(req.getParameter("tourId"));
////        System.out.println(req.getSession().getAttribute("UserId"));
////        TourDAO tourDAO = new TourDAOImpl();
//
////        if(req.getSession().getAttribute("UserActive").equals("1")) {
//            System.out.println("active user");
//            ReceiptService receiptService = new ReceiptService();
////            TourDTO tourDTO = null;
////            int userId = (int) req.getSession().getAttribute("UserId");
//            //        int discount = 0;
//            //        tourDTO = tourService.getById(Integer.parseInt(req.getParameter("tourId")));
//
//            //        int amount = tourDTO.getPrice() - (tourDTO.getPrice() * (discount / 100));
//            //        Receipt receipt = new Receipt(tour.getId(), userId,
//            //                                    discount, amount, "registered");
//            ReceiptDTO receiptDTO = new ReceiptDTO();
//            receiptDTO.setUserId((Integer) req.getSession().getAttribute("UserId"));
//            receiptDTO.setTourId(Integer.parseInt(req.getParameter("tourId")));
//        System.out.println(receiptDTO);
//
//            //        ReceiptDao receiptDao = new ReceiptDAOImpl();
//            if (receiptService.add(receiptDTO)) {
//                req.getRequestDispatcher("ReceiptOk.jsp").forward(req, resp);
////            }
//        }
    }
}
