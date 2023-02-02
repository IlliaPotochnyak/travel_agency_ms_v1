package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;

//@WebFilter( urlPatterns = { "/Cabinet.jsp" })
@WebFilter( urlPatterns = { "/controller" },
        initParams = {
        @WebInitParam(name = "admin", value = ""),
        @WebInitParam(name = "manager", value = "add_tour delete_tour user_list user_active"),
        @WebInitParam(name = "client", value = "receipt_status_change set_discount edit_tour"),
        @WebInitParam(name = "out-of-control", value = "login logout list_tour tour_page register lang_change")
}
)

public class PageRedirectSecurityFilter implements Filter {

    private String[] outOfControl;
    private String[] client;
    private String[] manager;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("PageRedirectSecurityFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userRole = (String) session.getAttribute("UserRole");
        String command = req.getParameter("command");

        if (!accessAllowed(command, userRole)) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }
// pass the request along the filter chain
        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
        outOfControl = fConfig.getInitParameter("out-of-control").split(" ");
        client = fConfig.getInitParameter("client").split(" ");
        manager = fConfig.getInitParameter("manager").split(" ");
    }

    public void destroy() {
    }
    private boolean accessAllowed(String command, String userRole) {

        if (command == null || command.isEmpty()) return false;
        if (Arrays.stream(outOfControl).anyMatch(x -> x.equals(command))) return true;
        if (userRole != null) {
            if (userRole.equals("admin")) return true;

            if (userRole.equals("client")) {
                //check in list of forbidden commands for client and manager
                return Arrays.stream(client).noneMatch(x -> x.equals(command)) &&
                        Arrays.stream(manager).noneMatch(x -> x.equals(command));
            }
            if (userRole.equals("manager")) {
                //check in list of forbidden commands for client
                return Arrays.stream(manager).noneMatch(x -> x.equals(command));
            }
        }
        return false;

    }
}
