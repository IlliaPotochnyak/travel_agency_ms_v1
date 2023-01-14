package servlet;

import DTO.TourDTO;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TourService;

import java.io.IOException;
import java.util.List;

@WebServlet("/TourList")
public class TourListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TourList servlet Get method");
        List<TourDTO> listOfTourDTO = null;
        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        System.out.println("Query - " + req.getQueryString());
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
        System.out.println("Success");
        req.setAttribute("tourList", listOfTourDTO);

//        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").forward(req, resp);
        req.getRequestDispatcher("WEB-INF/view/ListTour.jsp").include(req, resp);

    }
}
