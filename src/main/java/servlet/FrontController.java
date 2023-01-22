package servlet;

import action.ActionFactory;
import command.ActionCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "get");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "post");
    }
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response, String method)
            throws ServletException, IOException {
        String page = null;
        request.getSession().removeAttribute("errorMessage");
// определение команды, пришедшей из JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        /*
         * вызов реализованного метода execute() и передача параметров
         * классу-обработчику конкретной команды
         */
        page = command.execute(request);

// метод возвращает страницу ответа
// page = null; // поэксперементировать!
        if (page != null) {

            if (method.equals("get")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//            System.out.println("page forward - " + request.getContextPath() + page);
//            System.out.println(request);
//
//            Enumeration<String> parameterNames = request.getParameterNames();
//
//            while (parameterNames.hasMoreElements()) {
//
//                String paramName = parameterNames.nextElement();
//                System.out.println("param name - " + paramName);
//                System.out.println("n");
//
//                String[] paramValues = request.getParameterValues(paramName);
//                for (int i = 0; i < paramValues.length; i++) {
//                    String paramValue = paramValues[i];
//                    System.out.println("t" + paramValue);
//                    System.out.println("n");
//                }
//            }

//            System.out.println(request.getParameter("list"));
// вызов страницы ответа на запрос
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + page);
            }
        } else {
// установка страницы c cообщением об ошибке
//            page = ConfigurationManager.getProperty("path.page.index");
//            request.getSession().setAttribute("nullPage",
//                    MessageManager.getProperty("message.nullpage"));
//            System.out.println("page forward - " + request.getContextPath() + page);
            System.out.println(request.getParameter("errorMessage"));

            response.sendRedirect(request.getContextPath() + page);
        }
    }
}