package util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class FormCheckUtils {
    public static boolean addTourFormCheck(HttpServletRequest req) {
        if(req == null) return false;

        String regexName = ".{2,64}";
        String regexDesc = ".{2,65500}";
        String regexPersonNumber = "^[1-9][0-9]?";
        String regexTourPrice = "^[1-9][0-9]{1,8}";
        String regexTourDiscount = "[0-9]{1,2}";
        String regexHotelType = "[1-5]{1}";
        String regexTourHot = "[0-1]{1}";
        String[] tourTypes = {"rest", "excursion", "shopping"};
//        System.out.println(req.getParameter("tourType"));

        if ( req.getParameter("tourName") == null || !req.getParameter("tourName").matches(regexName) ) {
            System.out.println("Wrong tour name");
            return false;
        }
        if ( req.getParameter("tourDescription") == null || !req.getParameter("tourDescription").trim().matches(regexDesc)) {
            System.out.println("Wrong tour Desc");
            return false;
        }
        if ( req.getParameter("PersonNumber") == null || !req.getParameter("PersonNumber").matches(regexPersonNumber)) {
            System.out.println("Wrong person number");
            return false;
        }
        if ( req.getParameter("tourPrice") == null || !req.getParameter("tourPrice").matches(regexTourPrice)) {
            System.out.println("Wrong tour price");
            return false;
        }
        if ( req.getParameter("maxDiscount") == null || !req.getParameter("maxDiscount").matches(regexTourDiscount)) {
            System.out.println("Wrong tour discount");
            return false;
        }
//        if ( !req.getParameter("tourHot").matches(regexTourPrice) ) {
//            System.out.println("Wrong tour Hot");
//            return false;
//        }
        if ( req.getParameter("tourType") == null
                || !Arrays.stream(tourTypes).anyMatch (req.getParameter("tourType")::equals)) {
            System.out.println("Wrong tour type");
            return false;
        }
        if ( req.getParameter("hotelType") == null || !req.getParameter("hotelType").matches(regexHotelType)) {
            System.out.println("Wrong hotel type");
            return false;
        }

        return true;

    }

    public static boolean registerFormCheck (HttpServletRequest req) {

        String regexName = "^[A-Za-z' А-Яа-яіІїЇ]{2,40}";
        String regexPassword = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$";
        String regexEmail = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$";
        String regexPhone = "^[+][0-9]{2}-[0-9]{3}-[0-9]{3}-[0-9]{4}";

        if ( !req.getParameter("firstName").matches(regexName) ) return false;
        if ( !req.getParameter("lastName").matches(regexName) ) return false;
        if ( !req.getParameter("password").matches(regexPassword) ) return false;
        if ( !req.getParameter("email").matches(regexEmail) ) return false;
        if ( !req.getParameter("phone").matches(regexPhone) ) return false;


        return true;
    }

}
