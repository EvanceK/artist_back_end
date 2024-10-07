package com.artist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 添加自定義過濾器
				.authorizeHttpRequests(ExpressionUrlAuthorizationConfigurer -> ExpressionUrlAuthorizationConfigurer
						.requestMatchers("/api/token/refresh").permitAll() // 允許刷新 token API 訪問
						.requestMatchers("/api/**").authenticated() // 需要身份驗證的 API 路徑
						.anyRequest().permitAll() // 其他請求允許所有 搭配authenticated 就是 需通過身份認證
				);
		return http.build();
	}
	

}
