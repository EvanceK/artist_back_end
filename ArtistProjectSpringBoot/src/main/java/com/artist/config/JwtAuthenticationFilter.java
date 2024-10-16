package com.artist.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.artist.entity.Customers;
import com.artist.service.impl.CustomersServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
    @Autowired
    @Lazy
    private CustomersServiceImpl csi; // 使用自動注入

    @Value("${jwt.secret}")
    private String jwtSecret;


//	private static final String UNIVERSAL_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZXIxQGVtYWlsLmNvbSIsIm5pY2tuYW1lIjoidGVzdGVyMSIsImN1c3RvbWVySWQiOiJDVTAwMDQiLCJleHAiOjE3MjkzMTgzNjd9.xOz-AEu_yJpTOGN6q8Jc-wvbgRPWm3sW5m7aZfqg7ZfCBIpHSNsIz5O2KO1R4_rqFpVG48pyYz5oaIbve3qxQQ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// 正常的 JWT 驗證邏輯
		Optional<String> jwt = extractJwtFromHeader(request);
		if (jwt.isPresent()) {
			String email = csi.getEmailFromToken(jwt.get());
			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				Customers customer = csi.getCustomer(email);
				if (customer != null && csi.validateToken(jwt.get(), customer.getEmail())) {
					// 提取角色信息
//                    List<String> roles = csi.getRolesForCustomer(email); // 确保这里是正确的方法调用
//                    List<GrantedAuthority> authorities = roles.stream()
//                            .map(role -> new SimpleGrantedAuthority(role))
//                            .toList();
//					
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							customer, null, Collections.emptyList());
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}

		chain.doFilter(request, response);
	}

	private Optional<String> extractJwtFromHeader(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return Optional.of(authorizationHeader.substring(7));
		}
		return Optional.empty();
	}
}

//	logger.info("JwtAuthenticationFilter triggered");
//
//	String requestToken = request.getHeader("Authorization");
//	
//	//=================================================暫時先這樣處理
//    // 不論前端發什麼 token，後端都會將其替換為萬用 token
//    if (requestToken == null || !requestToken.startsWith("Bearer ")) {
//        // 如果沒有 token 或者格式不對，設置為萬用 token
//        requestToken = UNIVERSAL_TOKEN;
//    } else {
//        // 如果有 token，無論是什麼 token，一律替換成萬用 token
//        requestToken = UNIVERSAL_TOKEN;
//    }
//	//=================================================暫時先這樣處理
//
//	// 檢查是否為萬用的 token
//	if (requestToken != null && requestToken.equals(UNIVERSAL_TOKEN)) {
//		Customers universalCustomer = new Customers();
//		universalCustomer.setEmail("tester1@email.com"); // 設置萬用用戶的電子郵件
//		universalCustomer.setPassword("123"); // 設置萬用用戶的密碼
//
//		// 創建身份驗證對象
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//				universalCustomer, null, Collections.emptyList());
//		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//		// 通過萬用 token，直接跳過其他驗證邏輯
//		chain.doFilter(request, response);
//		return;
//	}
	
	
