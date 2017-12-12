/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.spring.java.util.CORSFilter;

/**
 * @author User
 *
 */
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
/* @Override
      protected Filter[] getServletFilters() {
  	        Filter [] filters = {new CORSFilter()};
  	        return filters;
      }*/
}