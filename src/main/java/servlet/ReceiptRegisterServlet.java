package servlet;

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

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ReceiptRegister")
public class ReceiptRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ReceiptRegister doGet method");
//        System.out.println(req.getParameter("tourId"));
//        System.out.println(req.getSession().getAttribute("UserId"));
        TourDAO tourDAO = new TourDAOImpl();
        Tour tour = null;
        int userId = (int) req.getSession().getAttribute("UserId");
        int discount = 0;

        try {
            tour = tourDAO.getTourById(Integer.parseInt((req.getParameter("tourId"))));
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        int amount = tour.getPrice() - (tour.getPrice() * (discount / 100));
        Receipt receipt = new Receipt(tour.getId(), userId,
                                    discount, amount, "registered");
        ReceiptDao receiptDao = new ReceiptDAOImpl();
        try {
            if(receiptDao.addReceipt(receipt)) {
                req.getRequestDispatcher("ReceiptOk.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
