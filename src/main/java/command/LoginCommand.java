package command;

import DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import service.UserService;

public class LoginCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("LoginCommand");
        String page = null;

        UserService userService = new UserService();
        UserDTO userDTO = userService.loginUser(req.getParameter("email"), req.getParameter("password"));

        if (userDTO != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("UserId", userDTO.getId());
            session.setAttribute("UserFirstName", userDTO.getFirstName());
            session.setAttribute("UserLastName", userDTO.getLastName());
            session.setAttribute("UserEmail", userDTO.getEmail());
            session.setAttribute("UserPhone", userDTO.getPhone());
            session.setAttribute("UserRole", userDTO.getRole());
            session.setAttribute("UserActive", userDTO.getActive());

            page = "/index.jsp";
        } else {

            req.getSession().setAttribute("errorMessage", "Wrong email or password");

            page = "/Login.jsp";
        }

        return page;
    }
}
