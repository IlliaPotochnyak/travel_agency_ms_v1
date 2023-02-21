package command;

import DTO.ReceiptDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.interfaces.ReceiptDao;
import jakarta.servlet.http.HttpServletRequest;
import service.ReceiptService;

public class ReceiptStatusChangeCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("ReceiptStatusChangeCommand");
        String pagePath = "/index.jsp";


        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setId(Integer.parseInt(req.getParameter("receiptId")));
        receiptDTO.setOrderStatus(req.getParameter("orderStatus"));
        ReceiptDao receiptDao = new ReceiptDAOImpl();
        ReceiptService receiptService = new ReceiptService(receiptDao);

        receiptService.updateReceiptStatus(receiptDTO);

        pagePath = "/controller?command=receipt_list";

        return pagePath;
    }
}
