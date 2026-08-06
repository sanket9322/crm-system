package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException { 
		final String authHeader = request.getHeader("Authorization");

	        String token = null;
	        String username = null;
	        String role = null;

	        // ✅ Extract token
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);

	            username = jwtUtil.extractUsername(token);
	            role = jwtUtil.extractAllClaims(token).get("role", String.class);
	        }

	        // ✅ Set authentication with role
	        if (username != null &&
	                SecurityContextHolder.getContext().getAuthentication() == null) {

	            var userDetails = userDetailsService.loadUserByUsername(username);

	            var authorities =
	                    List.of(new SimpleGrantedAuthority(role));

	            UsernamePasswordAuthenticationToken authToken =
	                    new UsernamePasswordAuthenticationToken(
	                            userDetails, null, authorities);

	            authToken.setDetails(
	                    new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }

	        filterChain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}
}
