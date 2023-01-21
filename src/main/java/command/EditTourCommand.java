package command;

import DTO.TourDTO;
import jakarta.servlet.http.HttpServletRequest;
import service.TourService;
import util.FormCheckUtils;

public class EditTourCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("EditTourCommand");
        String page = "/index.jsp";

        int tourIsHot;
        if (req.getParameter("tourHot") == null) { tourIsHot = 0; }
        else { tourIsHot = 1; }

        if(FormCheckUtils.addTourFormCheck(req)){


            TourDTO tourDTO = new TourDTO();
            tourDTO.setId(Integer.parseInt(req.getParameter("tourId")));
            tourDTO.setName(req.getParameter("tourName"));
            tourDTO.setDescription(req.getParameter("tourDescription"));
            tourDTO.setPersonsNumber(Integer.parseInt(req.getParameter("PersonNumber")));
            tourDTO.setPrice(Integer.parseInt(req.getParameter("tourPrice")));
            tourDTO.setMaxDiscount(Integer.parseInt(req.getParameter("maxDiscount")));
            tourDTO.setHot(tourIsHot);
            tourDTO.setTourType(req.getParameter("tourType"));
            tourDTO.setHotelType(Integer.parseInt(req.getParameter("hotelType")));

//            TourDAO tourDAO = new TourDAOImpl();
            TourService tourService = new TourService();

            if (tourService.update(tourDTO)) {

//                System.out.println("Update - ok");

//                    req.getRequestDispatcher("AddTourOk.jsp").forward(req, resp);
                page = "/controller?command=tour_page&tourId=" + req.getParameter("tourId");
//                System.out.println("After redirect");
            }

        } else {

            req.setAttribute("errorUpdateTour", "Wrong Tour data");

//            req.getRequestDispatcher("AddTour.jsp").forward(req, resp);
            page = "/controller?command=tour_page&tourId=" + req.getParameter("tourId");
        }

        return page;
    }
}
