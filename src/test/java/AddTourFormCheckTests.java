
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

}
