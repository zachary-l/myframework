package org.framework.mvc.paramsHandler;

import org.apache.commons.beanutils.ConvertUtils;
import org.framework.mvc.SetParamsHandler;

import java.lang.reflect.Parameter;

/**
 * 初始化基本数据类型，
 */
public class BasicDataTypeHandler extends SetParamsHandler {

    @Override
    public Object handle(Parameter parameter,HandlerChain chain) {
        Object param = null;
        if(parameter.getType().isArray()){
            param = getRequest().getParameterValues(parameter.getName());
        }else{
            param = getRequest().getParameter(parameter.getName());
           }
        if (param==null){
            //递归
            return chain.execute(parameter);
        }
        Object value = ConvertUtils.convert(param,parameter.getType());
        if(value==null){
            throw new RuntimeException("value is null");
        }
        return value;
    }
}
