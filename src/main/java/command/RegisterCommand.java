package command;

import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.UserDAOImpl;
import db.dao.interfaces.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import util.FormCheckUtils;

public class RegisterCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("RegisterCommand");
        String page = null;

        if (FormCheckUtils.registerFormCheck(req)){

            UserDTO newUserDTO = new UserDTO();
            newUserDTO.setFirstName(req.getParameter("firstName"));
            newUserDTO.setLastName(req.getParameter("lastName"));
            newUserDTO.setEmail(req.getParameter("email"));
            newUserDTO.setPassword(req.getParameter("password"));
            newUserDTO.setPhone(req.getParameter("phone"));
//            System.out.println(newUserDTO);

            UserDAO userDAO = new UserDAOImpl();
            UserService userService = new UserService(userDAO);


            if (userService.add(newUserDTO)) {

                System.out.println("Register ok");
//                    req.getRequestDispatcher("RegisterOK.jsp").forward(req, resp);
                page = "/RegisterOK.jsp";
            }

        } else {
            req.setAttribute("errorRegister", "Wrong Registration data");

            page = "/Register.jsp";
        }
        return page;
    }
}
