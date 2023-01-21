package command;

import DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import service.TourService;
import service.UserService;
import util.FormCheckUtils;

public class DeleteTourCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("DeleteTourCommand");
        String page = "/index.jsp";

        TourService tourService = new TourService();
        tourService.delete(Integer.parseInt(req.getParameter("tourId")));

        return page;
    }
}