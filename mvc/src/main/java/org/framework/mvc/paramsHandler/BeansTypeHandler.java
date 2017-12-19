package org.framework.mvc.paramsHandler;

import org.apache.commons.beanutils.BeanUtils;
import org.framework.mvc.SetParamsHandler;

import java.lang.reflect.Parameter;

public class BeansTypeHandler extends SetParamsHandler {
    @Override
    public Object handle(Parameter parameter,HandlerChain chain) {
        Object param = null;
        try {
            param = parameter.getType().newInstance();
            BeanUtils.populate(param, getRequest().getParameterMap());
        } catch (Throwable e) {
            return chain.execute(parameter);
        }
        return param;
    }
}
