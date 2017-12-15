package org.framework.mvc.handler;

import org.framework.mvc.ann.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/handlers")
public class Handlers {
    @RequestMapping("/method1")
    public void handler(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().println("handlers method1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/method2")
    public void handler2(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().println("handlers method2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
