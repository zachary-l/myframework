package org.framework.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultHandler extends HttpServlet implements Handler{
    HttpServletRequest request = ActionContext.getContext().getRequest();
    HttpServletResponse response = ActionContext.getContext().getResponse();
    @Override
    public void handler() throws IOException{
        RequestDispatcher rd = request.getServletContext().getNamedDispatcher("default");
        System.out.println(rd);
        System.out.println(request);
        System.out.println(response);
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
