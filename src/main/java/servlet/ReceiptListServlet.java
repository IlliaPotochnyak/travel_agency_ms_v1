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
//        System.out.println("ReceiptListServlet doGet");

        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        int userId = (int) req.getSession().getAttribute("UserId");

        ReceiptDao receiptDao = new ReceiptDAOImpl();
        List<Receipt> receiptList = null;
        try {
            if (req.getSession().getAttribute("UserRole").equals("admin") ||
                    req.getSession().getAttribute("UserRole").equals("manager")) {

                receiptList = receiptDao.getAllReceipts((page-1)*recordsPerPage, recordsPerPage);
            } else {

                receiptList = receiptDao.getAllUserReceiptsByUserId(userId,
                        (page-1)*recordsPerPage, recordsPerPage);
            }

            int noOfRecords = receiptDao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);


        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("receiptList", receiptList);
//        req.getRequestDispatcher("Cabinet.jsp").forward(req, resp);
        req.getRequestDispatcher("WEB-INF/view/ListReceipt.jsp").include(req, resp);
    }
}
