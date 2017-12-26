package org.framework.plugin;

import org.framework.beans.factory.BeanFactory;
import org.framework.mvc.DispatcherServlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String path = servletContextEvent.getServletContext().getInitParameter("scanPackage");
        BeanFactory beanFactory = new BeanFactory(path);
        ContextFactory contextFactory = new ContextFactory(beanFactory);
        servletContextEvent.getServletContext().setAttribute(DispatcherServlet.ACTION_FACTORY,contextFactory);
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ContextFactory contextFactory = (ContextFactory)servletContextEvent.getServletContext().getAttribute(DispatcherServlet.ACTION_FACTORY);
        contextFactory.getBeanFactory().close();
        servletContextEvent.getServletContext().removeAttribute(DispatcherServlet.ACTION_FACTORY);
    }
}
