package com.userapp.springmvc.configuration;


import com.userapp.springmvc.configuration.ConfigurationSpring;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.Collections;

public class MainSpring implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext contexto = new AnnotationConfigWebApplicationContext();
        contexto.register(ConfigurationSpring.class);

        contexto.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(contexto));
        servlet.setLoadOnStartup(1);
        servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
        SessionCookieConfig sessionCookieConfig=servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);
        servlet.addMapping("/");
    }
}
