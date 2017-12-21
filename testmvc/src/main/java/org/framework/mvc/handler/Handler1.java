package org.framework.mvc.handler;

import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.test.Entity;
import org.framework.mvc.view.JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class Handler1 {
    //测试参数
    @RequestMapping("/handler1")
    public ViewResult handler(HttpServletResponse response, int a, Entity entity, String b, int c, Date d){
        try {
            response.getWriter().println("---a:"+a+"---bean:"+entity.getA()+"---b:"+b+"---c:"+c+"---d:"+d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonView(entity);
    }
}
