package com.artist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 使用 BCrypt 加密算法
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	   http
           .csrf(csrf -> csrf.disable())  // 禁用 CSRF
           .cors(cors -> cors.disable())  // 啟用 CORS

           // 配置路徑的授權規則
           .authorizeHttpRequests(auth -> auth
               // 允許未認證用戶訪問 "/customers/login", "/customers/register" 路徑
               .requestMatchers("/customers/login", "/customers/register").permitAll() 

               // 允許訪問其他非 /api/ 的路徑
               .requestMatchers("/public/**", "/resources/**", "/static/**").permitAll()

               // "/api/**" 開頭的路徑需要身份驗證
               .requestMatchers("/api/**").authenticated()

               // 其他所有路徑允許訪問
               .anyRequest().permitAll()
           ); 

       return http.build();
    }
}