package command;

import DTO.TourDTO;
import db.dao.DAOImpl.MySQLImpl.TourDAOImpl;
import db.dao.interfaces.TourDAO;
import jakarta.servlet.http.HttpServletRequest;
import service.TourService;
import util.FormCheckUtils;

public class TourPageCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("TourPageCommand");
        String page = "/index.jsp";

        TourDTO tourDTO;
        TourDAO tourDAO = new TourDAOImpl();
        TourService service = new TourService(tourDAO);

        tourDTO = service.getById(Integer.parseInt(req.getParameter("tourId")));
        if (tourDTO != null) {
//        System.out.println(tourDTO);
            req.setAttribute("tour", tourDTO);
            page = "/TourPage.jsp";
        } else {
            req.getSession().setAttribute("errorMessage", "Wrong tour data");
            page = "/error.jsp";
        }

        return page;
    }
}
