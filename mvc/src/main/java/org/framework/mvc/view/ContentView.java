package org.framework.mvc.view;

import org.framework.mvc.ViewResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理不实现viewresult接口的默认返回类型
 */
public class ContentView extends ViewResult{
    private Object content;
    public ContentView(Object content){
        this.content = content;
    }
    @Override
    public void execute() throws IOException {
        HttpServletResponse response= getResponse();
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().println(content.toString());
    }
}
