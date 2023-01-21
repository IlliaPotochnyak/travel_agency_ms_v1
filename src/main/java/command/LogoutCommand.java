package command;

import DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import service.UserService;

public class LogoutCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("LogoutCommand");
        String page = "/index.jsp";

        HttpSession session = req.getSession(false);
        if(session != null)
            session.invalidate();

        return page;
    }
}
