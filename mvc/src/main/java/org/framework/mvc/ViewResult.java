package org.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ViewResult {
    protected HttpServletRequest getRequest(){
        return  ActionContext.getContext().getRequest();
    }
    protected HttpServletResponse getResponse(){
       return ActionContext.getContext().getResponse();
    }
    public abstract void execute() throws IOException;
}
