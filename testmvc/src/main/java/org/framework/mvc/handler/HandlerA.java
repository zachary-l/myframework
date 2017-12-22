package org.framework.mvc.handler;

import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.ContentView;
import org.framework.mvc.view.ForwardView;
import org.framework.mvc.view.JsonView;
import org.framework.mvc.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class HandlerA{
    //重定向
    @RequestMapping("/handleA")
    public ViewResult handleA() throws IOException {
        return new RedirectView("index.html");
    }
    //转发
    @RequestMapping("/handleB")
    public ViewResult handleB(){
        return new ForwardView("handler1");
    }
    //返回json对象.session作用域
    @RequestMapping("/handleC")
    public ViewResult handleC(){
        Map<String,Object> map = ActionContext.getContext().getSessionMap();
        map.put("a",1);
        int a =(Integer) map.get("a");
        return new JsonView(a);
    }
    @RequestMapping("/handleD")
    public ViewResult handleD(){
        Map<String,Object> map = ActionContext.getContext().getSessionMap();
        int a =(Integer) map.get("a");
        return new ContentView(a);
    }
    @RequestMapping("/handleF")
    public String handleF(){
        return "zacahry";
    }
    @RequestMapping("/handleI")
    public void handleI(){

    }
}
