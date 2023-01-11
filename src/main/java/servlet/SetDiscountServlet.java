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

@WebServlet("/SetDiscountServlet")
public class SetDiscountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SetDiscountServlet doGet");



    }
}
