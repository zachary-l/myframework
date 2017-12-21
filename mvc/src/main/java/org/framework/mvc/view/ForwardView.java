package org.framework.mvc.view;

import org.framework.mvc.ViewResult;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 转发
 */
public class ForwardView extends ViewResult{
    private String urlPathName;
    public ForwardView(String urlPathName){
        this.urlPathName = urlPathName;
    }
    @Override
    public void execute() throws IOException {
        if(urlPathName!=null){
            try {
                getRequest().getRequestDispatcher("/"+urlPathName).forward(getRequest(),getResponse());
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    }
}
