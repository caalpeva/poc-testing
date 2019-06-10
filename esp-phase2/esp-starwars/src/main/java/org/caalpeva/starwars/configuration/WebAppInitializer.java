package org.caalpeva.starwars.configuration;

import java.util.EnumSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Clase inicializadora del contexto de spring por anotaciones
 * @author Alberto
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer   {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
        servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	
}