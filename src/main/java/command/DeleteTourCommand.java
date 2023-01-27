package command;

import DTO.UserDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.TourDAO;
import jakarta.servlet.http.HttpServletRequest;
import service.TourService;
import service.UserService;
import util.FormCheckUtils;

public class DeleteTourCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("DeleteTourCommand");
        String page = "/index.jsp";

        TourDAO tourDAO = new TourDAOImpl();
        TourService tourService = new TourService(tourDAO);
        tourService.delete(Integer.parseInt(req.getParameter("tourId")));

        return page;
    }
}
