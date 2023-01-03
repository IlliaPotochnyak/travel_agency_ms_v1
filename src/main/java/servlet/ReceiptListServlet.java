package servlet;

import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.interfaces.ReceiptDao;
import entities.Receipt;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ReceiptListServlet")
public class ReceiptListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("UserId");

        ReceiptDao receiptDao = new ReceiptDAOImpl();
        List<Receipt> receiptList = null;
        try {
            if ((int) req.getSession().getAttribute("UserRole") == 1 ||
                    (int) req.getSession().getAttribute("UserRole") == 2) {

                receiptList = receiptDao.getAllReceipts();
            } else {

                receiptList = receiptDao.getAllUserReceiptsByUserId(userId);
            }


        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("receiptList", receiptList);
//        req.getRequestDispatcher("Cabinet.jsp").forward(req, resp);
        req.getRequestDispatcher("WEB-INF/view/ListReceipt.jsp").include(req, resp);
    }
}
