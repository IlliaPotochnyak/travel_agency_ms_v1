package command;

import DTO.ReceiptDTO;
import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.interfaces.ReceiptDao;
import jakarta.servlet.http.HttpServletRequest;
import service.ReceiptService;
import service.UserService;

import java.util.List;

public class ReceiptRegisterCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("ReceiptRegisterCommand");
        String pagePath = "/index.jsp";

        ReceiptDao receiptDao = new ReceiptDAOImpl();
        ReceiptService receiptService = new ReceiptService(receiptDao);

        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setUserId((Integer) req.getSession().getAttribute("UserId"));
        receiptDTO.setTourId(Integer.parseInt(req.getParameter("tourId")));

        if (receiptService.add(receiptDTO)) {
            pagePath = "/ReceiptOk.jsp";
        }

        return pagePath;
    }
}
