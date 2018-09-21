package com.nuhs.gcto;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;  

@Order(1) // Filters declared at the Dispatcher initializer should be registered first
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {    
	public static final String CHARACTER_ENCODING = "UTF-8";
	
	
	public MvcWebApplicationInitializer() {
	    super();
	}
	
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
	    return new Class<?>[] { SpringWebConfig.class };
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
	    return new Class<?>[] { SpringSecurityConfig.class };
	}
	
	@Override
	protected String[] getServletMappings() {
	    return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
	    final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
	    encodingFilter.setEncoding(CHARACTER_ENCODING);
	    encodingFilter.setForceEncoding(true);
	    return new Filter[] { encodingFilter };
	}
	    
}  