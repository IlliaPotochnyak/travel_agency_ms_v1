package command;

import DTO.ReceiptDTO;
import DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import service.ReceiptService;
import service.UserService;

import java.util.List;

public class ReceiptRegisterCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("ReceiptRegisterCommand");
        String pagePath = "/index.jsp";

        ReceiptService receiptService = new ReceiptService();

        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setUserId((Integer) req.getSession().getAttribute("UserId"));
        receiptDTO.setTourId(Integer.parseInt(req.getParameter("tourId")));

        if (receiptService.add(receiptDTO)) {
            pagePath = "/ReceiptOk.jsp";
        }

        return pagePath;
    }
}
