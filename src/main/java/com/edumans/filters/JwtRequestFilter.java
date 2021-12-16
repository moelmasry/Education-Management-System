package com.edumans.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edumans.service.impl.UserDetailsServiceImpl;
import com.edumans.utils.AuthExceptionHandler;
import com.edumans.utils.JwtUtil;
/**
 * custom Filter extends {@link OncePerRequestFilter} for handle all the requests come to the API and authenticate the JWT token
 * 
 * @author mohamed.elmasry
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	AuthExceptionHandler authExceptionHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		try {
			// get auth header(JWT)
		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;
		// as we depend on (Bearer) we extract the JWT from the Auth header and then extract the username from it.
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}
		
		// here i validate JWT--> user data and expiration time
		// also, handle validation exception using a custom handler AuthExceptionHandler and advice controller not help here 
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (jwtUtil.validateToken(jwt, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
		} catch (Exception exception) {
			authExceptionHandler.onAuthenticationFailure(request, response, exception);
		}
	}

}
