package com.desafio.filter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AbstractFilter {
	
	public AbstractFilter() {
	}
	
	protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("index");
		dispatcher.forward(request, response);
	}

}
