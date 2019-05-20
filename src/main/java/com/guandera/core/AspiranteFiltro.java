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

import com.guandera.talento.aspirante.client.view.LoginAspiranteManagedBean;

/**
 * 
 * @author Fredi Javier Velasco villarreal
 */

public class AspiranteFiltro implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();

		LoginAspiranteManagedBean loginAspiranteMB = (LoginAspiranteManagedBean) session
				.getAttribute("LoginAspiranteMB");


			if (loginAspiranteMB == null || !loginAspiranteMB.isAutenticado()) {
				String contextPath = ((HttpServletRequest) request).getContextPath();

				((HttpServletResponse) response).sendRedirect(contextPath + "/convocatoria/login.jsf");

		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) {

	}

}
