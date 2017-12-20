package org.framework.mvc.filter;

import com.sun.glass.events.ViewEvent;
import org.framework.mvc.*;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HandlerFilterChain {
    public ActionMapper mapper;
    public Iterator<Interceptor> it;
    public HandlerFilterChain(ActionMapper mapper){
        this.mapper=mapper;
        if(mapper.getFilterChainList()!=null){
            this.it = mapper.getFilterChainList().iterator();
        }
    }
    public Object handle(){
        Object viewObject = null;
        if(it.hasNext()){
            it.next().execute(this);
        }else{
            System.out.println("过滤通过");
            viewObject = new HandlerInvoker().handlerInvoker(mapper);
        }
        return viewObject;
    }

}
