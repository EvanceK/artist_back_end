package com.artist.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.artist.entity.Customers;
import com.artist.service.impl.CustomersServiceImpl;
import com.artist.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomersServiceImpl csi;

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String email = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			email = jwtUtil.extractEmail(jwt);
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			Customers customer = csi.getCustomer(email);

			// 驗證 token
			if (jwtUtil.validateToken(jwt, customer)) {
				// 建 Authentication
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customer, null,
						new ArrayList<>());

				// 將 Authentication 設置到 SecurityContext
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		// 執行下一個過濾器
		filterChain.doFilter(request, response);
	}
}
