package util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class FormCheckUtils {
    public static boolean addTourFormCheck(HttpServletRequest req) {

        String regexName = ".{2,64}";
        String regexDesc = ".{2,65500}";
        String regexPersonNumber = "^[0-9]{1,3}";
        String regexTourPrice = "^[1-9][0-9]{1,8}";
        String regexHotelType = "[1-5]{1}";
        String regexTourHot = "[0-1]{1}";
        String[] tourTypes = {"rest", "excursion", "shopping"};
        System.out.println(req.getParameter("tourType"));

        if ( !req.getParameter("tourName").matches(regexName) ) {
            System.out.println("Wrong tour name");
            return false;
        }
        if ( !req.getParameter("tourDescription").trim().matches(regexDesc) ) {
            System.out.println(req.getParameter("tourDescription"));
            System.out.println("Wrong tour Desc");
            return false;
        }
        if ( !req.getParameter("PersonNumber").matches(regexPersonNumber) ) {
            System.out.println("Wrong person number");
            return false;
        }
        if ( !req.getParameter("tourPrice").matches(regexTourPrice) ) {
            System.out.println("Wrong tour price");
            return false;
        }
//        if ( !req.getParameter("tourHot").matches(regexTourPrice) ) {
//            System.out.println("Wrong tour Hot");
//            return false;
//        }
        if ( !Arrays.stream(tourTypes).anyMatch (req.getParameter("tourType")::equals)) {
            System.out.println("Wrong tour type");
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
