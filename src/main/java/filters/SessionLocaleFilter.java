package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})

public class SessionLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

            throws IOException, ServletException {
        System.out.println("LocaleFilter");

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("sessionLocale") != null) {

            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));

        }

        chain.doFilter(request, response);

    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}