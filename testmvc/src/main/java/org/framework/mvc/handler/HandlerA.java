package org.framework.mvc.handler;

import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.ForwardView;
import org.framework.mvc.view.JsonView;
import org.framework.mvc.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class HandlerA{
    @RequestMapping("/handleA")
    public ViewResult handleA() throws IOException {
        return new RedirectView("index.html");
    }
    @RequestMapping("/handleB")
    public ViewResult handleB(){
        return new ForwardView("handler1");
    }
    @RequestMapping("/handleC")
    public ViewResult handleC(){
        Map<String,Object> map = ActionContext.getContext().getSession();
        map.put("a",1);
        int a =(Integer) map.get("a");
        return new JsonView(a);
    }
}
