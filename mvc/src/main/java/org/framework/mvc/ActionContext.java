package org.framework.mvc;

import org.framework.mvc.scopeOfAction.SessionMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.Map;

public class ActionContext {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> sessionMap;
    final static ThreadLocal<ActionContext> localContext = new ThreadLocal<ActionContext>();

    private ActionContext() {}
    public static ActionContext getContext() {
        if (localContext.get() == null) {
            // 如果当前线程上没有绑定ActionContext,则创建一个并绑定当前线程
            localContext.set(new ActionContext());
        }

        // 返回当前线程的ActionContext对象
        return localContext.get();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Map<String, Object> getSession() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap sessionMap) {
        this.sessionMap = sessionMap;
    }
}
