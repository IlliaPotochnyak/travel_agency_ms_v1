package servlet;

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

import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet Post method");
        System.out.println(req.getParameter("password"));
        if (registerFormCheck(req)){
            //String first_name, String last_name, String email, String password, String phone, int active, int role
            User newUser = new User(req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    req.getParameter("phone"),
                    1, "client");
            UserDAO userDAO = new UserDAOImpl();
            System.out.println(newUser);

            try {
                if (userDAO.addUser(newUser)) {
//                    HttpSession session = req.getSession(true);
//                    session.setAttribute("UserFirstName", newUser.getFirstName());
//                    session.setAttribute("UserLastName", newUser.getLastLame());
//                    session.setAttribute("UserRole", newUser.getRole());
//                    session.setAttribute("UserId", newUser.getId());
                    System.out.println("Register ok");
                    req.getRequestDispatcher("RegisterOK.jsp").forward(req, resp);
                }
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }

        }
        req.setAttribute("errorRegister", "Wrong Registration data");

        req.getRequestDispatcher("Register.jsp").forward(req, resp);
    }

    private boolean registerFormCheck (HttpServletRequest req) {

        String regexName = "^[A-Za-z' А-Яа-яіІїЇ]{2,40}";
        String regexPassword = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$";
        String regexEmail = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$";
        String regexPhone = "^[+][0-9]{2}-[0-9]{3}-[0-9]{3}-[0-9]{4}";

        if ( !req.getParameter("firstName").matches(regexName) ) return false;
        if ( !req.getParameter("lastName").matches(regexName) ) return false;
        if ( !req.getParameter("password").matches(regexPassword) ) return false;
        if ( !req.getParameter("email").matches(regexEmail) ) return false;
        if ( !req.getParameter("phone").matches(regexPhone) ) return false;


        return true;
    }
}

