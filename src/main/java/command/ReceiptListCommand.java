package command;

import DTO.ReceiptDTO;
import DTO.TourDTO;
import jakarta.servlet.http.HttpServletRequest;
import service.ReceiptService;
import service.TourService;

import java.util.List;

public class ReceiptListCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("ReceiptListCommand");
        String pagePath = "/index.jsp";

        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        int userId = (int) req.getSession().getAttribute("UserId");
//        ReceiptDao receiptDao = new ReceiptDAOImpl();
        List<ReceiptDTO> receiptDTOList = null;
        ReceiptService receiptService = new ReceiptService();
        if (req.getSession().getAttribute("UserRole").equals("admin") ||
                req.getSession().getAttribute("UserRole").equals("manager")) {

            receiptDTOList = receiptService.getAll((page-1)*recordsPerPage, recordsPerPage);
        } else {
            receiptDTOList = receiptService.getAllByUserId(userId,
                    (page-1)*recordsPerPage, recordsPerPage);
        }

        int noOfRecords = receiptService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.setAttribute("receiptList", receiptDTOList);
//        req.getRequestDispatcher("Cabinet.jsp").forward(req, resp);
        pagePath = "/WEB-INF/view/ListReceipt.jsp";

        return pagePath;
    }
}
