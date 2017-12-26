package org.framework.mvc;

import org.framework.mvc.paramsHandler.HandlerChain;
import org.framework.mvc.util.BasicTypeChangeUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class HandlerInvoker {
    public Object handlerInvoker(ActionMapper mapper) {
        //创建实例
        createInstance(mapper);
        //设置参数
        setParams(mapper);
        //校验参数
        dataVerification(mapper);
        //执行方法并返回
        return exectorMethod(mapper);
    }
    //执行方法
    private Object exectorMethod(ActionMapper mapper) {
        Object viewObject = null;
        //参数
        Object[] params = mapper.getParams();
        //实例
        Object instance = mapper.getTarget();
        try {
            viewObject = mapper.getDefinition().getMethod().invoke(instance, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return viewObject;
    }

    //参数赋值
    private void setParams(ActionMapper mapper) {
        Object[] params = new Object[mapper.getDefinition().getMethod().getParameterCount()];
        for (int i = 0; i < params.length; i++) {
            Parameter parameter = mapper.getDefinition().getMethod().getParameters()[i];
            params[i] = new HandlerChain().execute(parameter);
        }
        mapper.setParams(params);
    }
    //创建实例
    private void createInstance(ActionMapper mapper){
        HttpServletRequest request = ActionContext.getContext().getRequest();
        HandlerFactory handlerFactory = (HandlerFactory) request.getServletContext().getAttribute(DispatcherServlet.ACTION_FACTORY);
        mapper.setTarget(handlerFactory.createHandler(mapper.getDefinition()));
    }
/*    private void createInstance(ActionMapper mapper) {
        Class<?> c = mapper.getDefinition().getClazz();
        Object instance = null;
        if (c != null) {
            try {
               instance = c.newInstance();
               mapper.setTarget(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
    private void dataVerification(ActionMapper mapper){
        Object[] params = mapper.getParams();
        for(int i = 0; i < params.length; i++){
            Parameter parameter = mapper.getDefinition().getMethod().getParameters()[i];
            if (params[i] == null && parameter.getType().isPrimitive()) {
                params[i] = BasicTypeChangeUtil.primitiveDefaults.get(parameter.getType());
            }else if(params[i] == null){
                throw new RuntimeException("Error of parameters or Incomplete");
            }
        }
    }
}
