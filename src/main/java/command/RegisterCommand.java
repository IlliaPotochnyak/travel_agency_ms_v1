package command;

import DTO.UserDTO;
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
