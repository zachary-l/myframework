package org.framework.mvc;

import org.framework.mvc.util.HandlerDefinitionParser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DispatcherServlet  extends HttpServlet{
    public final static String DETINITIONS = "definition";
    private Map<String,HandlerDefinition> mappings = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initMapping(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HandlerDefinition definition = new HandlerMapping().findHandler(req,resp);
        if(definition==null){
            try {
                new DefaultHandler().handler(req,resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            new HandlerInvoker().handlerInvoker(definition,req,resp);
        }
    }

    public void initMapping(ServletConfig config){
        mappings = HandlerDefinitionParser.parser();
        config.getServletContext().setAttribute(DETINITIONS,mappings);
    }
}
