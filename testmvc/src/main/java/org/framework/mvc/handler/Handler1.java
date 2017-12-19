package org.framework.mvc.handler;

import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.test.Entity;
import org.framework.mvc.view.JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Handler1 {
    @RequestMapping("/handler1")
    public ViewResult handler(HttpServletResponse response, int a, Entity entity, String b, int c){
        try {
            response.getWriter().println(a+"---"+entity.getA()+"--"+b+"--"+c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonView(entity);
    }
}
