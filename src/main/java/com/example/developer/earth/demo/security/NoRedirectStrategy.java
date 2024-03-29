package com.example.developer.earth.demo.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.RedirectStrategy;

class NoRedirectStrategy implements RedirectStrategy {

	@Override
	public void sendRedirect(final HttpServletRequest request, final HttpServletResponse response, final String url)
			throws IOException {
		// No redirect is required with pure REST
	}
}
