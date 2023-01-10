package servlet;

import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.ReceiptDao;
import db.dao.interfaces.UserDAO;
import entities.Receipt;
import entities.User;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserListServlet doGet");

        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("pageUsers") != null)
            page = Integer.parseInt(req.getParameter("pageUsers"));

        int userId = (int) req.getSession().getAttribute("UserId");

        UserDAO userDAO = new UserDAOImpl();
        List<User> userList = null;
        try {
            if (req.getSession().getAttribute("UserRole").equals("admin")) {

                userList = userDAO.getAllUsers((page-1)*recordsPerPage, recordsPerPage);
            }

            int noOfRecords = userDAO.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("noOfPagesUsers", noOfPages);
            req.setAttribute("currentPageUsers", page);


        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("userList", userList);
//        req.getRequestDispatcher("Cabinet.jsp").forward(req, resp);
        req.getRequestDispatcher("WEB-INF/view/ListUsers.jsp").include(req, resp);
    }
}
