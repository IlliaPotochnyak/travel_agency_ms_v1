
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Test;
import util.FormCheckUtils;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddTourFormCheckTests {


    @Test
    public void AddTourFormNullTest () {
        assertFalse(FormCheckUtils.addTourFormCheck(null));
    }
    @Test
    public void AddTourFormAllParamNullTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        // mock the returned value of request.getParameterMap()
        when(request.getParameter("tourName")).thenReturn(null);
        when(request.getParameter("tourDescription")).thenReturn(null);
        when(request.getParameter("PersonNumber")).thenReturn(null);
        when(request.getParameter("tourPrice")).thenReturn(null);
        when(request.getParameter("maxDiscount")).thenReturn(null);
        when(request.getParameter("tourType")).thenReturn(null);
        when(request.getParameter("hotelType")).thenReturn(null);

        assertFalse(FormCheckUtils.addTourFormCheck(request));
    }
    @Test
    public void AddTourFormOneParamNullTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        // mock the returned value of request.getParameterMap()
        when(request.getParameter("tourName")).thenReturn(null);
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertFalse(FormCheckUtils.addTourFormCheck(request));

        when(request.getParameter("tourName")).thenReturn("Tour name");
        when(request.getParameter("tourDescription")).thenReturn(null);

        assertFalse(FormCheckUtils.addTourFormCheck(request));

        when(request.getParameter("tourDescription")).thenReturn("tourDescription");
        when(request.getParameter("PersonNumber")).thenReturn(null);

        assertFalse(FormCheckUtils.addTourFormCheck(request));

        when(request.getParameter("PersonNumber")).thenReturn("2");
        when(request.getParameter("tourPrice")).thenReturn(null);

        assertFalse(FormCheckUtils.addTourFormCheck(request));

        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn(null);

        assertFalse(FormCheckUtils.addTourFormCheck(request));

        when(request.getParameter("maxDiscount")).thenReturn("0");
        when(request.getParameter("tourType")).thenReturn(null);

        assertFalse(FormCheckUtils.addTourFormCheck(request));

        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn(null);

        assertFalse(FormCheckUtils.addTourFormCheck(request));
    }

    @Test
    public void AddTourFormTourNameTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("tourName")).thenReturn("Name");
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertTrue(FormCheckUtils.addTourFormCheck(request));

        String[] namesTrue = {"aa", "Aa", "AA", "12", "Abcd", "Abcd abcd.", "!aBcd %_/.12345 ",
                Stream.generate(() -> "a").limit(64).collect(Collectors.joining())};

        for (String name: namesTrue) {
            when(request.getParameter("tourName")).thenReturn(name);
            assertTrue(FormCheckUtils.addTourFormCheck(request));
        }

        String[] namesFalse = {"", "a", "A", "1", "!", ".",
                Stream.generate(() -> "a").limit(65).collect(Collectors.joining())};

        for (String name: namesFalse) {
            when(request.getParameter("tourName")).thenReturn(name);
            assertFalse(FormCheckUtils.addTourFormCheck(request));
        }
    }

    @Test
    public void AddTourFormTourDescriptionTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("tourName")).thenReturn("Name");
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertTrue(FormCheckUtils.addTourFormCheck(request));

        String[] descTrue = {"aa", "Aa", "AA", "12", "Abcd", "Abcd abcd.", "!aBcd %_/.12345 ",
                Stream.generate(() -> "a").limit(65500).collect(Collectors.joining())};

        for (String name: descTrue) {
            when(request.getParameter("tourDescription")).thenReturn(name);
            assertTrue(FormCheckUtils.addTourFormCheck(request));
        }

        String[] descFalse = {"", "a", "A", "1", "!", ".",
                Stream.generate(() -> "a").limit(65501).collect(Collectors.joining())};

        for (String name: descFalse) {
            when(request.getParameter("tourDescription")).thenReturn(name);
            assertFalse(FormCheckUtils.addTourFormCheck(request));
        }
    }

    @Test
    public void AddTourFormPersonNumberTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("tourName")).thenReturn("Name");
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertTrue(FormCheckUtils.addTourFormCheck(request));

        String[] personsTrue = {"2", "10", "99"};

        for (String name: personsTrue) {
            when(request.getParameter("PersonNumber")).thenReturn(name);
            assertTrue(FormCheckUtils.addTourFormCheck(request));
        }

        String[] personsFalse = {"", "a", "A", "!", ".", " 1", "!1", "0", "01", "100"};

        for (String name: personsFalse) {
            when(request.getParameter("PersonNumber")).thenReturn(name);
            assertFalse(FormCheckUtils.addTourFormCheck(request));
        }
    }

    @Test
    public void AddTourFormMaxDiscountTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("tourName")).thenReturn("Name");
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertTrue(FormCheckUtils.addTourFormCheck(request));

        String[] discountTrue = {"2", "10", "99"};

        for (String name: discountTrue) {
            when(request.getParameter("maxDiscount")).thenReturn(name);
            assertTrue(FormCheckUtils.addTourFormCheck(request));
        }

        String[] discountFalse = {"", "a", "A", "!", ".", " 1", "!1", "100"};

        for (String name: discountFalse) {
            when(request.getParameter("maxDiscount")).thenReturn(name);
            assertFalse(FormCheckUtils.addTourFormCheck(request));
        }
    }

    @Test
    public void AddTourFormPriceTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("tourName")).thenReturn("Name");
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertTrue(FormCheckUtils.addTourFormCheck(request));

        String[] priceTrue = {"10", "11234", "999999999"};

        for (String name: priceTrue) {
            when(request.getParameter("tourPrice")).thenReturn(name);
            assertTrue(FormCheckUtils.addTourFormCheck(request));
        }

        String[] priceFalse = {"", "a", "A", "!", ".", " 1", "!1", "0", "01", "00", "1000000000"};

        for (String name: priceFalse) {
            when(request.getParameter("tourPrice")).thenReturn(name);
            assertFalse(FormCheckUtils.addTourFormCheck(request));
        }
    }
    @Test
    public void AddTourFormTourTypeTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("tourName")).thenReturn("Name");
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertTrue(FormCheckUtils.addTourFormCheck(request));

        String[] typeTrue = {"rest", "excursion", "shopping"};

        for (String name: typeTrue) {
            when(request.getParameter("tourType")).thenReturn(name);
            assertTrue(FormCheckUtils.addTourFormCheck(request));
        }

        String[] typeFalse = {"", "a", "A", "!", ".", " 1", "!1", "0", "01", "00", "1000000000",
                                " rest", "shopping!"};

        for (String name: typeFalse) {
            when(request.getParameter("tourType")).thenReturn(name);
            assertFalse(FormCheckUtils.addTourFormCheck(request));
        }
    }
    @Test
    public void AddTourFormHotelTypeTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("tourName")).thenReturn("Name");
        when(request.getParameter("tourDescription")).thenReturn("Desc");
        when(request.getParameter("PersonNumber")).thenReturn("1");
        when(request.getParameter("tourPrice")).thenReturn("1000");
        when(request.getParameter("maxDiscount")).thenReturn("10");
        when(request.getParameter("tourType")).thenReturn("rest");
        when(request.getParameter("hotelType")).thenReturn("3");

        assertTrue(FormCheckUtils.addTourFormCheck(request));

        String[] hotelTrue = {"1", "3", "5"};

        for (String name: hotelTrue) {
            when(request.getParameter("hotelType")).thenReturn(name);
            assertTrue(FormCheckUtils.addTourFormCheck(request));
        }

        String[] hotelFalse = {"", "a", "A", "!", ".", " 1", "!1", "0", "01", "00", "6", "9"};

        for (String name: hotelFalse) {
            when(request.getParameter("hotelType")).thenReturn(name);
            assertFalse(FormCheckUtils.addTourFormCheck(request));
        }
    }



}
