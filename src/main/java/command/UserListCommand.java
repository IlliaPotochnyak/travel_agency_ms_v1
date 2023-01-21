package command;

import DTO.ReceiptDTO;
import DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import service.ReceiptService;
import service.UserService;

import java.util.List;

public class UserListCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("UserListCommand");
        String pagePath = "/index.jsp";

        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("pageUsers") != null)
            page = Integer.parseInt(req.getParameter("pageUsers"));

//        int userId = (int) req.getSession().getAttribute("UserId");

//        UserDAO userDAO = new UserDAOImpl();
        UserService userService = new UserService();
        List<UserDTO> userList = null;
        if (req.getSession().getAttribute("UserRole").equals("admin")) {

            userList = userService.getAll((page-1)*recordsPerPage, recordsPerPage);
        }

        int noOfRecords = userService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("noOfPagesUsers", noOfPages);
        req.setAttribute("currentPageUsers", page);


        req.setAttribute("userList", userList);
//        req.getRequestDispatcher("Cabinet.jsp").forward(req, resp);
        pagePath = "/WEB-INF/view/ListUsers.jsp";

        return pagePath;
    }
}
