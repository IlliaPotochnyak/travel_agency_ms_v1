package servlet;

import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.ReceiptDao;
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

@WebServlet("/ReceiptStatusServlet")
public class ReceiptStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("ReceiptStatusServlet Post method");
//
//        System.out.println("receiptId" + " - " + req.getParameter("receiptId"));
//        System.out.println("orderStatus" + " - " + req.getParameter("orderStatus"));

        ReceiptDao receiptDao = new ReceiptDAOImpl();

        try {
            receiptDao.updateReceiptStatus(Integer.parseInt(req.getParameter("receiptId")),
                    req.getParameter("orderStatus"));

            resp.sendRedirect("Cabinet.jsp");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }


    }


}

