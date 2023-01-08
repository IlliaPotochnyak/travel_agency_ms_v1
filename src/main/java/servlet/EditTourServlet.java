package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EditTourServlet")
public class EditTourServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditTourServlet");
        System.out.println(req.getParameter("tourName"));
        System.out.println(req.getParameter("tourDescription"));
        System.out.println(req.getParameter("tourHot"));
        System.out.println(req.getParameter("tourType"));
        System.out.println(req.getParameter("hotelType"));
        System.out.println(req.getParameter("PersonNumber"));
        System.out.println(req.getParameter("tourPrice"));
        System.out.println(req.getParameter("tourId"));
    }
}
