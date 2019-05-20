/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guandera.core.client.view.acceso.LoginManagedBean;

/**
 * 
 * @author Fredi Javier Velasco villarreal
 */

public class LoginFiltro implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();

		LoginManagedBean loginMB = (LoginManagedBean) session.getAttribute("LoginMB");

		if (loginMB == null || !loginMB.isAutenticado()) {

			String contextPath = ((HttpServletRequest) request).getContextPath();

			((HttpServletResponse) response).sendRedirect(contextPath + "/login.jsf");

		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) {

	}

}
