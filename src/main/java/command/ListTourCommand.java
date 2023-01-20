package command;

import DTO.TourDTO;
import DTO.UserDTO;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import service.TourService;
import service.UserService;

import java.util.List;

public class ListTourCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        System.out.println("ListTourCommand");
        System.out.println("page - " + req.getParameter("page"));
        String pagePath = null;

        List<TourDTO> listOfTourDTO = null;
        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
//        System.out.println("Query - " + req.getQueryString());
//        TourDAOImpl tourDAO = new TourDAOImpl();
        TourService tourService = new TourService();
        if (StringUtils.isNullOrEmpty(req.getParameter("tour_type"))  &&
                StringUtils.isNullOrEmpty(req.getParameter("price")) &&
                StringUtils.isNullOrEmpty(req.getParameter("people_amount")) &&
                StringUtils.isNullOrEmpty(req.getParameter("hotel_stars") ))
        {
//                System.out.println("");
            listOfTourDTO = tourService.getAll((page-1)*recordsPerPage, recordsPerPage);

        } else {
            listOfTourDTO = tourService.getSorted(req.getParameter("tour_type"),
                    req.getParameter("price"),
                    req.getParameter("people_amount"),
                    req.getParameter("hotel_stars"),
                    (page-1)*recordsPerPage, recordsPerPage
            );
        }
        int noOfRecords = tourService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//            req.setAttribute("employeeList", listOfTourDTO);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
//        listOfTourDTO.forEach(System.out::println);

        req.setAttribute("tourList", listOfTourDTO);
        System.out.println("Success tourList");
//        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").forward(req, resp);
//        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").include(req, resp);
        pagePath = "/WEB-INF/view/ListTour.jsp";




        return pagePath;
    }
}
