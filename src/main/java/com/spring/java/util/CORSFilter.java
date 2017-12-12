package com.spring.java.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author By Dinesh Bade 11:41:46 AM
 *
 */
@Component
public class CORSFilter extends OncePerRequestFilter {
	/*
	 * @Override* public void doFilter(ServletRequest req, ServletResponse res,
	 * FilterChain chain) throws IOException, ServletException {
	 * 
	 * }
	 */
	/*
	 * @Override public void init(FilterConfig filterConfig) throws
	 * ServletException { }
	 */
	@Override
	public void destroy() {
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println("---CORS check start---");
		response.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT,GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"Authorization,Content-Type, Accept, X-Requested-With, remember-me");
		System.out.println("---CORS Configuration Completed---");

		final HttpServletRequest request1 = (HttpServletRequest) req;
		if (!request1.getMethod().equals("OPTIONS")) {
			chain.doFilter(req, res);
		} else {
			// do not continue with filter chain for options requests
		}
	}
}
