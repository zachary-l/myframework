package org.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultHandler implements Handler {
    @Override
    public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.getWriter().println("handler Default");
    }
}
