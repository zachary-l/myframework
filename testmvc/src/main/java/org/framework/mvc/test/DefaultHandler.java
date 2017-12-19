package org.framework.mvc.test;


import org.framework.mvc.ActionContext;
import org.framework.mvc.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/14.
 */
public class DefaultHandler implements Handler {
    HttpServletResponse response = ActionContext.getContext().getResponse();

    @Override
    public void handler() throws IOException {
        try {
            response.getWriter().println("default....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
