import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import util.FormCheckUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterFormCheckTests {


    @Test
    public void RegisterFormNullTest () {
        assertFalse(FormCheckUtils.registerFormCheck(null));
    }
    @Test
    public void RegisterFormAllParamNullTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        // mock the returned value of request.getParameterMap()
        when(request.getParameter("firstName")).thenReturn(null);
        when(request.getParameter("lastName")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("phone")).thenReturn(null);


        assertFalse(FormCheckUtils.registerFormCheck(request));
    }
    @Test
    public void RegisterFormOneParamNullTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        // mock the returned value of request.getParameterMap()
        when(request.getParameter("firstName")).thenReturn(null);
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getParameter("password")).thenReturn("passworD@");
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("phone")).thenReturn("+38-123-123-1234");

        assertFalse(FormCheckUtils.registerFormCheck(request));

        when(request.getParameter("firstName")).thenReturn("name");
        when(request.getParameter("lastName")).thenReturn(null);

        assertFalse(FormCheckUtils.registerFormCheck(request));

        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getParameter("password")).thenReturn(null);

        assertFalse(FormCheckUtils.registerFormCheck(request));

        when(request.getParameter("password")).thenReturn("passworD@");
        when(request.getParameter("email")).thenReturn(null);

        assertFalse(FormCheckUtils.registerFormCheck(request));

        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("phone")).thenReturn(null);

        assertFalse(FormCheckUtils.registerFormCheck(request));
    }

    @Test
    public void RegisterFormFirstNameTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("firstName")).thenReturn("firstName");
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getParameter("password")).thenReturn("User1@");
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("phone")).thenReturn("+38-123-123-1234");

        assertTrue(FormCheckUtils.registerFormCheck(request));

        String[] firstNameTrue = {"aa", "Aa", "AA", "Abcd", "Abcd abcd",
                Stream.generate(() -> "a").limit(40).collect(Collectors.joining())};

        for (String name: firstNameTrue) {
            when(request.getParameter("firstName")).thenReturn(name);
            assertTrue(FormCheckUtils.registerFormCheck(request));
        }

        String[] firstNameFalse = {"", "a", "A", "we1", "!we", "1asd",
                Stream.generate(() -> "a").limit(41).collect(Collectors.joining())};

        for (String name: firstNameFalse) {
            when(request.getParameter("firstName")).thenReturn(name);
            assertFalse(FormCheckUtils.registerFormCheck(request));
        }
    }

    @Test
    public void RegisterFormLastNameTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("firstName")).thenReturn("firstName");
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getParameter("password")).thenReturn("User1@");
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("phone")).thenReturn("+38-123-123-1234");

        assertTrue(FormCheckUtils.registerFormCheck(request));

        String[] lastNameTrue = {"aa", "Aa", "AA", "Abcd", "Abcd abcd",
                Stream.generate(() -> "a").limit(40).collect(Collectors.joining())};

        for (String name:lastNameTrue) {
            when(request.getParameter("lastName")).thenReturn(name);
            assertTrue(FormCheckUtils.registerFormCheck(request));
        }

        String[] lastNameFalse = {"", "a", "A", "we1", "!we", "1asd",
                Stream.generate(() -> "a").limit(41).collect(Collectors.joining())};

        for (String name: lastNameFalse) {
            when(request.getParameter("lastName")).thenReturn(name);
            assertFalse(FormCheckUtils.registerFormCheck(request));
        }
    }



    @Test
    public void RegisterFormPasswordNumberTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("firstName")).thenReturn("firstName");
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getParameter("password")).thenReturn("User1@");
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("phone")).thenReturn("+38-123-123-1234");

        assertTrue(FormCheckUtils.registerFormCheck(request));

        String[] passwordTrue = {"USEr12%", "#aaa123", "12wa$!@"};

        for (String name: passwordTrue) {
            when(request.getParameter("password")).thenReturn(name);
            assertTrue(FormCheckUtils.registerFormCheck(request));
        }

        String[] passwordFalse = {"", "a", "A", "!", ".", " 1", "!1", "0", "aA@1", "100aaa",
                                    "aaaAAA", "aaaAAA1"};

        for (String name: passwordFalse) {
            when(request.getParameter("password")).thenReturn(name);
            assertFalse(FormCheckUtils.registerFormCheck(request));
        }
    }

    @Test
    public void RegisterFormEmailNumberTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("firstName")).thenReturn("firstName");
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getParameter("password")).thenReturn("User1@");
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("phone")).thenReturn("+38-123-123-1234");

        assertTrue(FormCheckUtils.registerFormCheck(request));

        String[] emailTrue = {"mail12@mail.com", "mail_we@mail.com", "mail@sd.mail.com"};

        for (String name: emailTrue) {
            when(request.getParameter("email")).thenReturn(name);
            assertTrue(FormCheckUtils.registerFormCheck(request));
        }

        String[] emailFalse = {"mail12mail.com", "mail_we@mailcom", "mail@sd.mail."};

        for (String name: emailFalse) {
            when(request.getParameter("email")).thenReturn(name);
            assertFalse(FormCheckUtils.registerFormCheck(request));
        }
    }

    @Test
    public void RegisterFormPhoneNumberTest () {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("firstName")).thenReturn("firstName");
        when(request.getParameter("lastName")).thenReturn("lastName");
        when(request.getParameter("password")).thenReturn("User1@");
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("phone")).thenReturn("+38-123-123-1234");

        assertTrue(FormCheckUtils.registerFormCheck(request));

        String[] phoneTrue = {"+38-123-123-1234", "+00-000-000-0000"};

        for (String name: phoneTrue) {
            when(request.getParameter("phone")).thenReturn(name);
            assertTrue(FormCheckUtils.registerFormCheck(request));
        }

        String[] phoneFalse = {"38-123-123-1234", "+38123-123-1234", "+38-123123-1234", "+38-123-1231234",
            "+38-123-123-123a"};

        for (String name: phoneFalse) {
            when(request.getParameter("phone")).thenReturn(name);
            assertFalse(FormCheckUtils.registerFormCheck(request));
        }
    }



}
