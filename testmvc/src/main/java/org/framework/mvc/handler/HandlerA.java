package org.framework.mvc.handler;

import org.framework.mvc.ann.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequestMapping("/handlerA")
public class HandlerA{
    @RequestMapping("/handleA")
    public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("handler A");
    }
}
