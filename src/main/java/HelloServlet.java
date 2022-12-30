import db.DataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = "Stranger";
            if (req.getParameter("name") != null) {
                name = req.getParameter("name");
            }
            resp.getWriter()
                    .append("<html>")
                    .append("<body>")
                    .append("Hello, ")
                    .append(name)
                    .append("<hr>")
                    .append("<body>")
                    .append("<html>");


    }
}
