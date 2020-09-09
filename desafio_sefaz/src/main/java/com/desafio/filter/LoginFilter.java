package com.desafio.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.desafio.user.model.User;

//@WebFilter(urlPatterns = "asdasdasd/sdfsdf/sdfsdf", servletNames = "{Faces Servlet}")
public class LoginFilter extends AbstractFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		if(session.isNew() || user == null) {
			doLogin(request, response, req);
		}else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		
	}

}
