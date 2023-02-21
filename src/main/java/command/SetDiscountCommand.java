package command;

import DTO.ReceiptDTO;
import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.interfaces.ReceiptDao;
import jakarta.servlet.http.HttpServletRequest;
import service.ReceiptService;
import service.UserService;

import java.util.List;

public class SetDiscountCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("SetDiscountCommand");
        String pagePath = "/index.jsp";

        int receiptId = Integer.parseInt(req.getParameter("receiptId"));
        int tourId = Integer.parseInt(req.getParameter("tourId"));
        int discount = Integer.parseInt(req.getParameter("discount"));

        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setId(receiptId);
        receiptDTO.setTourId(tourId);
        receiptDTO.setDiscount(discount);

        ReceiptDao receiptDao = new ReceiptDAOImpl();
        ReceiptService receiptService = new ReceiptService(receiptDao);
        receiptService.updateReceiptDiscount(receiptDTO);


        pagePath = "/controller?command=receipt_list";

        return pagePath;
    }
}
