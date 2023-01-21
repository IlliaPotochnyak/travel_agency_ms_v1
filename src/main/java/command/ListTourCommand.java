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
    public String execute(HttpServletRequest request) {
        System.out.println("ListTourCommand");
        System.out.println("page- " + request.getParameter("page"));
        String pagePath = null;

        List<TourDTO> listOfTourDTO = null;
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
//        System.out.println("Query - " + request.getQueryString());
//        TourDAOImpl tourDAO = new TourDAOImpl();
        TourService tourService = new TourService();
        if (StringUtils.isNullOrEmpty(request.getParameter("tour_type"))  &&
                StringUtils.isNullOrEmpty(request.getParameter("price")) &&
                StringUtils.isNullOrEmpty(request.getParameter("people_amount")) &&
                StringUtils.isNullOrEmpty(request.getParameter("hotel_stars") ))
        {
//                System.out.println("");
            listOfTourDTO = tourService.getAll((page-1)*recordsPerPage, recordsPerPage);

        } else {
            listOfTourDTO = tourService.getSorted(request.getParameter("tour_type"),
                    request.getParameter("price"),
                    request.getParameter("people_amount"),
                    request.getParameter("hotel_stars"),
                    (page-1)*recordsPerPage, recordsPerPage
            );
        }
        int noOfRecords = tourService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//            request.setAttribute("employeeList", listOfTourDTO);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
//        listOfTourDTO.forEach(System.out::println);

        request.setAttribute("command", "commandshifted");
        request.setAttribute("tourList", listOfTourDTO);
        request.setAttribute("list", "listattr");
        System.out.println("Success tourList");
//        request.getRequestDispatcher("WEB-INF/view/ListTour.jsp").forward(request, resp);
//        request.getRequestDispatcher("WEB-INF/view/ListTour.jsp").include(request, resp);
        pagePath = "/WEB-INF/view/ListTour.jsp";




        return pagePath;
    }
}
