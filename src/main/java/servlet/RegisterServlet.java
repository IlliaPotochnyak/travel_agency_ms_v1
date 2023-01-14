package servlet;

import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import entities.User;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import util.FormCheckUtils;

import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet Post method");
        System.out.println(req.getParameter("password"));
        if (FormCheckUtils.registerFormCheck(req)){
            //String first_name, String last_name, String email, String password, String phone, int active, int role
//            User newUser = new User(req.getParameter("firstName"),
//                    req.getParameter("lastName"),
//                    req.getParameter("email"),
//                    req.getParameter("password"),
//                    req.getParameter("phone"),
//                    1, "client");
//            UserDAO userDAO = new UserDAOImpl();
            UserDTO newUserDTO = new UserDTO();
            newUserDTO.setFirstName(req.getParameter("firstName"));
            newUserDTO.setLastName(req.getParameter("lastName"));
            newUserDTO.setEmail(req.getParameter("email"));
            newUserDTO.setPassword(req.getParameter("password"));
            newUserDTO.setPhone(req.getParameter("phone"));
//            System.out.println(newUserDTO);

            UserService userService = new UserService();


            if (userService.add(newUserDTO)) {
//                    HttpSession session = req.getSession(true);
//                    session.setAttribute("UserFirstName", newUser.getFirstName());
//                    session.setAttribute("UserLastName", newUser.getLastLame());
//                    session.setAttribute("UserRole", newUser.getRole());
//                    session.setAttribute("UserId", newUser.getId());
                System.out.println("Register ok");
//                    req.getRequestDispatcher("RegisterOK.jsp").forward(req, resp);
                resp.sendRedirect("RegisterOK.jsp");
            }

        } else {
            req.setAttribute("errorRegister", "Wrong Registration data");

            req.getRequestDispatcher("Register.jsp").forward(req, resp);
        }
    }


}

