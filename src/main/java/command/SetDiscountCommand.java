package command;

import DTO.ReceiptDTO;
import DTO.TourDTO;
import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.ReceiptDAOImpl;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.ReceiptDao;
import db.dao.interfaces.TourDAO;
import jakarta.servlet.http.HttpServletRequest;
import service.ReceiptService;
import service.TourService;
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
        TourDAO tourDAO = new TourDAOImpl();
        TourDTO tourDTO = new TourService(tourDAO).getById(receiptDTO.getTourId());
        receiptService.updateReceiptDiscount(receiptDTO, tourDTO);


        pagePath = "/controller?command=receipt_list";

        return pagePath;
    }
}
