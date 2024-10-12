package com.artist.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.artist.entity.Customers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    // 從 Token 中提取 email
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 提取 Token 中的任意信息
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 提取所有 Token 的 Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }


    // 提取 Token 的到期時間
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 生成 Token（根據 Customers 物件）
    public String generateToken(Customers customer) {
    	Map<String, Object> claims = new HashMap<>();
		//將想放近token的資訊一併寫入
		claims.put("nickname", customer.getNickName());
		claims.put("customerId", customer.getCustomerId());

		// 生成 JWT
		return Jwts.builder().setSubject(customer.getEmail()).addClaims(claims) // 添加其他 claims
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 天
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
    
    
    // 驗證 Token 是否有效
    public Boolean validateToken(String token, Customers customer) {
        final String email = extractEmail(token);
        return (email.equals(customer.getEmail()) && !isTokenExpired(token));
    }
    
    
	public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date()); // 檢查是否過期 expirationDate < new Date()還沒過期
        } catch (Exception e) {
            return true;
        }
        
    }
	
    //生成 臨時 JWT 的方法
    public String generatePasswordResetToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        return Jwts.builder()
                .setSubject(email)
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 900000)) // 15 分鐘
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
	public String getCustomerIdFromToken(String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return (String) claims.get("customerId");
	}
}