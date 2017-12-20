package org.framework.mvc;

import org.framework.mvc.filter.HandlerFilterChain;
import org.framework.mvc.util.FilterInterceptor;
import org.framework.mvc.filter.Interceptor;
import org.framework.mvc.scopeOfAction.SessionMap;
import org.framework.mvc.util.HandlerDefinitionParser;
import org.framework.mvc.util.ScanUtil;
import org.framework.mvc.view.DefaultView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DispatcherServlet  extends HttpServlet{
    public final static String DETINITIONS = "definition";
    public final static String FILTERDETINITIONS = "filterDefinition";
    private Map<String,HandlerDefinition> mappings = null;
    private Map<String,List<Interceptor>> interceptor = null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List<String> list = ScanUtil.scanPackage();
        //所有servlet信息
        mappings = initMapping(config,list);
        //拦截器所拦截的servlet信息
        initInterceptor(config,mappings,list);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //把作用域绑定到当前线程
        setActionContext(req,resp);

//        ActionMapper mapper = new HandlerMapping().findHandler();
        HandlerFilterChain filterChain = new HandlerMapping().findHandler();
        if(filterChain.mapper.getDefinition()==null){
            try {
                new DefaultHandler().handler();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //方法的返回值类型
//            Object viewObject = new HandlerInvoker().handlerInvoker(filterChain.mapper);
            Object viewObject = filterChain.handle();
            //相应视图结果集
            response(viewObject);
            //移除当前线程
            removeActionContext();
        }
    }

    private Map<String,HandlerDefinition> initMapping(ServletConfig config,List<String> list){
        mappings = HandlerDefinitionParser.parser(list);
        config.getServletContext().setAttribute(DETINITIONS,mappings);
        return mappings;
    }
    private void initInterceptor(ServletConfig config,Map<String,HandlerDefinition> mappings,List<String> list){
        interceptor = new FilterInterceptor().interceptor(mappings,list);
        config.getServletContext().setAttribute(FILTERDETINITIONS,interceptor);
    }
    private void setActionContext(HttpServletRequest request,HttpServletResponse response){
        ActionContext actionContext = ActionContext.getContext();
        actionContext.setRequest(request);
        actionContext.setResponse(response);
        actionContext.setSessionMap(new SessionMap(request));
    }

    private void response(Object viewObject){
        if(viewObject!=null){
            ViewResult viewResult = null;
            if(viewObject instanceof ViewResult){
               viewResult = (ViewResult)viewObject;
            }else{
                viewResult = new DefaultView();
            }
            try {
                viewResult.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void removeActionContext(){
        ActionContext.localContext.remove();
    }
}
