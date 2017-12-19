package org.framework.mvc.view;

import org.framework.mvc.ViewResult;

import java.io.IOException;

public class RedirectView  extends ViewResult{
    private String urlPathName;
    public RedirectView(String urlPathName){
        this.urlPathName=urlPathName;
    }
    @Override
    public void execute() throws IOException {
        if(urlPathName!=null) {
            getResponse().sendRedirect("/"+urlPathName);
        }
    }
}
