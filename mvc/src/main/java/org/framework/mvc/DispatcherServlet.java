package org.framework.mvc;

import org.framework.mvc.ann.ServletUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.framework.mvc.util.ScanUtil.scanPackage;

public class DispatcherServlet  extends HttpServlet{
    private Map<String,Class<?>> mappings = null;
    @Override
    public void init() throws ServletException {
        super.init();
        initMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String url = req.getServletPath();
        Class<?> handler = mappings.get(url);
        if (handler ==null){
            new DefaultHandler().handler(req,resp);
        }else{
            try {
                ((Handler)handler.newInstance()).handler(req,resp);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void initMapping(){
        List<String> classNames = scanPackage();
        mappings = new HashMap<>();
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Object obj = clazz.newInstance();
                if(obj instanceof Handler){
                    if(clazz.isAnnotationPresent(ServletUrl.class)){
                        ServletUrl servletUrl = clazz.getAnnotation(ServletUrl.class);
//                        System.out.println(servletUrl.value()+"-->"+clazz);
                        mappings.put(servletUrl.value(),clazz);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
