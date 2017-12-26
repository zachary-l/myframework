package org.framework.mvc;

import org.framework.mvc.scopeOfAction.SessionMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.Map;

public class ActionContext {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String,Object> requestMap;
    private Map<String, Object> sessionMap;
    private Map<String,Object> appliactionMap;
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

    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    protected void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Map<String, Object> getRequestMap() {
        return requestMap;
    }

    protected void setRequestMap(Map<String, Object> requestMap) {
        this.requestMap = requestMap;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    protected void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public Map<String, Object> getAppliactionMap() {
        return appliactionMap;
    }

    protected void setAppliactionMap(Map<String, Object> appliactionMap) {
        this.appliactionMap = appliactionMap;
    }


    // -------------Servlet API-------------
    /**
     * 获取资源绝对路径
     *
     * @param arg0
     * @return
     */
    public String getRealPath(String arg0) {
        HttpServletRequest request = getContext().getRequest();
        return request.getServletContext().getRealPath(arg0);
    }

    /**
     * 获取上下文路径
     * @return
     */
    public String getContextPath() {
        HttpServletRequest request = getContext().getRequest();
        return request.getServletContext().getContextPath();
    }

    /**
     * 获取请求参数
     * @return
     */
    public String getQueryString(){
        HttpServletRequest request =getContext().getRequest();
        return request.getQueryString();
    }

    /**
     * 获取Servlet路径
     * @return
     */
    public String getServletPath(){
        HttpServletRequest request = getContext().getRequest();
        return request.getServletPath();
    }

    /**
     * 获取请求路径信息
     * @return
     */
    public String getPathInfo(){
        HttpServletRequest request = getContext().getRequest();
        return request.getPathInfo();
    }

    /**
     * 获取请求头信息
     * @param arg0
     * @return
     */
    public String getHeader(String arg0){
        HttpServletRequest request =getContext().getRequest();
        return request.getHeader(arg0);
    }

    /**
     * 获取请求类型
     * @return
     */
    public String getContentType(){
        HttpServletRequest request = getContext().getRequest();
        return request.getContentType();
    }

    /**
     * 获取cookies
     * @return
     */
    public Cookie[] getCookies(){
        HttpServletRequest request =getContext().getRequest();
        return request.getCookies();
    }

    /**
     * 添加cookie
     */
    public void addCookie(Cookie cookie) {
        HttpServletResponse response = getContext().getResponse();
        response.addCookie(cookie);
    }

    /**
     * 获取Servlet的输入流
     * @return
     * @throws IOException
     */
    public ServletInputStream getInputStream() throws IOException {
        HttpServletRequest request = getContext().getRequest();
        return request.getInputStream();
    }

    /**
     * 获取所有请求参数
     */
    public Map<String, String[]> getParameters() {
        HttpServletRequest request = getContext().getRequest();
        return request.getParameterMap();
    }

    /**
     * 获取sessionID
     */
    public String getSessionId() {
        HttpServletRequest request = getContext().getRequest();
        return request.getSession().getId();
    }
}
