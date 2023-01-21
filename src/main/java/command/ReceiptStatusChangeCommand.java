package command;

import DTO.ReceiptDTO;
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
        ReceiptService receiptService = new ReceiptService();

        receiptService.updateReceiptStatus(receiptDTO);

        pagePath = "/controller?command=receipt_list";

        return pagePath;
    }
}
