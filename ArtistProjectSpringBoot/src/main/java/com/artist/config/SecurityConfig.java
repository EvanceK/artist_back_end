package com.artist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 使用 BCrypt 加密算法
	}

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter; // 自動注入過濾器

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // 禁用 CSRF
				.cors(Customizer.withDefaults()) // 使用默認的 CORS 設定
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // 添加自定義過濾器
				// 配置路徑的授權規則
				.authorizeHttpRequests(auth -> auth
						// 允許未認證用戶訪問 "/customers/login", "/customers/register" 路徑
						.requestMatchers("/customers/login", "/customers/register").permitAll()
		                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
						// "/api/**" 開頭的路徑需要身份驗證
						.requestMatchers("/api/wishlist", "/api/bidding/history","/api/bidding/bid").authenticated()
						// 其他所有路徑允許訪問
						.anyRequest().permitAll());

		return http.build();
	}
}