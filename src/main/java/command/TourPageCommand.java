package command;

import DTO.TourDTO;
import jakarta.servlet.http.HttpServletRequest;
import service.TourService;
import util.FormCheckUtils;

public class TourPageCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("TourPageCommand");
        String page = "/index.jsp";

        TourDTO tourDTO;
        TourService service = new TourService();

        tourDTO = service.getById(Integer.parseInt(req.getParameter("tourId")));
        System.out.println(tourDTO);
        req.setAttribute("tour", tourDTO);


        page = "/TourPage.jsp";

        return page;
    }
}
