package org.caalpeva.starwars.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("classpath:spring-servlet.xml");
		
		ServletRegistration.Dynamic dispatcher = container
				.addServlet("distpatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		XmlWebApplicationContext alternativeContext = new XmlWebApplicationContext();
		alternativeContext.setConfigLocation("classpath:spring-service.xml");
		container.addListener(new ContextLoaderListener(alternativeContext));
	}
}