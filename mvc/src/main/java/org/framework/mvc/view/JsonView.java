package org.framework.mvc.view;

import org.framework.mvc.ViewResult;
import org.framework.mvc.util.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回json对象
 */
public class JsonView extends ViewResult {
    private String json;
    public JsonView(Object bean){
        json = JsonUtil.toJson(bean);
    }
    public JsonView(Object bean, String format){
        json  =JsonUtil.toJson(bean,format);
    }
    @Override
    public void execute() throws IOException {
        HttpServletResponse  response= getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }
}
