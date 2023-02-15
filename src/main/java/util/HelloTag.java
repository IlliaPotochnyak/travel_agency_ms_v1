package util;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class HelloTag extends TagSupport {
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String lang;
        if (session.getAttribute("lang") != null) {
            lang = String.valueOf(session.getAttribute("lang")).toUpperCase();
        } else {
            lang = "EN";
        }
        Locale locale = new Locale(lang);
        ResourceBundle rb = ResourceBundle.getBundle("languages/messages", locale);
        String guest = rb.getString("header.guest");
        try {
            String to = null;
            if (name.length() > 2) {
                to = name;
            } else {
                to = guest;
//                to = "message";
            }
            pageContext.getOut().write(to);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
