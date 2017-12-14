package org.framework.mvc.handler;

import org.framework.mvc.Handler;
import org.framework.mvc.ann.ServletUrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@ServletUrl("/handlerA")
public class HandlerA  implements Handler {
    @Override
    public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("handler A");
    }
}
