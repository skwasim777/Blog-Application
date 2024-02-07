package com.bolgapplication.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private JwtTokenHelper helper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requstToken = request.getHeader("Authorization");
		System.out.println(requstToken);
		String username = null;
		String token = null;
		if (requstToken != null && requstToken.startsWith("Bearer")) {
			token = requstToken.substring(7);
			try {
				username = this.helper.getUserNameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get Jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("Jwr token has expired");
			} catch (MalformedJwtException e) {
				System.out.println("invalid jwt token");
			}
		} else {
			System.out.println("Jwt Token does not begin with bearer");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails details = this.detailsService.loadUserByUsername(username);
			if (this.helper.validateToken(token, details)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						details, null, details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			} else {
				System.out.println("invalid Jwt token");
			}
		} else {
			System.out.println("user name is null or context is not null ");
		}
		filterChain.doFilter(request, response);
	}

}
