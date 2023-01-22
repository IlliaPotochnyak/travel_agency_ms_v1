package command;

import DTO.TourDTO;
import DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import service.TourService;
import service.UserService;
import util.FormCheckUtils;

public class AddTourCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("AddTourCommand");
        String page = null;

        int tourIsHot;
        if (req.getParameter("tourHot") == null) {
            tourIsHot = 0;
        } else {
            tourIsHot = 1;
        }

        if (FormCheckUtils.addTourFormCheck(req)) {

            TourDTO newTour = new TourDTO();
            newTour.setName(req.getParameter("tourName"));
            newTour.setDescription(req.getParameter("tourDescription"));
            newTour.setPersonsNumber(Integer.parseInt(req.getParameter("PersonNumber")));
            newTour.setPrice(Integer.parseInt(req.getParameter("tourPrice")));
            newTour.setMaxDiscount(Integer.parseInt(req.getParameter("maxDiscount")));
            newTour.setHot(tourIsHot);
            newTour.setTourType(req.getParameter("tourType"));
            newTour.setHotelType(Integer.parseInt(req.getParameter("hotelType")));

//            TourDAO tourDAO = new TourDAOImpl();
            TourService tourService = new TourService();

            if (tourService.add(newTour)) {

                page = "/AddTourOk.jsp";
//                System.out.println("After redirect");
            }
        }else {
            System.out.println("errorAddTour, Wrong Tour data");
            req.getSession().setAttribute("errorMessage", "Wrong Tour data");

            page = "/AddTour.jsp";
        }
        return page;
    }
}
