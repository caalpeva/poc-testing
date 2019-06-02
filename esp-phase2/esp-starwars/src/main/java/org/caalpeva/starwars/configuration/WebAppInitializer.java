package org.caalpeva.starwars.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Clase inicializadora del contexto de spring por anotaciones
 * @author Alberto
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer   {

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