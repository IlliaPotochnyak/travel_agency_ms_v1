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

@WebServlet("/roles")
public class RolesTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String SQL_QUERY = "SELECT * from role";
        System.out.println("roles Get Method");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery())
        {
//            List <String> roles = new ArrayList<>();
            StringBuilder roles = new StringBuilder();
            while (rs.next()) {
                roles.append(rs.getString("role")).append("<br>");
            }

            resp.getWriter()
                    .append("<html>")
                    .append("<body>")
                    .append("OK!!! go!")
                    .append("<hr>")
                    .append(roles)
                    .append("<body>")
                    .append("<html>");

        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
