package com.artist.config;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter  {
	
	
	@Value("${jwt.secret}")
	  private String jwtSecret; 
	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	        
	        String token = request.getHeader("Authorization");
	        if (token != null && token.startsWith("Bearer ")) {
	            token = token.substring(7); // 移除 "Bearer " 前綴
	            try {
	                Claims claims = Jwts.parser()
	                        .setSigningKey(jwtSecret)
	                        .parseClaimsJws(token)
	                        .getBody();
	                
	                // 檢查過期時間
	                if (claims.getExpiration().before(new Date())) {
	                    // token已過期
	                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired");
	                    return;
	                }
	                // 在這裡，你可以根據聲明創建 Authentication 對象並設置到上下文中
	                // 例如，根據用戶名從數據庫獲取 UserDetails

	            } catch (Exception e) {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
	                return;
	            }
	        }
	        
	        chain.doFilter(request, response); // 繼續處理請求
	    }
}
